package com.softsquared.template.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.login.SignUpActivity;
import com.softsquared.template.src.login.StartActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splash();
    }

    private void splash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
