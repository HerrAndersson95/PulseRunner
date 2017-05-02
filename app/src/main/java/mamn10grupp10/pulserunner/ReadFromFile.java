package mamn10grupp10.pulserunner;
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;
import java.io.FileInputStream;

public class ReadFromFile  extends AppCompatActivity {


    public String readFromFile(String selectfile){
        String txt = "";
        FileInputStream fiS = null;
        try {
            fiS = openFileInput(selectfile);

        byte [] input = new byte[fiS.available()];
        while(fiS.read(input)!=-1) {
            txt += new String(input);
        }
        fiS.close();
        return txt;
        }catch(FileNotFoundException e ) {
            e.printStackTrace();
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

