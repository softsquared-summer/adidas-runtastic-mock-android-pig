package com.softsquared.runtastic.src.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.softsquared.runtastic.src.ApplicationClass.X_ACCESS_TOKEN;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    EditText mEtEmail, mEtPassword;
    String mEmail, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if(code == 200) {
            showCustomToast("회원 정보를 확인해주세요");
        }
    }

    @Override
    public void putJwtToken(String jwt) {
        Log.e("[Log.e] jwt : " , jwt);
        SharedPreferences preferences = getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(X_ACCESS_TOKEN,jwt);
        editor.apply();
    }
}
