package mamn10grupp10.pulserunner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by annelinegjersem on 2017-04-05.
 */

public class ReadFromFile  extends AppCompatActivity {

    /*Anne-Lines read method*/
    public String readfilefromAssist(String file) throws IOException {
        BufferedReader reader= null;
        StringBuilder sb= new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            String line;
            while((line = reader.readLine())!= null) {
                    sb.append(line).append("\n");
                }
            return sb.toString();

        }catch (IOException e) {
            e.printStackTrace();

        }finally {
            reader.close();
        }
        return null;
    }


    /*Input the string from the file. Output only the double data with diffMeter*/
    public ArrayList<Double> returnDiffArray(String fileString){
        /*Creats a list with both name of track and runner, as well as double values*/
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        /*Creats a ArrayList for the double values and then only adds those from the stringlist*/
        ArrayList<Double> list = new ArrayList<Double>();
        /*Starts at 2 instead of 0, so we skip the name of track and runner*/
        for(int i = 3; i<stringList.size();i++){
            /*Converts the index value to double*/
            list.add(Double.parseDouble(stringList.get(i)));
        }
        /*Returns the double list*/
        return list;
    }

    /*Returns the name of the track as a String*/
    public String getTrackName(String fileString){
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        return stringList.get(0);
    }

    /*Returns the name of the Runner as a string*/
    public String getRunnerName(String fileString){
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        return stringList.get(1);
    }

    public String getTimeStamp(String fileString){
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        return stringList.get(2);
    }



}
