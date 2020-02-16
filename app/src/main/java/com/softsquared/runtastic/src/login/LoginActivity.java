package com.softsquared.runtastic.src.login;

import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.interfaces.LoginActivityView;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_back:
                finish();
                break;
            case R.id.login_btn_login:
                break;
            default:
                break;
        }
    }
}
