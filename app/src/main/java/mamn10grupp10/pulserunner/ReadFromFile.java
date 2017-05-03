package mamn10grupp10.pulserunner;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
=======

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
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileInputStream;

public class ReadFromFile  extends AppCompatActivity {

<<<<<<< HEAD

    public String readFromFile(String selectfile){
        String txt = "";
        FileInputStream fiS = null;
=======
    /*Anne-Lines read method*/
    public String readfilefromAssist(String file) throws IOException {
        BufferedReader reader= null;
        StringBuilder sb= new StringBuilder();
>>>>>>> origin/master
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
<<<<<<< HEAD
=======

    public String getTimeStamp(String fileString){
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        return stringList.get(2);
    }



>>>>>>> origin/master
}

