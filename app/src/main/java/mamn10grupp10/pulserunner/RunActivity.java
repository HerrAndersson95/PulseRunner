package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RunActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, RunActivityRunning.class);
        startActivity(intent);
    }



    public void onClickTreadmill(View v){
        Intent intent = new Intent(this, RunActivityTreadmill.class);
        startActivity(intent);
    }
}