package mamn10grupp10.pulserunner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.math.MathContext;
import android.icu.text.DecimalFormat;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RunActivityTreadmillFinish extends AppCompatActivity {
    private int speed;
    private String time;
    private double distance,myAvgSpeed;
    private TextView tw,twGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_finish);
        Intent intent = getIntent();
        tw = (TextView) findViewById(R.id.InfoText);
        twGrade = (TextView) findViewById(R.id.gradeOnRun);

        /*
        intent.putExtra("speed",speed);
        intent.putExtra("myAvgSpeed",mySpeedSmooth);
        intent.putExtra("time",displayTime.getText().toString());
        intent.putExtra("distance",distance);
        */
        speed = intent.getIntExtra("speed",0);
        myAvgSpeed = intent.getDoubleExtra("mySpeedSmooth",0);
        time = intent.getStringExtra("time");
        distance = intent.getDoubleExtra("distance",0);
        String distString = "";
        if(distance > 1000){
            double dist = distance*10;
            dist = Math.round(dist);
            dist = dist/10;

            distString = "Distance: "+dist+" km";

        }else {
            distString = "Distance: "+ distance+" m";
        }
        //tw.setText(speed +"\n"+ myAvgSpeed+"\n"+ time +"\n"+distance);
        tw.setText("Time: "+time+"\nThe chosen speed was: "+speed+" km/h"+"\n"+
        "My average speed was: " + myAvgSpeed +" km/h"+ "\n"+
        "Distance: "+distance+" km"+"\n");

        double result = myAvgSpeed/speed;
        if (result>1){
            twGrade.setText("Excellent!");
            twGrade.setTextColor(Color.argb(188, 97, 162, 108));
        }else if(result > 0.75) {
            twGrade.setText("Good!");
            twGrade.setTextColor(Color.argb(188,181,179,49));
        }else {
            twGrade.setText("Could get better..");
            twGrade.setTextColor(Color.argb(188, 121, 32, 63));
        }

    }

    public void onClickDone(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        //Overwrites so the back button cannot be used.
    }
}
