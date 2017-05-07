package mamn10grupp10.pulserunner; /**
 * Created by Annie on 2017-05-07.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import android.content.Context;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by annelinegjersem on 2017-05-07.
 */

public class FileManager {
    private Context context;

    public FileManager( Context context){
        this.context = context;

    }

    public String readFile(String filename) {
        String data = null;
        FileInputStream fs;
        try {
            fs = context.openFileInput(filename);
            data = (convertStreamToString(fs));


        } catch (Exception e) {
            e.printStackTrace();

        }
        return data;
    }

    public void writeFile(String filename,String inputdata) {

        try {
            FileOutputStream os = context.openFileOutput(filename, context.MODE_PRIVATE);
            os.write(inputdata.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
