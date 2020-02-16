package com.softsquared.runtastic.src.login;

import android.os.Bundle;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.interfaces.SignUpNextActivityView;

public class SignUpNextActivity extends BaseActivity implements SignUpNextActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_next);
    }
}
