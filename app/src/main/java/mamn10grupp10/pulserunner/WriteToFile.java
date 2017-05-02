package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-04-05.
 */

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;

import java.io.FileOutputStream;

public class WriteToFile extends AppCompatActivity {
    File file;
    FileOutputStream fop = null;
    public void saveFile(String nameofrun) {
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

