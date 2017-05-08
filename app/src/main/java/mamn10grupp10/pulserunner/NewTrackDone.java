package mamn10grupp10.pulserunner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewTrackDone extends AppCompatActivity {
    EditText nameOfTrack;
    Button save;
    ArrayList<String> nameoftracklist;
    FileManager fileManager;
    ArrayList<Double> newTrackList;
    String time;
    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_done);
        nameOfTrack = (EditText)findViewById(R.id.trackName);
        save = (Button)findViewById(R.id.save);
        nameoftracklist = new ArrayList<String>();
        fileManager = new FileManager(this.getApplicationContext());
        Intent intent = getIntent();
        Bundle b = new Bundle();
        b = intent.getBundleExtra("bundle");
        time = intent.getStringExtra("time");
        newTrackList = (ArrayList<Double>) b.getSerializable("newtrack");
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

    public void onClickSave(View v){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = nameOfTrack.getText().toString() + " - " + time + " - " + timeStamp;
        fileManager.writeFile(fileName,fileManager.creatStringFile(newTrackList));
         //fileManager.writeFile(nameOfTrack.getText().toString()+" "+time+"/ "+timeStamp,fileManager.creatStringFile(newTrackList));
        //fileManager.writeFile(nameOfTrack.getText().toString()+"/ ","123");
        //fileManager.writeFile(nameOfTrack.getText().toString()+"/ "+timeStamp,fileManager.creatStringFile(newTrackList));
        Toast.makeText(this,"Saved name of the route",Toast.LENGTH_LONG).show();
         Intent intent = new Intent(this, MainActivity.class);
         startActivity(intent);

    }

}
