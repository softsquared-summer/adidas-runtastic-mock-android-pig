package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.runtastic.R;

public class AddFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
    }

    public void customOnClickInAddFriends (View v){
        switch (v.getId()) {
            case R.id.add_friends_btn_complete:
                finish();
                break;
            case R.id.add_friends_btn_bottom_find:
                break;
            case R.id.add_friends_btn_top_find:
                break;
            default:
                break;
        }

    }
}
