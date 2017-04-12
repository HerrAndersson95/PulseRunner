package mamn10grupp10.pulserunner;

import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * Created by annelinegjersem on 2017-04-05.
 */

public class ReadFromFile  extends AppCompatActivity {

    public String readfilefromAssist(String file) throws IOException {
        BufferedReader reader= null;
        StringBuilder sb= new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            String line;
            while((line = reader.readLine())!= null) {
                    sb.append(line).append("\n");
                }
            return sb.toString();

        }catch (IOException e) {
            e.printStackTrace();

        }finally {
            reader.close();
        }
        return null;
    }



}
