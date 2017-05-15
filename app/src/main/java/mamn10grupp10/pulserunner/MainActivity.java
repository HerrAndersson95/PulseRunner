package mamn10grupp10.pulserunner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    //Variable for permission check
    protected static final int MY_PERMISSIONS_REQUEST_GPS_FINE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
        fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
        //sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();

    }

    public void onClickRun(View v){
        //Check permissions
        if (hasPermissions()){
            Intent intent = new Intent(this, RunActivity.class);
            startActivity(intent);
        }
        else {
            requestPerms();
        }
    }

    public void onClickNewTrack(View v){
        if (hasPermissions()){
            Intent intent = new Intent(this, NewTrackActivity.class);
            startActivity(intent);
        }
        else {
            requestPerms();
        }
    }

    public void onClickStatistics(View v){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    //PERMISSION CHECK START TO FIN
    private boolean hasPermissions(){
        int res = 0;
        //string array of permissions,
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

        for (String perms : permissions){
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                System.out.println("PERM NOT GRANTED GRANTED");
                return false;
            }
        }
        System.out.println("PERM ALREADY GRANTED GRANTED");
        return true;
    }
    private void requestPerms(){
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions,MY_PERMISSIONS_REQUEST_GPS_FINE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GPS_FINE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("OK PERMISSONS GRANTED");
                } else {
                    System.out.println("No Permission given");
                }
            }
        }
    }
    //PERMISSION CHECK FIN

}
