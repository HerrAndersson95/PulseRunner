package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-04-05.
 */


import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import java.io.IOException;
import java.io.File;


import java.io.FileOutputStream;

public class WriteToFile extends AppCompatActivity {

    public WriteToFile() {

    }

    public void saveFile(String text) {

        String filename = "myfile";

        try {
            FileOutputStream os = openFileOutput(filename, MODE_PRIVATE);
            os.write(text.getBytes());
            os.close();

            Toast.makeText(getBaseContext(),"Saved file sucessful",Toast.LENGTH_SHORT).show();

         } catch (IOException e) {
        e.printStackTrace();
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

/*  fop = openFileOutput(data,MODE_PRIVATE);
            fop.write(data.getBytes());
            fop.flush();
            fop.close();
            Toast.makeText(getBaseContext(),"Saved file sucessful",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
         }
*/
