package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.serviingo.serviingo.Common.CustomLoader;
import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends BaseActivity {
    ImageView img;
    Timer timer = new Timer();
    CustomLoader loader;
    Preferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        pref = new Preferences(mActivity);


        init();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveToLogin();
               // moveToDashBoard();
               /* if(pref.get(AppSettings.loginCheck).equals("true")){
                    moveToDashBoard();
                }else{
                    moveToLogin();
                }*/
            }

            private void moveToLogin() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            }

            private void moveToDashBoard() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        }, 3000);
    }

    private void init() {
        img = findViewById(R.id.ivSplash);
    }
}
