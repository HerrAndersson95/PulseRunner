package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRun(View v){
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }

    public void onClickNewTrack(View v){
        Intent intent = new Intent(this, NewTrackActivity.class);
        startActivity(intent);
    }

}
