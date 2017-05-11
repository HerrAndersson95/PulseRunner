package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-05-03.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.media.MediaPlayer;

public class NewTest extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    TextView textv;
    EditText textData;
    EditText textFilename;
    Button save;
    Button load;
    SharedPreferences sp ;
    FileManager  fm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        textv = (TextView)findViewById(R.id.read);
            fm = new FileManager(this);

        if(getFilesDir().list().length == 0) {
            /* No run **/
            textv.setText("You have to start a track");
            mediaPlayer = MediaPlayer.create(this,R.raw.fail1);
            mediaPlayer.start();
        }else {
            /* Winning music  **/
            textv.setText(" Your best track : " + fm.DisplayNameOfRoute()+ " \n speed:" + fm.DisplaySpeed());
            mediaPlayer = MediaPlayer.create(this,R.raw.win);
            mediaPlayer.start();
        }
    }
}
