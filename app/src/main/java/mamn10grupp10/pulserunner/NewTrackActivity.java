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

public class NewTrackActivity extends AppCompatActivity {
    TextView displayTitle;
    TextView displayTime;
    TextView displayValue;

    ToggleButton onOffTime;
    Button stop;
    Button finish;

    Handler handler;
    StopWatch stopwatch;
    CalculationManager manager;

    int timeunit;
    double distDiff;
    Vibrator vibrator;
    ArrayList<Double> newtrack;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);

        timeunit = 10;
        newtrack = new ArrayList<>();
        distDiff = 0;

        stopwatch = new StopWatch();
        handler = new Handler();
        manager = new CalculationManager();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);
        displayValue = (TextView) findViewById(R.id.testText);

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
                        displayValue.setText(distDiff + " m");
                        distDiff ++;
                    }
                }
            }
        };


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

    public void onClickStop(View v){
        createDialog();
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle("Save Or Not");
        builder.setMessage("Your current track will be lost, are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                NewTrackActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing
            }
        });
        builder.show();
    }

    public void onClickFinish(View v){
        stopwatch.pause();
        Intent intent = new Intent(this, NewTrackDone.class);
        startActivity(intent);
    }

    /* Creates a dialog with a message that is asking the user if he/she really want to stop
    * the current run. If Yes, the user will return to the main menu. If no, the uset till return
    * to the current run again and the time wont be reflected, since it is still running in
    * the background*/
    public void createDialog(){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Your newly created track will be lost, are you sure?");
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
}


/*
double a = 56.773;
                        manager.getDistance(a, a, a ,a);
                        a ++;
                        displayTest.setText(Double.toString(manager.getDistanceDifference(a, a-1)));
                        double length = manager.getTotalLength();
                        newtrack.add(length);


* */