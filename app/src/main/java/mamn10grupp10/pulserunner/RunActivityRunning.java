package mamn10grupp10.pulserunner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RunActivityRunning extends AppCompatActivity {
    TextView displayTitle;
    TextView displayTime;
    Handler handler;
    ToggleButton onOffTime;
    Button stop;
    Button finish;
    StopWatch stopwatch;

    private Vibrator vib;
    private long[] vibPattern;
    private final long[] close = {0, 200, 1500};
    private final long[] closeer = {0, 200, 800};
    private final long[] closest = {0, 200, 200};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_running);

        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stopwatch = new StopWatch();
        handler = new Handler();

        displayTime = (TextView) findViewById(R.id.runTime);
        displayTitle = (TextView) findViewById(R.id.RunnerTitle);

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

    public void onClickFinish(View v) {
        Intent intent = new Intent(this, RunActivityFinish.class);
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