package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RunActivityTreadmill extends AppCompatActivity {
    public int speed = 5;
    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tw = (TextView) findViewById(R.id.avgSpeed);
        tw.setText(speed + " km/h");
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, RunActivityTreadmillRunning.class);
        startActivity(intent);
    }

    public void onClickPulseRunner(View v){
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }

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


}
