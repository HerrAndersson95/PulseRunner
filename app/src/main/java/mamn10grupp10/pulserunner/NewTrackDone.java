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
    int totalfiles = 0;
    double distKm;



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


        avgSpeed = totDist/totSec;
        avgSpeed = avgSpeed*100*3.6;
        avgSpeed = Math.round(avgSpeed);
        avgSpeed = avgSpeed/100;
        distKm = totDist/1000;
        distKm = distKm*100;
        distKm = Math.round(distKm);
        distKm = distKm/100;
        avgSpeed = avgSpeed*10;
        avgSpeed = Math.round(avgSpeed);
        avgSpeed = avgSpeed/10;
        newTrackList = (ArrayList<Double>) b.getSerializable("newtrack");
        tw = (TextView) findViewById(R.id.infoText);
        StringBuilder sb = new StringBuilder();
        if(distKm > 1){
            sb.append(time + "\n");
            sb.append("Distance: "+distKm+" km\n");
            sb.append("Average speed: "+avgSpeed+" km/h\n");
        }else {
            sb.append(time + "\n");
            sb.append("Distance: "+totDist+" m\n");
            sb.append("Average speed: "+avgSpeed+" km/h\n");
        }
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
        if(!nameOfTrack.getText().toString().isEmpty()) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
            String spname = nameOfTrack.getText().toString();
            String fileName = spname + "  -  " + time + "\nDATE:  " + timeStamp;
            fileManager.writeFile(fileName, fileManager.creatStringFile(newTrackList));

            Toast.makeText(this, "previus speed " + fileManager.DisplaySpeed() + "new speed" + avgSpeed, Toast.LENGTH_LONG).show();
            if (totalfiles == 0) {
                fileManager.saveSpeed(spname, avgSpeed);
                fileManager.saveDistance(spname, totDist);
                totalfiles++;
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return;
            }
            if (Double.compare(avgSpeed, Double.parseDouble(fileManager.DisplaySpeed())) > 0) {
                fileManager.saveSpeed(spname, avgSpeed);
            }

            if (Double.compare(totDist, Double.parseDouble(fileManager.DisplayTotalDistance())) > 0)
                fileManager.saveDistance(spname, distKm);

        }
        totalfiles++;
        
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }

    }


