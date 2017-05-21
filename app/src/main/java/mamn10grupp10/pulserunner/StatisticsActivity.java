package mamn10grupp10.pulserunner;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.media.MediaPlayer;

public class StatisticsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_statistics);
        textv = (TextView)findViewById(R.id.read);
        fm = new FileManager(this);

        if(getFilesDir().list().length == 0) {
            /* No run **/
            textv.setText("You have to start a new track and go for a run");
            mediaPlayer = MediaPlayer.create(this,R.raw.fail1);
            mediaPlayer.start();
        }else {
            /* Winning music  **/
                textv.setText("Latest route: " + fm.DisplayNameOfRoute()+ " \n \n   Speed: " +
                        fm.DisplaySpeed() +" km/h "+  "\n \n Distance: " +fm.DisplayTotalDistance() +" m");
            }
            mediaPlayer = MediaPlayer.create(this,R.raw.win1);
            mediaPlayer.start();
        }
}
