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

public class NewTrackActivity extends AppCompatActivity {
    TextView displayTitle;
    TextView displayTime;
    Handler handler;
    ToggleButton onOffTime;
    Button stop;
    Button finish;
    StopWatch stopwatch;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);

        stopwatch = new StopWatch();
        handler = new Handler();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.newTrackTitle);

        onOffTime = (ToggleButton) findViewById(R.id.onOff);
        onOffTime.setText("STARTA");

        stop = (Button) findViewById(R.id.btnStop);
        finish = (Button) findViewById(R.id.btnFinish);

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

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               createDialog();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_new_track_done);
            }
        });
    }

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