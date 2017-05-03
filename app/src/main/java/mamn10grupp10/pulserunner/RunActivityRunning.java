package mamn10grupp10.pulserunner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class RunActivityRunning extends AppCompatActivity {
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

    private Vibrator vib;
    private long[] vibPattern;
    private final long[] close = {0, 200, 1500};
    private final long[] closeer = {0, 200, 800};
    private final long[] closest = {0, 200, 200};

    /*Varibles to send*/
    private int distance;
    private int hours,mins,ms;
    private int secs;
    private ArrayList<Double> logDistances;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_running);
        logDistances = new ArrayList<Double>();

        timeunit = 10;
        newtrack = new ArrayList<>();

        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stopwatch = new StopWatch();
        handler = new Handler();
        manager = new CalculationManager();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);
        displayValue = (TextView) findViewById(R.id.textDifference);

        onOffTime = (ToggleButton) findViewById(R.id.onOff);
        onOffTime.setText("STARTA");

        stop = (Button) findViewById(R.id.btnStop);
        finish = (Button) findViewById(R.id.btnFinish);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


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
                        double distDiff = manager.getDistanceDifference(a,b);
                        if (distDiff <0){
                            displayValue.setTextColor(Color.argb(188, 121, 32, 63));
                            displayValue.setText("- " + Math.abs(distDiff) + " m");

                        }else{
                            displayValue.setTextColor(Color.argb(188, 97, 162, 108));
                            displayValue.setText("+ " + (distDiff) + " m");
                        }
                        a -= 1;
                        b += 3;
                    }
                }
            }
        };


        /*Start/Pause/Continue-button */
        onOffTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!onOffTime.isChecked()) {
                    stopwatch.pause();
                    displayTitle.setText("Paused");
                } else {
                    stopwatch.resume();
                    displayTitle.setText("Running");
                }
                handler.post(updater);
            }
        });
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

    public int getAvgSpeed(){
        return getDistance()/secs;
    }

    public int getDistance(){
        double distance = 0;
        for(Double value : logDistances){
            distance +=value;
        }
        return (int) distance;
    }


    public void onClickFinish(View v) {
        Intent intent = new Intent(this, RunActivityFinish.class);
        /*Change these to the right value when we've got the GPS part etc...*/
        //int avgSpeed = getAvgSpeed();
        int avgSpeed = 66;
        hours = 13;
        mins = 37;
        ms = 99;
        distance = (int)7035.50;
        intent.putExtra("myAvgSpeed",avgSpeed);
        intent.putExtra("hours",hours);
        intent.putExtra("mins",mins);
        intent.putExtra("ms",ms);
        intent.putExtra("distance",distance);
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
    public void setVibPattern(int thisTrackMeter, int savedTrackMeter) {
        double diff = thisTrackMeter - savedTrackMeter;
        /*If you are behind*/
        if (diff < 0) {
            double percentage = thisTrackMeter % savedTrackMeter;
            if (percentage > 90) {
                vib.vibrate(closest, 0);
            }

            /*Els you are ahead*/
        } else {
            double percentage = thisTrackMeter % savedTrackMeter;
            if (percentage < 10) {
                vib.vibrate(closest, 0);
            } else if (percentage < 20) {
                vib.vibrate(closeer, 0);
            } else if (percentage < 40) {
                vib.vibrate(close, 0);
            } else {
                vib.cancel();
            }

        }
    }


}