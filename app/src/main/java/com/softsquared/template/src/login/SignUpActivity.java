package com.softsquared.template.src.login;

import android.os.Bundle;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.login.interfaces.SignUpActivityView;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
