package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RunActivityTreadmillFinish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_treadmill_finish);
    }

    public void onClickDone(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
