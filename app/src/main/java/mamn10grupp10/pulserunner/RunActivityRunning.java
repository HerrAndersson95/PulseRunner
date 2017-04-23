package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RunActivityRunning extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_running);
    }

    //Stop
    public void onClickStop(View v){
        createDialog();
    }

    //Pause
    public void onClickPause(View v){
        Intent intent = new Intent(this, RunActivityPause.class);
        startActivity(intent);
    }

    public void onClickFinish(View v){
        Intent intent = new Intent(this, RunActivityFinish.class);
        startActivity(intent);
    }


    public void createDialog(){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Exit PulseRunner Mode?");
        alertDlg.setCancelable(false);
        final Intent popUpintent = new Intent(this, RunActivity.class);

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
