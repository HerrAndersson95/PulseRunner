package mamn10grupp10.pulserunner;

/**
 * Created by annelinegjersem on 2017-05-03.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

import java.util.HashSet;
import java.util.Set;
import android.os.Environment;
import android.content.res.AssetManager;


public class NewTest extends AppCompatActivity {
    String path;
    TextView textv;
    EditText textData;
    EditText textFilename;
    Button save;
    Button load;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);

    }
}
      /* textData = (EditText) findViewById(R.id.textData);
        textv = (TextView) findViewById(R.id.read);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
       textFilename = (EditText)findViewById(R.id.textFilename);


    }

   /* public void writeFile(View view) {

        String filename = textFilename.getText().toString();

        try {
            FileOutputStream os = openFileOutput(filename, MODE_PRIVATE);
            os.write(textData.getText().toString().getBytes());
            os.close();
            Toast.makeText(getBaseContext(), "Saved file sucessful", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    // wf.saveFile(txt.getText().toString());
    /* String filename  =txt.getText().toString();
        //AssetManager manager= getAssets();

        try {
            FileOutputStream fos = openFileOutput("distances.txt", Context.MODE_PRIVATE);
         //   InputStream inputstream =manager.open("distances.txt");
            //byte [] input = new byte[inputstream.available()];
            //inputstream.read(input);
           // txt = new String(input) + "extra";

            fos.write(filename.getBytes());
            fos.close();
            //inputstream.close();


            Toast.makeText(getBaseContext(),"Saved file sucessful",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  FileOutputStream fop = null;
        String data = txt.getText().toString();
        try {
            File dir = new File(path + "Downloads");
            dir.mkdirs();
            File file = new File(dir, data);
            fop = new FileOutputStream(file);
            PrintWriter f = new PrintWriter(fop);
            f.write("hej");
            f.write("hejhje");
            f.close();
            fop.close();

            Toast.makeText(getBaseContext(), "Saved file sucessful", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }**/


    /*public void readFile(View view) {
        String filename = textFilename.getText().toString();
        FileInputStream fs;
        try {
            fs = openFileInput(filename);
            // byte[] buffer = new byte[is.available()];
            //fs.read(buffer);
            //is.close();
            //txt = new String(buffer);
            textv.setText(convertStreamToString(fs));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }
}

     /*     String txt;
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

 fop.write(data.getBytes()); fop.close();


             String name = sp.returnInfo();
             String name =  txt.getText().toString();
             sp.saveInfo(name,"",name);
             sp.saveInfo(txt.getText().toString(),"2 3 5 7 30");
             Toast.makeText(this,"Saved name of the route",Toast.LENGTH_LONG).show();
    **/
         //textv.setText("name of route " + name);


   /*    readFile (){

    String data = txt.getText().toString();
           FileOutputStream fop = null;
           try {
               fop = openFileOutput("distance.txt",MODE_PRIVATE);
               fop.write(data.getBytes());
               fop.close();
               Toast.makeText(getBaseContext(),"Saved file sucessful",Toast.LENGTH_SHORT).show();
           } catch (FileNotFoundException e) {
               e.printStackTrace();

           } catch (IOException e) {
               e.printStackTrace();
           }
  */

     /* writeFile(){ */
