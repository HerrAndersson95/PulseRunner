package mamn10grupp10.pulserunner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RunActivityPause extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_pause);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
