package com.softsquared.runtastic.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.interfaces.LoginActivityView;
import com.softsquared.runtastic.src.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mActList.add(this);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_back:
                finish();
                break;
            case R.id.login_btn_login:
                redirectMainActivity();
                finish();
                break;
            default:
                break;
        }
    }

    private void redirectMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        activityFinish();
    }

}
