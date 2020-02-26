package com.softsquared.runtastic.src.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.MainActivity;
import com.softsquared.runtastic.src.start.StartActivity;

import static com.softsquared.runtastic.src.ApplicationClass.X_ACCESS_TOKEN;

public class SplashActivity extends BaseActivity {
    SharedPreferences mSharedPreferences;
    String jwtToken;
    Boolean hasToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mSharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
        jwtToken = mSharedPreferences.getString(X_ACCESS_TOKEN,null);
    //  Log.e("[Log.e] jwtToken : " , jwtToken);

        splash();
    }

    private void splash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(jwtToken == null){
                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }
}
