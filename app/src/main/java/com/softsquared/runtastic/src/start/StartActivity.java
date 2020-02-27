package com.softsquared.runtastic.src.start;


import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.LoginActivity;
import com.softsquared.runtastic.src.sign.SignUpActivity;

public class StartActivity extends BaseActivity {
    Button mFaceBookLogin, mGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    private void redirectSingUpActivity(){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    private void redirectLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn_login_facebook:
                break;
            case R.id.start_btn_login_google:
                break;
            case R.id.start_btn_login_origin:
                redirectLoginActivity();
                break;
            case R.id.start_btn_sign_up:
                redirectSingUpActivity();
                break;
            default:
                break;
        }
    }

}
