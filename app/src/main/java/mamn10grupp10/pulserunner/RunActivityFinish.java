package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RunActivityFinish extends AppCompatActivity {
    private int speed,myAvgSpeed,hours,mins,ms;
    private double distance;
    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_finish);

        Intent intent = getIntent();
        tw = (TextView) findViewById(R.id.InfoText);
        myAvgSpeed = intent.getIntExtra("myAvgSpeed",0);
        hours = intent.getIntExtra("hours",0);
        mins = intent.getIntExtra("mins",0);
        ms = intent.getIntExtra("ms",0);
        distance = intent.getIntExtra("distance",0);
        distance = distance/1000;
        distance = distance*100;
        distance = Math.round(distance);
        distance = distance/100;
        tw.setText("My average speed was: " + myAvgSpeed +" km/h"+ "\n"+
                "Distance: "+distance+" km"+"\n"+
                "Total time: "+hours+":"+mins+":"+ms);
    }

    public void onClickDone(View v){
        /*ADD A SAVE METHOD HERE*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        //Overwrites so the back button cannot be used.
    }
}
