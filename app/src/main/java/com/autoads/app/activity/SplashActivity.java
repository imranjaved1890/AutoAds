package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.autoads.app.R;
import com.autoads.app.constants.AppConstants;
import com.autoads.app.preferences.MyPreferences;


public class SplashActivity extends AppCompatActivity {
    private Handler handler;

    private Runnable runnable;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myPreferences = new MyPreferences(this);


        handler = new Handler();
        makeDelay();
    }

    private void makeDelay() {
        runnable = new Runnable() {
            @Override
            public void run() {

                Intent intent = null;

                if (myPreferences.isLoggedIn()) {
                    intent = new Intent(SplashActivity.this, DashboardActivity.class);
                } else {
                    if(myPreferences.isMobileVerified()){
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }else {
                        intent = new Intent(SplashActivity.this, VerifyMobileActivity.class);
                    }

                }

                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, AppConstants.SPLAH_TIMEOUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

}

