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
            textv.setText(" Highest speed in route: " + fm.DisplayNameOfRoute()+ "\n with speed" +
                    fm.DisplaySpeed() +"m/s " +"Longest distance in route  "+
                    fm.DisplayNameofRouteDistance() + "\n with the distance" +fm.DisplayTotalDistance() +"m");

            mediaPlayer = MediaPlayer.create(this,R.raw.win1);
            mediaPlayer.start();
        }
    }
    public void TestSave(View v) {
        Intent intent = new Intent(this, NewTest.class);
        startActivity(intent);
    }
}

