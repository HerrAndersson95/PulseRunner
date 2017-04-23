package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RunActivityTreadmill extends AppCompatActivity {
    public String avg_speed_txt = getString(R.string.input_avg_speed);
    public int speed = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, RunActivityTreadmillRunning.class);
        startActivity(intent);
    }

    public void onClickPulseRunner(View v){
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }

    public void onClickPlus(){
        if(speed<50){
            speed++;
            avg_speed_txt = speed+" km/h";
        }
    }

    public void onClickMinus(){
        if(speed>1){
            speed--;
            avg_speed_txt = speed+" km/h";
        }
    }


}
