package com.softsquared.template.src.login;

import android.os.Bundle;
import android.view.View;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.login.interfaces.LoginActivityView;

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
