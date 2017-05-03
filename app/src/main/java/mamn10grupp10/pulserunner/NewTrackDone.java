package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewTrackDone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_done);
    }

    //Cancel
    public void onClickCancel(View v){
        createDialog();
    }

    private void createDialog(){
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

<<<<<<< HEAD
=======

    //Save
    public void onClickSave(View v){
        EditText etTrack = (EditText) findViewById(R.id.trackName);
        EditText etRunner = (EditText) findViewById(R.id.runnerName);
        String trackName = etTrack.getText().toString();
        String runnerName = etRunner.getText().toString();
        if(!runnerName.isEmpty() && !trackName.isEmpty()){
            Intent intent = new Intent(this, MainActivity.class);

            // Insert SAVE Method here!!

            startActivity(intent);
        }


    }
>>>>>>> origin/master
}
