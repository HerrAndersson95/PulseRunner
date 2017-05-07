package mamn10grupp10.pulserunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RunActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list =(ListView)findViewById(R.id.listview);
        String[] tracks = getFilesDir().list();
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracks);
        list.setAdapter(adapter);
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, RunActivityRunning.class);
        startActivity(intent);
    }



    public void onClickTreadmill(View v){
        Intent intent = new Intent(this, RunActivityTreadmill.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(RunActivity.this, MainActivity.class));
    }
}