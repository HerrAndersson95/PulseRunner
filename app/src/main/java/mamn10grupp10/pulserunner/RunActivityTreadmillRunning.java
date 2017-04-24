package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RunActivityTreadmillRunning extends AppCompatActivity {
    private int speed = 5;
    private TextView tw;
    TextView displayTitle;
    TextView displayTime;
    Handler handler;
    ToggleButton onOffTime;
    Button stop;
    Button finish;
    StopWatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_running);
        stopwatch = new StopWatch();
        handler = new Handler();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);

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
                    displayTime.setText(elapsedTime);
                    handler.postDelayed(this, 30);
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

    public void onClickFinish(View v){
        Intent intent = new Intent(this, RunActivityTreadmillFinish.class);
        startActivity(intent);
    }

    //Pause
    /*
    public void onClickPause(View v){
        Intent intent = new Intent(this, RunActivityTreadmillPause.class);
        intent.putExtra("speed",speed);
        startActivity(intent);
    }
    */


    public void onClickPlus(View v){
        System.out.println(speed + " km/h");

        if(speed<50){
            speed++;
            //tw.setText("Yes");
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
