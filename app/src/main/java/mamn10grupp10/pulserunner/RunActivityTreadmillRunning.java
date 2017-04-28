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

    /*Varibles for Vib*/
    private Vibrator vib;
    long[] vibPattern;
    private final long[] close = {0,200,1500};
    private final long[] closeer = {0,200,800};
    private final long[] closest = {0,200,200};

    /*Varibles for Vib*/
    private Vibrator vib;
    long[] vibPattern;
    private final long[] close = {0,200,1500};
    private final long[] closeer = {0,200,800};
    private final long[] closest = {0,200,200};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_running);

<<<<<<< HEAD
        timeunit = 10;
        mySpeed = 0;

=======
>>>>>>> origin/master
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
                        displayValue.setText(mySpeed + " km/h");
                        mySpeed ++;
                    }
                }
            }
        };

        /*Lägg till om det gått mer än 10 sec*/
        if(true){

<<<<<<< HEAD
=======
        }

>>>>>>> origin/master
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

    public void onClickFinish(View v){
        Intent intent = new Intent(this, RunActivityTreadmillFinish.class);
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


}
