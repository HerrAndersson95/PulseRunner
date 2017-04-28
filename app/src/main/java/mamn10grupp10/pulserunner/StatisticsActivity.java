package mamn10grupp10.pulserunner;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    private Vibrator vib;
    private int speed;
    private int sleep;
    private TextView tw;
    private TextView twSleep;

    //Test
    private TextView twLoader;
    private ArrayList<Double> listOfValues = new ArrayList<Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listOfValues.add(50.0);
        listOfValues.add(30.0);
        listOfValues.add(40.0);
        listOfValues.add(50.0);
        listOfValues.add(60.0);

        speed = 100;
        sleep = 100;
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tw = (TextView) findViewById(R.id.infoBar);
        twSleep = (TextView) findViewById(R.id.sleepTime);
        twLoader = (TextView) findViewById(R.id.loadedText);

        tw.setText("Speed: "+speed+" ms");
        twSleep.setText("Sleep: "+sleep+" ms");
        twLoader.setText("Loader...");
    }

    public void onClickClose(View v){
        speed = 200;
        sleep = 1500;
        long[] vibClose = {0,speed,sleep};
        tw.setText("Speed: "+speed+" ms");
        twSleep.setText("Sleep: "+sleep+" ms");
        vib.vibrate(vibClose,0);
    }
    public void onClickCloser(View v){
        speed = 200;
        sleep = 800;
        long[] vibCloser = {0,speed,sleep};
        tw.setText("Speed: "+speed+" ms");
        twSleep.setText("Sleep: "+sleep+" ms");
        vib.vibrate(vibCloser,0);
    }
    public void onClickClosest(View v){
        speed = 200;
        sleep = 200;
        long[] vibClosest = {0,speed,sleep};
        tw.setText("Speed: "+speed+" ms");
        twSleep.setText("Sleep: "+sleep+" ms");
        vib.vibrate(vibClosest,0);
    }
    public void onClickStop(View v){
        vib.cancel();
    }

    public void onClickPlus(View v){
        speed = 50 + speed;
        tw.setText("Speed: "+speed+" ms");
        long[] pattern = {0,speed,sleep};
        vib.vibrate(pattern,0);
    }
    public void onClickPlusSleep(View v){
        sleep = 50 + sleep;
        twSleep.setText("Sleep: "+sleep+" ms");
        long[] pattern = {0,speed,sleep};
        vib.vibrate(pattern,0);
    }

    public void onClickMinusSleep(View v){
        if(sleep > 50){
            sleep = sleep - 50;
        }
        twSleep.setText("Sleep: "+sleep+" ms");
        long[] pattern = {0,speed,sleep};
        vib.vibrate(pattern,0);
    }

    public void onClickMinus(View v){
        if(speed > 50){
            speed = speed - 50;
        }
        tw.setText("Speed: "+speed+" ms");
        long[] pattern = {0,speed,sleep};
        vib.vibrate(pattern,0);
    }



    private String trackName = "Träskrundan";
    private String runnerName = "Christer Sjögren";
    private String savedData;

    public void onClickLoad(View v){
        twLoader.setText("Load Button Pressed");
        ReadFromFile rfw = new ReadFromFile();
        StringBuilder sb = new StringBuilder();
        sb.append("Get TrackName: "+rfw.getTrackName(savedData)+"\n");
        sb.append("Get RunnerName: "+rfw.getRunnerName(savedData)+"\n");
        ArrayList<Double> list = rfw.returnDiffArray(savedData);
        for(double value: list){
            sb.append(value+"\n");
        }
        twLoader.setText(sb.toString());



    }

    public void onClickSave(View v){
        twLoader.setText("Save Button pressed.");
        WriteToFile wf = new WriteToFile();
        String data = wf.createStringFile(trackName,runnerName,listOfValues);
        //savedData = savedData +"\n"+"\n" + data;
        savedData = data;
        twLoader.setText("Save Button pressed \n"+data);
        /*
        wf.saveFile(wf.createStringFile(trackName,runnerName,listOfValues));*/
    }


}
