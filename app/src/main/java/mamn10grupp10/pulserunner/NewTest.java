package mamn10grupp10.pulserunner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import android.content.Context;


public class NewTest extends AppCompatActivity {
    TextView textv;
    EditText txt;
    Button save;
    Button load;
    ReadFromFile rf;
    WriteToFile wf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        txt = (EditText) findViewById(R.id.write);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
        textv = (TextView) findViewById(R.id.read);


    }

    public void writeFile(View view) {
        wf = new WriteToFile();
        String data = txt.getText().toString();
        wf.saveFile(data);

    }

    public void readFile(View view) {
        String txt;
        try{
        InputStream is = getAssets().open("distance.txt");
          byte[] buffer = new byte[is.available()];
          is.read(buffer);
          is.close();
          txt = new String(buffer);
          textv.setText(txt);
         }catch(IOException e ) {
          e.printStackTrace();
        }
      }
}



