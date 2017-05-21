package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-05-03.
 */
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewTest extends AppCompatActivity {
    private Vibrator vib;
    private int speed;
    private int sleep;
    private TextView tw;
    private TextView twSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        speed = 100;
        sleep = 100;
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tw = (TextView) findViewById(R.id.infoBar);
        twSleep = (TextView) findViewById(R.id.sleepTime);
        tw.setText("Speed: " + speed + " ms");
        twSleep.setText("Sleep: " + sleep + " ms");

    }

    public void onClickClose(View v) {
        speed = 200;
        sleep = 1500;
        long[] vibClose = {0, speed, sleep};
        tw.setText("Speed: " + speed + " ms");
        twSleep.setText("Sleep: " + sleep + " ms");
        vib.vibrate(vibClose, 0);
    }

    public void onClickCloser(View v) {
        speed = 200;
        sleep = 800;
        long[] vibCloser = {0, speed, sleep};
        tw.setText("Speed: " + speed + " ms");
        twSleep.setText("Sleep: " + sleep + " ms");
        vib.vibrate(vibCloser, 0);
    }

    public void onClickClosest(View v) {
        speed = 200;
        sleep = 200;
        long[] vibClosest = {0, speed, sleep};
        tw.setText("Speed: " + speed + " ms");
        twSleep.setText("Sleep: " + sleep + " ms");
        vib.vibrate(vibClosest, 0);
    }

    public void onClickStop(View v) {
        vib.cancel();
    }

    public void onClickPlus(View v) {
        speed = 50 + speed;
        tw.setText("Speed: " + speed + " ms");
        long[] pattern = {0, speed, sleep};
        vib.vibrate(pattern, 0);
    }

    public void onClickPlusSleep(View v) {
        sleep = 50 + sleep;
        twSleep.setText("Sleep: " + sleep + " ms");
        long[] pattern = {0, speed, sleep};
        vib.vibrate(pattern, 0);
    }

    public void onClickMinusSleep(View v) {
        if (sleep > 50) {
            sleep = sleep - 50;
        }
        twSleep.setText("Sleep: " + sleep + " ms");
        long[] pattern = {0, speed, sleep};
        vib.vibrate(pattern, 0);
    }

    public void onClickMinus(View v) {
        if (speed > 50) {
            speed = speed - 50;
        }
        tw.setText("Speed: " + speed + " ms");
        long[] pattern = {0, speed, sleep};
        vib.vibrate(pattern, 0);
    }

}
