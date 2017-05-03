package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-05-03.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.View;


import java.io.IOException;
import java.io.InputStream;
import java.lang.String;


   public class NewTest extends AppCompatActivity {

       TextView textv;
       EditText txt;
       Button save;
       Button load;


       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_new_test);
           txt = (EditText) findViewById(R.id.write);
           textv = (TextView) findViewById(R.id.read);
           save = (Button) findViewById(R.id.save);
           load = (Button) findViewById(R.id.load);


       }

       public void writeFile(View view) {

       }

       public void readFile(View view) {
           String txt;
           try {
               InputStream is = getAssets().open("distance.txt");
               byte[] buffer = new byte[is.available()];
               is.read();
               is.close();
               txt = new String(buffer);
                    textv.setText(txt);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }