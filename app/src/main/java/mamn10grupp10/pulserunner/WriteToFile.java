package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-04-05.
 */
<<<<<<< HEAD

import android.support.v7.app.AppCompatActivity;
=======
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.Date;

import java.io.IOException;
import java.io.File;

<<<<<<< HEAD
import java.io.FileOutputStream;

public class WriteToFile extends AppCompatActivity {
    File file;
    FileOutputStream fop = null;
    public void saveFile(String nameofrun) {
=======
public class WriteToFile  extends AppCompatActivity {


    /*Anne-Lines spar metod*/
    public void saveFile(String file) {
>>>>>>> origin/master
        try {
            file = new File(nameofrun);
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(nameofrun.getBytes());
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();

<<<<<<< HEAD
=======
            }
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
                for (int  i = 0; i<data.length; i++)
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
>>>>>>> origin/master
        }
    }

    /*Input the trackName, the runnerName and the arraylist of diffs (meters)
    * Creates StringBuillder and returns a strin with seperation of "\n", new line.*/
    public String createStringFile(String trackName, String runnerName, ArrayList<Double> listOfDiffs){
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        sb.append(trackName+"\n");
        sb.append(runnerName+"\n");
        sb.append(date.toString());
        for(double value : listOfDiffs) {
            sb.append(value+"\n");
        }
        return sb.toString();
    }
}

