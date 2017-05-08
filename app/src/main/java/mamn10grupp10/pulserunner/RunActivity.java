package mamn10grupp10.pulserunner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;

public class RunActivity extends AppCompatActivity {
    ListView list;
    TextView view;
    ListAdapter adapter;
    String[] tracks;
    String selectedTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list =(ListView)findViewById(R.id.listview);
        view = (TextView)findViewById(R.id.textView11);
        tracks = getFilesDir().list();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracks);
        list.setAdapter(adapter);


        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedTrack = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(RunActivity.this, selectedTrack, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


    public void onClickStart(View v){
        Intent intent = new Intent(this, RunActivityRunning.class);
        intent.putExtra("selectedTrack",selectedTrack);
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