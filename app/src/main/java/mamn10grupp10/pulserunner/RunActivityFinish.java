package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RunActivityFinish extends AppCompatActivity {
    private double totSec;
    private double distance = 1;
    private TextView tw;
    private String time;
    private ArrayList<Double> newTrackList;
    private String trackName;
    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_finish);
        fileManager = new FileManager(this.getApplicationContext());
        Intent intent = getIntent();
        tw = (TextView) findViewById(R.id.InfoText);
        trackName = intent.getStringExtra("trackName");
        Bundle b = new Bundle();
        b = intent.getBundleExtra("bundle");
        newTrackList = (ArrayList<Double>) b.getSerializable("newtrack");
        totSec = intent.getDoubleExtra("totSec",0.0);
        time = intent.getStringExtra("time");
        distance = newTrackList.get(newTrackList.size()-1);
        double distKm = distance/1000;
        double avgSpeed = distance/totSec;

        String infoTxt = "My average speed was: " + avgSpeed +" m/s"+ "\n"+
                "Distance: "+distKm+" km"+"\n"+
                "Total time: "+time+"\nLogs:\n";
        StringBuilder sb = new StringBuilder();
        sb.append(infoTxt);
        for(Double mets : newTrackList){
            sb.append(mets + " - ");
        }
        tw.setText(sb.toString());
    }

    public void onClickDone(View v){
        /*ADD A SAVE METHOD HERE*/
        Intent intent = new Intent(this, MainActivity.class);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = trackName + " - " + time + " - " + timeStamp;
        fileManager.writeFile(fileName,fileManager.creatStringFile(newTrackList));
        Toast.makeText(this,"Saved route: "+fileName,Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void onBackPressed(){
        //Overwrites so the back button cannot be used.
    }
}
