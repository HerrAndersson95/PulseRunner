package mamn10grupp10.pulserunner;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RunActivityTreadmillRunning extends AppCompatActivity {
    /*Varibles for speed*/
    private int speed = 5;
    private double myAvgSpeed;
    private int totMeters = 0;

    private TextView tw;
    TextView displayTitle;
    TextView displayTime;
    TextView displayValue;
    Handler handler;
    CalculationManager manager;
    ToggleButton onOffTime;
    Button stop;
    Button finish;
    StopWatch stopwatch;
    double timeunit;
    double mySpeed;
    double mySpeedSmooth;

    /*Varibles for Vib*/
    private Vibrator vib;
    long[] vibPattern;
    private final long[] close = {0,200,1500};
    private final long[] closeer = {0,200,800};
    private final long[] closest = {0,200,200};


    /*Varibles to send*/
    private int distance;
    private int hours,mins,ms;
    //Variables for GPSservice
    protected static final int MY_PERMISSIONS_REQUEST_GPS_FINE = 1;
    private Location currentLocation;
    private Location oldCurrentLocation;
    public static String str_receiver = "GPSserviceTAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_running);


        timeunit = 10;
        mySpeed = 0;
        mySpeedSmooth = 0;

        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stopwatch = new StopWatch();
        handler = new Handler();
        manager = new CalculationManager();


        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);
        displayValue = (TextView) findViewById(R.id.speedText);

        onOffTime = (ToggleButton) findViewById(R.id.onOff);
        onOffTime.setText("STARTA");

        stop = (Button) findViewById(R.id.btnStop);
        finish = (Button) findViewById(R.id.btnFinish);

        tw = (TextView) findViewById(R.id.avgSpeed);
        Intent intent = getIntent();
        speed = intent.getIntExtra("speed",0);
        tw.setText(speed + " km/h");

        //start GPS service
        if (hasPermissions()){
            startService(new Intent(this, GPSservice.class));
            System.out.println("GPS service started");
        }
        else {
            requestPerms();
        }

        final Runnable updater = new Runnable() {
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
                        //displayValue.setText(Double.toString(mySpeed) + " km/h");
                        System.out.println(mySpeedSmooth);
                    }
                    displayValue.setText(Double.toString(mySpeedSmooth) + " km/h");
                }
            }
        };

        /*Lägg till om det gått mer än 10 sec*/
        if(true){

        }

        /*Start/Pause/Continue-button */
        onOffTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!onOffTime.isChecked()){
                    stopwatch.pause();
                    displayTitle.setText("Paused");
                }
                else{
                    stopwatch.resume();
                    displayTitle.setText("Running");
                }
                handler.post(updater);
            }
        });
    }

    //PERMISSION CHECK START TO FIN
    private boolean hasPermissions(){
        int res = 0;
        //string array of permissions,
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                System.out.println("PERM NOT GRANTED GRANTED");
                return false;
            }
        }
        System.out.println("PERM ALREADY GRANTED GRANTED");
        return true;
    }
    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,MY_PERMISSIONS_REQUEST_GPS_FINE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GPS_FINE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    System.out.println("OK PERM GRANTED");

                    System.out.println("OK GPS SETUP COMPLETED");

                } else {
                    System.out.println("No Permission given");
                    finish();
                }
            }
        }
    }
    //PERMISSION CHECK FIN

    //GPSservice BROADCAST to activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = (Bundle) intent.getBundleExtra("Location");
            if(oldCurrentLocation == null){
                oldCurrentLocation = b.getParcelable("Location");
                currentLocation = b.getParcelable("Location");
            }
            oldCurrentLocation = currentLocation;
            currentLocation = b.getParcelable("Location");

            float spd = currentLocation.distanceTo(oldCurrentLocation);
            System.out.println(spd);
            mySpeed = spd * 3.6;
            mySpeed = round(mySpeed);
            mySpeedSmooth = lowPassFilter(mySpeed, mySpeedSmooth);
            mySpeedSmooth = round(mySpeedSmooth);

            System.out.println("GPS recieved kmh: " + mySpeedSmooth);
        }
    };

    public void onClickStop(View v){
        createDialog();
    }

    /*Use the arraylist to sum all distances*/
    public int getDistance(){
        return 1;
    }

    public void onClickFinish(View v){
        Intent intent = new Intent(this, RunActivityTreadmillFinish.class);
       /*Change these to the right value when we've got the GPS part etc...*/
        int avgSpeed = (int) myAvgSpeed;
        hours = 13;
        mins = 37;
        ms = 99;
        distance = (int)7035.50;
        intent.putExtra("speed",speed);
        intent.putExtra("myAvgSpeed",avgSpeed);
        intent.putExtra("hours",hours);
        intent.putExtra("mins",mins);
        intent.putExtra("ms",ms);
        intent.putExtra("distance",distance);
        startActivity(intent);
    }

    public void onClickPlus(View v){
        System.out.println(speed + " km/h");

        if(speed<50){
            speed++;
            tw.setText(speed + " km/h");
        }
        System.out.println(speed + " km/h");
    }

    public void onClickMinus(View v){
        if(speed>1){
            speed--;
            tw.setText(speed + " km/h");
        }
    }

    /*Sets the vibration according to if you are keeping the avg speed u want.
    * The more freq vibrations indicate on the further away from your avg speed
    * So hurry up!*/
    public void setVibPattern(){
        if(myAvgSpeed > speed){
            vib.cancel();
        } else{
            double percentage = myAvgSpeed % speed;
            if(percentage > 75){
                vib.vibrate(close,0);
            }else if(percentage > 50){
                vib.vibrate(closeer,0);
            }else {
                vib.vibrate(closest,0);
            }
        }
    }

    public double getMyAvgSpeed(int totalMeters, int totalSecs ){
        return totalMeters/totalSecs;
    }

    public void createDialog(){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Exit Treadmill Mode?");
        alertDlg.setCancelable(false);
        final Intent popUpintent = new Intent(this, RunActivityTreadmill.class);

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

    //Called by activity
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(RunActivityTreadmillRunning.str_receiver));

    }
    //Called by activity
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private double lowPassFilter( double input, double output ) {
        output = output + 0.5 * (input - output);
        return output;
    }

    public double round(double value) {
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
