package mamn10grupp10.pulserunner;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import static android.hardware.SensorManager.getRotationMatrix;

public class RunActivityRunning extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, SensorEventListener {

    TextView displayTitle;
    TextView displayTime;
    TextView displayValue;
    Handler handler;
    ToggleButton onOffTime;
    Button stop;
    Button finish;
    StopWatch stopwatch;
    CalculationManager manager;
    int timeunit;
    Vibrator vibrator;
    ArrayList<Double> newtrack;
    double a = 10;
    double b = 2;
    double totDist;

    private Vibrator vib;
    private long[] vibPattern;
    private final long[] close = {0, 200, 1500};
    private final long[] closeer = {0, 200, 800};
    private final long[] closest = {0, 200, 200};
    private final long[] superBehind = {0, 200, 5000};
    private final long[] none = {0,0,0};

    //Variables for sensor
    private SensorManager sm;
    private Sensor prox;
    private float[] proxValues;
    private boolean proximityPaused;

    /*Varibles to send*/
    private int distance;
    private int hours,mins,ms;
    private int secs;
    private ArrayList<Double> logDistances;

    //Variables for GPS
    protected static final String TAG = "MainActivity";
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000; //The desired interval for location updates. Inexact. Updates may be more or less frequent.
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    protected final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    protected final static String KEY_LOCATION = "location";
    protected final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";
    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected LocationSettingsRequest mLocationSettingsRequest;
    protected Boolean mRequestingLocationUpdates;
    //Time when the location was updated represented as a String.
    protected String mLastUpdateTime;
    protected Location mCurrentLocation;
    protected Location oldmCurrentLocation;
    protected long mCurrentLocationTime;
    protected long oldmCurrentLocationTime;
    protected String filename;
    protected FileManager filemanager;
    protected  String data;
    protected ArrayList <Double> compareTrack;
    protected ArrayList <Double> newTrack;
    private String trackName;
    private int counter;
    private TextView twInfo;

    private Runnable updater;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_running);

        logDistances = new ArrayList<Double>();
        Intent intent = getIntent();

        timeunit = 5;
        newtrack = new ArrayList<>();
        totDist = 0;
        counter = 0;

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);   //skapa manager för sensorer
        prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximityPaused = false;

        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stopwatch = new StopWatch();
        handler = new Handler();
        manager = new CalculationManager();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);
        displayValue = (TextView) findViewById(R.id.textDifference);
        twInfo = (TextView) findViewById(R.id.runningInfo);

        onOffTime = (ToggleButton) findViewById(R.id.onOff);
        onOffTime.setText("STARTA");

        stop = (Button) findViewById(R.id.btnStop);
        finish = (Button) findViewById(R.id.btnFinish);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        filename = intent.getStringExtra("selectedTrack");


        filemanager = new FileManager(this.getApplicationContext());
        data = filemanager.readFile(filename);
        compareTrack = filemanager.returnDiffArray(data);
        newtrack = new ArrayList<Double>();


        updater = new Runnable() {
            public void run() {
                if (onOffTime.isChecked()) {
                    if (!stopwatch.hasItStarted()) {
                        stopwatch.startStopWatch();
                        displayTitle.setText("Running");
                        stop.setVisibility(View.VISIBLE);
                        finish.setVisibility(View.VISIBLE);
                    }
                    String elapsedTime = stopwatch.getTimeElapsedAsString();
                    long elapsedTimeLong = stopwatch.getTimeElapsedAsLong();
                    displayTime.setText(elapsedTime);
                    handler.postDelayed(this, 100);
                    if((elapsedTimeLong/100) % (timeunit*10) == 0){
                        logDistances.add(totDist);
                        if(counter < compareTrack.size()){
                            double compare = compareTrack.get(counter);

                        /*Vad är detta???*/
                            //double distDiff = manager.getDistanceDifference(a,b);
                        /*Bör det ej vara:*/
                            double distDiff = totDist - compare;
                            distDiff = distDiff*100;
                            distDiff = Math.round(distDiff);
                            distDiff = distDiff/100;
                            if (distDiff <0){
                                displayValue.setTextColor(Color.argb(188, 121, 32, 63));
                                displayValue.setText("- " + Math.abs(distDiff) + " m");

                            }else{
                                displayValue.setTextColor(Color.argb(188, 97, 162, 108));
                                displayValue.setText("+ " + (distDiff) + " m");
                            }
                            double vibPerc = totDist/compare;
                            vibPerc = vibPerc*100;
                            vibPerc = Math.round(vibPerc);
                            vibPerc = vibPerc/100;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Distance you've ran: "+totDist+" m\n");
                            sb.append("Distance other track: "+compare+" m\n");
                            sb.append("Percentage speed: "+vibPerc);
                            twInfo.setText(sb.toString());
                            setVibPattern(vibPerc);
                            counter++;
                        }else{
                            //OH NO!
                        }

                    }
                }
            }
        };


        /*Start/Pause/Continue-button */
        onOffTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                if (!onOffTime.isChecked()) {
                    stopwatch.pause();
                    displayTitle.setText("Paused");
                    stopUpdatesnHandler(); //Pauses GPS
                } else {
                    stopwatch.resume();
                    displayTitle.setText("Running");
                    startUpdatesHandler(); //Start GPS
                }
                */
                onPauseAndContinue();
                handler.post(updater);
            }
        });

        //Kickoff gps
        mRequestingLocationUpdates = true;
        buildGoogleApiClient();
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    public void onPauseAndContinue(){
        if(proximityPaused){
            if(!onOffTime.isChecked()){
                proximityPaused = false;
                onOffTime.setChecked(true);
                stopwatch.resume();
                //setVibPattern(vibPerc);
                displayTitle.setText("Running");
                //startUpdatesHandler();  //Start GPS
                System.out.println("STARTED - WITH PROXIMITY");
            } else {
                proximityPaused = false;
                onOffTime.setChecked(false); //Sätter knapp på paus
                stopwatch.pause();
                vib.cancel();
                displayTitle.setText("Paused");
                //stopUpdatesnHandler(); //Pauses GPS
                System.out.println("PAUSED - WITH PROXIMITY");
            }
        } else if (!onOffTime.isChecked()){
            stopwatch.pause();
            vib.cancel();
            displayTitle.setText("Paused");
            //stopUpdatesnHandler(); //Pauses GPS
            System.out.println("PAUSED - WITH BUTTON");
        } else {
            stopwatch.resume();
            //setVibPattern(vibPerc);
            displayTitle.setText("Running");
            //startUpdatesHandler();  //Start GPS
            System.out.println("STARTED - BUTTON");
        }
        handler.post(updater);
    }

    public void onClickStop(View v) {
        createDialog();
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle("Save Or Not");
        builder.setMessage("Your current track will be lost, are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                RunActivityRunning.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing
            }
        });
        builder.show();
    }

    public void onClickFinish(View v) {
        Intent intent = new Intent(this, RunActivityFinish.class);
        /*Change these to the right value when we've got the GPS part etc...*/
        //int avgSpeed = getAvgSpeed();
        double totSec = stopwatch.getTimeElapsedAsLong() /1000;
        intent.putExtra("trackName",trackName);
        intent.putExtra("totSec",totSec);
        intent.putExtra("trackName",filename);
        Bundle b = new Bundle();
        b.putSerializable("newtrack",logDistances);
        intent.putExtra("bundle",b);
        intent.putExtra("time",displayTime.getText().toString());
        vib.cancel();
        startActivity(intent);
    }

    /* Creates a dialog with a message that is asking the user if he/she really want to stop
    * the current run. If Yes, the user will return to the main menu. If no, the uset till return
    * to the current run again and the time wont be reflected, since it is still running in
    * the background*/
    public void createDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Your current track will be lost, are you sure?");
        alertDlg.setCancelable(false);
        final Intent popUpintent = new Intent(this, MainActivity.class);

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(popUpintent);
            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();
    }



    /*The vib pattern will be set accordingly to the percentage of the track
    * Compares the saved tracks meters towards what you've ran yourself*/
    public void setVibPattern(double percDiff) {
        if(percDiff>1.40){
            vib.vibrate(none,0);
        }else if(percDiff>1.20){
            vib.vibrate(close,0);
        }else if(percDiff >1.10){
            vib.vibrate(closeer,0);
        }else if(percDiff>0.80){
            vib.vibrate(closest,0);
        }else {
            vib.vibrate(none,0);
        }
    }

    private double lowPassFilter( double input, double output ) {
        output = output + 0.5 * (input - output);
        return output;
    }

    private double round(double value) {
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }




    //Proximity Sensor
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            proxValues = event.values;
            //Recieved a change in Sensors
            if(proxValues != null){
                //System.out.println("PROXIMITY: " + proxValues[0]);
                //HAND IS OVER PROXIMITY
                if(proxValues[0] == 0){
                    //System.out.println("HAND OVER PROX");
                    if(proximityPaused){
                        proximityPaused = false;
                    } else {
                        proximityPaused = true;
                    }
                    onPauseAndContinue();
                }
            }
        }
    }






    //GPS STARTS HERE
    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(KEY_REQUESTING_LOCATION_UPDATES)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(KEY_REQUESTING_LOCATION_UPDATES);
            }
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }
            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME_STRING)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME_STRING);
            }
            updateLocationUI();
        }
    }
    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        updateLocationUI();
                        break;
                }
                break;
        }
    }
    public void startUpdatesHandler() {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            startLocationUpdates();
        }
    }
    public void stopUpdatesnHandler() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        stopLocationUpdates();
    }
    protected void startLocationUpdates() {
        LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, mLocationSettingsRequest
        ).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        //BEHÖVER PERMISSIONS
                        //noinspection MissingPermission
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, RunActivityRunning.this);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " + "location settings ");
                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the
                            // result in onActivityResult().
                            status.startResolutionForResult(RunActivityRunning.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        String errorMessage = "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings under Location tab";
                        Log.e(TAG, errorMessage);
                        Toast.makeText(RunActivityRunning.this, errorMessage, Toast.LENGTH_LONG).show();
                        mRequestingLocationUpdates = false;
                }
                updateLocationUI();
            }
        });
    }
    protected void stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Within {@code onPause()}, we pause location updates, but leave the
        // connection to GoogleApiClient intact.  Here, we resume receiving
        // location updates if the user has requested them.
        sm.registerListener(this, prox, SensorManager.SENSOR_DELAY_GAME);
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
        updateLocationUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
        vib.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sm.unregisterListener(this);
        mGoogleApiClient.disconnect();
        vib.cancel();
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        if (mCurrentLocation == null) {
            //PERMISSIONG CHECKING NEEDED
            //noinspection MissingPermission
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            oldmCurrentLocation = mCurrentLocation;
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            updateLocationUI();
        }
        if (mRequestingLocationUpdates) {
            Log.i(TAG, "in onConnected(), starting location updates");
            startLocationUpdates();
        }

    }

    /**
     * Callback that fires when the location changes.
     */
    @Override
    public void onLocationChanged(Location location) {
        oldmCurrentLocation = mCurrentLocation;
        mCurrentLocation = location;
        mCurrentLocationTime = mCurrentLocation.getTime();
        oldmCurrentLocationTime = oldmCurrentLocation.getTime();
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateLocationUI();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(KEY_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
        savedInstanceState.putString(KEY_LAST_UPDATED_TIME_STRING, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void updateLocationUI() {
        if (mCurrentLocation != null) {

            //latitude.setText(String.format(String.valueOf(mCurrentLocation.getLatitude())));
            //longitude.setText(String.format(String.valueOf(mCurrentLocation.getLongitude())));
            float dist = mCurrentLocation.distanceTo(oldmCurrentLocation);
            float gpsInterval = (mCurrentLocationTime - oldmCurrentLocationTime)/1000;
            totDist +=  dist;
            totDist = round(totDist);
            System.out.println("Toatl Distance is: " + totDist);
            /*
            if(gpsInterval != 0){
                mySpeed = (dist / gpsInterval) * 3.6;
            }
            mySpeed = round(mySpeed);
            mySpeedSmooth = lowPassFilter(mySpeed, mySpeedSmooth);
            mySpeedSmooth = round(mySpeedSmooth);
            */

            System.out.println("Distance is: " + dist);
            System.out.println("Interval time in seconds: " + gpsInterval);
            //System.out.println("Calculated speed is: " + mySpeedSmooth);
            System.out.println("Has speed? " + mCurrentLocation.hasSpeed());
            System.out.println("Speed: " +mCurrentLocation.getSpeed());
            System.out.println("Has Altitude? " +mCurrentLocation.hasAltitude());
            System.out.println("Altitide: " +mCurrentLocation.getAltitude());
            System.out.println("Accuracy: " +mCurrentLocation.getAccuracy());

            //speed.setText(String.valueOf(dist));


            System.out.println(String.valueOf(mLastUpdateTime));
            //Toast.makeText(GPSActivity.this, "TIME OF GPS UPDATE IS: " + String.valueOf(mLastUpdateTime), Toast.LENGTH_SHORT).show();
        }
    }
}