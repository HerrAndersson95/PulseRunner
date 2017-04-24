package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RunActivityTreadmillRunning extends AppCompatActivity {
    private int speed = 5;
    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_running);
        tw = (TextView) findViewById(R.id.avgSpeed);
        Intent intent = getIntent();
        speed = intent.getIntExtra("speed",0);
        tw.setText(speed + " km/h");
    }

    public void onClickFinish(View v){
        Intent intent = new Intent(this, RunActivityTreadmillFinish.class);
        startActivity(intent);
    }

    //Stop
    public void onClickStop(View v){
        createDialog();
    }

    //Pause
    public void onClickPause(View v){
        Intent intent = new Intent(this, RunActivityTreadmillPause.class);
        intent.putExtra("speed",speed);
        startActivity(intent);
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
