package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-04-05.
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class WriteToFile  extends AppCompatActivity {
    private Context mContext;


    public void saveFile(String file) {
        try {

       FileOutputStream fos = openFileOutput(file,Context.MODE_PRIVATE);
        OutputStreamWriter osw= new OutputStreamWriter(fos);
           osw.write(file);
            osw.flush();
            fos.close();
            Toast.makeText(getBaseContext(),"Data saved", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();

            }
        }

    /*Input the trackName, the runnerName and the arraylist of diffs (meters)
    * Creates StringBuillder and returns a strin with seperation of "\n", new line.*/
    public String createStringFile(String trackName, String runnerName, ArrayList<Double> listOfDiffs){
        StringBuilder sb = new StringBuilder();
        sb.append(trackName+"\n");
        sb.append(runnerName+"\n");
        for(double value : listOfDiffs) {
            sb.append(value+"\n");
        }
        return sb.toString();
    }
}




