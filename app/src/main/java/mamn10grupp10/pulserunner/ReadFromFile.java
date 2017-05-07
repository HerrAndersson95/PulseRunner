package mamn10grupp10.pulserunner;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;

public class ReadFromFile extends AppCompatActivity {

public ReadFromFile(){

}
    public String readFromFile(){
        String txt;

        try {
          InputStream inputstream =getAssets().open("distance.txt");
        byte [] input = new byte[inputstream.available()];
       inputstream.read(input);
            txt = new String(input);
        inputstream.close();
        return txt;

        } catch(IOException e) {
        e.printStackTrace();

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
        for(int i = 2; i<stringList.size();i++){
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
}

