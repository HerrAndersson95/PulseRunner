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
    }



