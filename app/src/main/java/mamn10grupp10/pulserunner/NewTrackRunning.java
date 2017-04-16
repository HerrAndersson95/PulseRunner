package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTrackRunning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_running);
    }

    //Stop
    public void onClickStop(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Pause
    public void onClickPause(View v){
        Intent intent = new Intent(this, NewTrackPause.class);
        startActivity(intent);
    }

    //Finish
    public void onClickFinish(View v){
        Intent intent = new Intent(this, NewTrackDone.class);
        startActivity(intent);
    }


}
