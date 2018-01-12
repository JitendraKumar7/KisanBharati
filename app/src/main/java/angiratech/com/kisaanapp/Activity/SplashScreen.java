package angiratech.com.kisaanapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.angiratech.kisaanapp.R;

import angiratech.com.kisaanapp.Session.MySession;
import angiratech.com.kisaanapp.Utility.PermissionHelper;

/**
 * Created by SONY on 30-04-2017.
 */

public class SplashScreen extends Activity {
    private static final int SPLASH_DISPLAY_TIME = 3000;
    private MySession mySession;
    private ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image_view = (ImageView) findViewById(R.id.image_view);
        PermissionHelper.DialogueShow(SplashScreen.this, "Grant Permission Access");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                String UserId=mySession.getUserId();
//                String mobile=mySession.getMobile();
                checkLogedInOrNot();

            }
        }, SPLASH_DISPLAY_TIME);
    }

    private void checkLogedInOrNot() {
        if (mySession.getActiveInstance(SplashScreen.this).getLogInStatus()) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            SplashScreen.this.finish();

        } else {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            SplashScreen.this.finish();


        }
    }
}
