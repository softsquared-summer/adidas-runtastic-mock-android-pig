package com.softsquared.runtastic.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.interfaces.LoginActivityView;
import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    EditText mEtEmail, mEtPassword;
    Button mBtnLogin;
    String mEmail, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mActList.add(this);

        mEtEmail = findViewById(R.id.login_et_email);
        mEtPassword = findViewById(R.id.login_et_password);

    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_back:
                finish();
                break;
            case R.id.login_btn_login:
                redirectMainActivity();
                break;
            default:
                break;
        }
    }

    private void redirectMainActivity(){
        mEmail = mEtEmail.getText().toString();
        mPassword = mEtPassword.getText().toString();

        LoginRequest request = new LoginRequest(mEmail,mPassword);

        tryLogin(request);
    }

    private void tryLogin(LoginRequest loginRequest){
        showProgressDialog();
        final LoginService loginService = new LoginService(this);
        loginService.postLogin(loginRequest);
    }

    @Override
    public void validateSuccess(String text) {
        Log.e("[Log.e] validateSuccess","message : " + text);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void validateCode(int code) {
        Log.e("[Log.e] validateCode","code : " + code);
        if(code == 100) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            activityFinish();
        } else if(code == 200) {
            showCustomToast("회원 정보를 확인해주세요");
        }
    }
}
