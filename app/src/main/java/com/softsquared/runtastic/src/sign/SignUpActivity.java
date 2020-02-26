package com.softsquared.runtastic.src.sign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputLayout;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.sign.interfaces.SignUpActivityView;
import com.softsquared.runtastic.src.sign.models.SignUpRequest;
import com.softsquared.runtastic.src.login.sub.TOSActivity;

import static com.softsquared.runtastic.src.ApplicationClass.X_ACCESS_TOKEN;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    EditText mEtFirstName, mEtLastName, mEtEmail, mEtPassword, mEtBirth;
    TextInputLayout mInputEmail, mInputFName, mInputLName, mInputPassword, mInputBirth;
    RadioButton mMan, mGirl;
    String mFirstName, mLastName, mEmail, mPassword, mProfileImage;
    int mBirth, mSex;
    boolean mEmailError , mSuccessSignUp;
    String mJwtToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSuccessSignUp = false;

        mInputEmail = findViewById(R.id.sign_up_input_email);
        mInputBirth = findViewById(R.id.sign_up_input_birth);
        mInputFName = findViewById(R.id.sign_up_input_first_name);
        mInputLName = findViewById(R.id.sign_up_input_last_name);
        mInputPassword = findViewById(R.id.sign_up_input_password);


        mEtFirstName = mInputFName.getEditText();
        mEtLastName = mInputLName.getEditText();
        mEtEmail = mInputEmail.getEditText();
        mEtPassword = mInputPassword.getEditText();
        mEtBirth = mInputBirth.getEditText();
        mMan = findViewById(R.id.sign_up_rb_man);
        mGirl = findViewById(R.id.sign_up_rb_girl);

        setEditTextError();
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn_profile_img:
                break;
            case R.id.sign_up_btn_join_top:
                redirectTOSActivity();
                break;
            case R.id.sign_up_btn_join_bottom:
                redirectTOSActivity();
                break;
            case R.id.sign_up_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void trySignUp(SignUpRequest signUpRequest){
        showProgressDialog();
        final SignUpService signUpService = new SignUpService(this);
        signUpService.postSignUp(signUpRequest);
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
    public void validateCode(int code, int userNo) {
        Log.e("[Log.e] validateCode","code : " + code);

        if(code == 100) { // 회원가입 성공
            mSuccessSignUp = true;
            getJwtToken(mEmail,mPassword);

            Intent intent = new Intent(getApplicationContext(), TOSActivity.class);
            intent.putExtra("name" , mFirstName);
            intent.putExtra("userNo",userNo);
            startActivity(intent);
        } else if(code == 200) { // 이메일 중복 코드
            mInputEmail.setError("중복된 이메일입니다.");
            mInputEmail.setErrorTextColor(getColorStateList(R.color.colorDanger));
        } else if(code == 202) { // 비밀번호 형식이 다름
            mInputPassword.setError("소문자, 대문자, 숫자를 1개 이상 입력하세요.");
            mInputPassword.setErrorTextColor(getColorStateList(R.color.colorDanger));
        } else if(code == 201) { // 올바르지 않은 이메일 형식
            mInputEmail.setError("올바르지 않은 이메일 형식입니다.");
            mInputEmail.setErrorTextColor(getColorStateList(R.color.colorDanger));
        }
    }

    public void getJwtToken(String email, String password){
        if(mSuccessSignUp){
            final SignUpService signUpService = new SignUpService(this);
            LoginRequest loginRequest = new LoginRequest(email,password);
            signUpService.postLogin(loginRequest);
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

    private void redirectTOSActivity(){
        mFirstName = mEtFirstName.getText().toString();
        mLastName = mEtLastName.getText().toString();
        mEmail = mEtEmail.getText().toString();
        mPassword = mEtPassword.getText().toString();
        mProfileImage = "";

        if(mEtBirth.getText().toString().equals("")){
            mBirth = 0;
        } else {
            mBirth = Integer.parseInt(mEtBirth.getText().toString());
        }

        if(mMan.isChecked()){
            mSex = 1;
        }
        if(mGirl.isChecked()){
            mSex = 2;
        }

        if(mFirstName.length() == 0 || mLastName.length() == 0 || mEtBirth.toString().length() == 0) {
            if (mFirstName.length() == 0) {
                mInputFName.setErrorEnabled(true);
                mInputFName.setError("1234");
                mInputFName.setBackgroundTintList(getColorStateList(R.color.colorDanger));
                if (mInputFName.getChildCount() == 2) {
                    mInputFName.getChildAt(1).setVisibility(View.GONE);
                }
            }
            if (mLastName.length() == 0) {
                mInputLName.setErrorEnabled(true);
                mInputLName.setError("1234");
                mInputLName.setBackgroundTintList(getColorStateList(R.color.colorDanger));
                if (mInputLName.getChildCount() == 2) {
                    mInputLName.getChildAt(1).setVisibility(View.GONE);
                }
            }
            if (mEtBirth.toString().length() == 0) {
                mEtBirth.setBackgroundTintList(getColorStateList(R.color.colorDanger));
            }
            if (mEmail.length() == 0) {
                mInputEmail.setError(getString(R.string.email_error));
                mInputEmail.setErrorTextColor(getColorStateList(R.color.colorDanger));
            }
            if (mPassword.length() == 0) {
                mInputPassword.setError(getString(R.string.password_error));
                mInputPassword.setErrorTextColor(getColorStateList(R.color.colorDanger));
            }
            return;
        }
        SignUpRequest signUp = new SignUpRequest(mLastName, mFirstName,mSex,mEmail, mPassword,mBirth,mProfileImage);
        trySignUp(signUp);
    }

    private void setEditTextError(){
        mEmailError = true;
        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().contains("@")){ // regex 정규식
                    mInputEmail.setError(getString(R.string.email_error));
                    mInputEmail.setErrorTextColor(getColorStateList(R.color.colorDanger));
                    mEmailError = true;
                } else {
                    mInputEmail.setError(null);
                    mEmailError = false;
                }

            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() < 8){
                    mInputPassword.setError(getString(R.string.password_error));

                    mInputPassword.setErrorTextColor(getColorStateList(R.color.colorDanger));
                } else {
                    mInputPassword.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mEtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                //Log.e("mEtFirstName","[afterTextChanged] : " + s.toString());
                if(s.toString().length() > 0){
                    mInputFName.setError("");
                    mInputFName.setErrorTextColor(getColorStateList(R.color.colorGray));
                } else {
                    mInputFName.setError(null);
                }
            }
        });
        mEtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0){
                    mInputLName.setError("");
                    mInputLName.setErrorTextColor(getColorStateList(R.color.colorGray));
                } else {
                    mInputLName.setError(null);
                }
            }
        });

    }
}
