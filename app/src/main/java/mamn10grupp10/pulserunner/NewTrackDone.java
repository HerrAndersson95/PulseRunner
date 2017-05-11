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
    double totSec,totDist;
    double avgSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_done);
        nameOfTrack = (EditText)findViewById(R.id.trackName);
        save = (Button)findViewById(R.id.save);
        nameoftracklist = new ArrayList<String>();
        fileManager = new FileManager(this.getApplicationContext());
        Intent intent = getIntent();
        totDist = intent.getDoubleExtra("distance",0.0);
        totSec = intent.getDoubleExtra("totSec",0.0);
        Bundle b = new Bundle();
        b = intent.getBundleExtra("bundle");
        time = intent.getStringExtra("time");

        double distKm = totDist/1000;
        avgSpeed = totDist/totSec;
        avgSpeed = avgSpeed*100;
        avgSpeed = Math.round(avgSpeed);
        avgSpeed = avgSpeed/100;
        distKm = distKm*100;
        distKm = Math.round(distKm);
        distKm = distKm/100;

        newTrackList = (ArrayList<Double>) b.getSerializable("newtrack");
        tw = (TextView) findViewById(R.id.infoText);
        StringBuilder sb = new StringBuilder();
        sb.append(time + "\n");
        sb.append("Distance: "+distKm+" km\n");
        sb.append("Average speed: "+avgSpeed+" m/s\n");

        tw.setText(sb.toString());
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
        if(!nameOfTrack.getText().toString().isEmpty()){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
            String fileName = nameOfTrack.getText().toString() + "  -  " + time + "\nDATE:  "+ timeStamp;
            fileManager.writeFile(fileName,fileManager.creatStringFile(newTrackList));
            fileManager.saveSpeed(nameOfTrack.getText().toString(),avgSpeed);
            Toast.makeText(this,"Your run is saved",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        //fileManager.writeFile(nameOfTrack.getText().toString()+" "+time+"/ "+timeStamp,fileManager.creatStringFile(newTrackList));
        //fileManager.writeFile(nameOfTrack.getText().toString()+"/ ","123");
        //fileManager.writeFile(nameOfTrack.getText().toString()+"/ "+timeStamp,fileManager.creatStringFile(newTrackList));


    }

}
