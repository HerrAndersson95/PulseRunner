package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickRun(View v){
        Intent intent = new Intent(this, NewTrackRunning.class);
        startActivity(intent);
    }


}
