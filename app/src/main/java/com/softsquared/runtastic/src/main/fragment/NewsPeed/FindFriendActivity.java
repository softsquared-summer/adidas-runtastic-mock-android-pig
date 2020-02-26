package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class FindFriendActivity extends BaseActivity {
    EditText mEtFindEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        mEtFindEmail = findViewById(R.id.find_friends_et_find_email);

        setEditTextListener();
    }

    public void customOnClickInFindFriend(View v) {
        switch (v.getId()) {
            case R.id.find_friends_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void setEditTextListener() {
        mEtFindEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE :
                        showCustomToast("email input done");
                        return false;
                    default:
                        return false;
                }
            }
        });
    }
}
