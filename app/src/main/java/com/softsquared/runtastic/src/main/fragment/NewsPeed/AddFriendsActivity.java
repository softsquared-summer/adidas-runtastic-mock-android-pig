package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.runtastic.R;

public class AddFriendsActivity extends AppCompatActivity {
    private int REQUEST_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
    }

    public void customOnClickInAddFriends(View v) {
        switch (v.getId()) {
            case R.id.add_friends_btn_complete:
                finish();
                break;
            case R.id.add_friends_btn_bottom_find:
                moveFIndFriendActivity();
                break;
            case R.id.add_friends_btn_top_find:
                moveFIndFriendActivity();
                break;
            default:
                break;
        }
    }

    private void moveFIndFriendActivity() {
        Intent intent = new Intent(this,FindFriendActivity.class);
        startActivityForResult(intent,REQUEST_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
