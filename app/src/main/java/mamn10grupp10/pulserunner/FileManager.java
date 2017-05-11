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
import android.content.SharedPreferences;

/**
 * Created by annelinegjersem on 2017-05-07.
 */

public class FileManager {
    private Context context;
    SharedPreferences sp;
    double prespeed =0;
    public FileManager(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("Userinfo", context.MODE_PRIVATE);

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

    public void writeFile(String filename, String inputdata) {
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
    public ArrayList<Double> returnDiffArray(String fileString) {
        /*Creats a list with both name of track and runner, as well as double values*/
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        /*Creats a ArrayList for the double values and then only adds those from the stringlist*/
        ArrayList<Double> list = new ArrayList<Double>();
        /*Starts at 0, so we skip the name of track and runner*/
        for (int i = 0; i < stringList.size(); i++) {
            /*Converts the index value to double*/
            list.add(Double.parseDouble(stringList.get(i)));
        }
        /*Returns the double list*/
        return list;
    }

    public String creatStringFile(ArrayList<Double> listOfDiffs) {
        StringBuilder sb = new StringBuilder();
        for (double value : listOfDiffs) {
            sb.append(value + "\n");
        }
        return sb.toString();
    }


    /*Returns the name of the Runner as a string*/
    public String getRunnerName(String fileString) {
        List<String> stringList = new ArrayList<>(Arrays.asList(fileString.split("\n")));
        return stringList.get(1);
    }

    public String DisplayNameOfRoute() {
        return sp.getString("name", "");
    }

    public String DisplaySpeed() {
        return sp.getString("speed","");

    }

/*spara högst hastighet*/
    public void saveSpeed(String name,double currentSpeed){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name", name);
        edit.putString("speed", Double.toString(currentSpeed));
        edit.commit();
    }

    public void saveDistance(String name, double totaldistance){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("nameofRoute", name);
        edit.putString("distance", Double.toString(totaldistance));
        edit.commit();
    }
    public String DisplayNameofRouteDistance(){
        return sp.getString("nameofRoute","");
    }

    public String DisplayTotalDistance(){
        return sp.getString("distance", "");
    }
}
