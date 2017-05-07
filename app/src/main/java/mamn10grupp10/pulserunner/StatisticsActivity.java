package mamn10grupp10.pulserunner;

import android.content.Context;

import android.content.Intent;

import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    private Vibrator vib;
    private int speed;
    private int sleep;
    private TextView tw;
    private TextView twSleep;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/PulseRunnerData";

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

        /*Testing save*/
        File dir = new File(path);
        dir.mkdir();
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

    public void TestSave(View v){
        Intent intent = new Intent(this,NewTest.class);
        startActivity(intent);
    }


    private String trackName = "Träskrundan";
    private String runnerName = "Christer Sjögren";
    private String savedData;


    public void onClickLoad(View v){
        twLoader.setText("Load Button Pressed");

        String filename = "myfile";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
           // int s = inputStream.read();
            twLoader.setText(convertStreamToString(inputStream));
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ReadFromFile rfw = new ReadFromFile();
        //File file = new File (path + "/savedFile.txt");
        //String [] loadText = loadFromFile(file);
        //StringBuilder sb = new StringBuilder();
        //for(int i = 0; i<loadText.length;i++){
          //  sb.append(loadText[i]+"\n");
        //}
        //twLoader.setText(sb.toString());
        /*
        savedData = rfw.readFromFile(this.getApplicationContext());
        sb.append("Get TrackName: "+rfw.getTrackName(savedData)+"\n");
        sb.append("Get RunnerName: "+rfw.getRunnerName(savedData)+"\n");
        ArrayList<Double> list = rfw.returnDiffArray(savedData);
        for(double value: list){
            sb.append(value+"\n");
        }
        twLoader.setText(sb.toString());
        */


    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

    public void onClickSave(View v){
        twLoader.setText("Save Button pressed.");
        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WriteToFile wf = new WriteToFile();
        //File file = new File(path+"/savedFile.txt");
        //int length = listOfValues.size() + 2;
        //String [] saveText = new String [length];

        /*
        for(int i = 0; i<length-1; i++){
            if(i==0){
                saveText[i] = trackName;
            }else if(i==1){
                saveText[i] = runnerName;
            }else{
                saveText[i] = listOfValues.get(i).toString();
            }
        }*/
        //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        //saveToFile(file, saveText);
        //String data = wf.createStringFile(trackName,runnerName,listOfValues);
        //savedData = savedData +"\n"+"\n" + data;
        //savedData = data;
        //twLoader.setText("Save Button pressed \n"+data);
        //wf.writeToFile(data,this.getApplicationContext());
    }

    public static void saveToFile(File file, String[] data){
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    public static String[] loadFromFile(File file){
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}
        return array;
    }
}
