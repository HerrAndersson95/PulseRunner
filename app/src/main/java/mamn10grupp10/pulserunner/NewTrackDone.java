package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTrackDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track_done);
    }

    //Cancel
    public void onClickCancel(View v){
        Intent intent = new Intent(this, NewTrackDone.class);
        startActivity(intent);
    }

    //Save
    public void onClickSave(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
