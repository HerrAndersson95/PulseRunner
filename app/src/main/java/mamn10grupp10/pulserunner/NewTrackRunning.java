package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class NewTrackRunning extends AppCompatActivity {

    public NewTrackRunning() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_running);

    }


    //Stop
    public void onClickStop(View v){
        createDialog();
    }

    //Pause
    public void onClickPause(View v){
        Intent intent = new Intent(this, NewTrackPause.class);
        startActivity(intent);
    }

    //Finish
    public void onClickFinish(View v){
        Intent intent = new Intent(this, NewTrackDone.class);
        startActivity(intent);
    }

    public void createDialog(){
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Your newly created track will be lost, are you sure?");
        alertDlg.setCancelable(false);
        final Intent popUpintent = new Intent(this, NewTrackActivity.class);

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
