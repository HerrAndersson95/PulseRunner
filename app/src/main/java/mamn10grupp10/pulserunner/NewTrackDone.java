package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;

public class NewTrackDone extends AppCompatActivity {
EditText nameOfTrack;
    Button save;
 ArrayList<String>  nameoftracklist;
    FileManager fileManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_done);
        nameOfTrack = (EditText)findViewById(R.id.trackName);
        save = (Button)findViewById(R.id.save);
        nameoftracklist = new ArrayList<String>();
        fileManager = new FileManager(this.getApplicationContext());

    }

    //Cancel
    public void onClickCancel(View v) {
        createDialog();
    }

    private void createDialog() {
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

    public void saveTrack(View view) {
        fileManager.writeFile(nameOfTrack.getText().toString(),"23456743");
     Toast.makeText(this,"Saved name of the route",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

