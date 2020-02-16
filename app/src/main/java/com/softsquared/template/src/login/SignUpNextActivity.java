package com.softsquared.template.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.login.interfaces.SignUpNextActivityView;

public class SignUpNextActivity extends BaseActivity implements SignUpNextActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_next);
    }
}
