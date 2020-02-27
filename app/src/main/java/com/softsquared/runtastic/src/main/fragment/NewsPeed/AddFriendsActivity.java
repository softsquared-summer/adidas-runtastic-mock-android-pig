package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.FriendsListAdapter;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.FriendsListItem;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.AddFriendsActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.services.AddFriendsActivityService;

import java.util.ArrayList;

public class AddFriendsActivity extends BaseActivity implements AddFriendsActivityView {
    private int REQUEST_OK = 1;
    LinearLayout mLayoutNoCount,mLayoutYesCount;
    ListView mListViewFriends;
    FriendsListAdapter adapter;

    ArrayList<FriendsListItem> mFriendsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        showProgressDialog();
        mLayoutNoCount = findViewById(R.id.add_friends_ll_no_count);
        mLayoutYesCount = findViewById(R.id.add_friends_ll_yes_count);
        mListViewFriends = findViewById(R.id.add_friends_lv_list_friends);

        final AddFriendsActivityService service = new AddFriendsActivityService(this);
        service.getFriendsList();
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

    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
    }

    @Override
    public void pasteFriendsList(ArrayList<FriendsListItem> result) {
        mFriendsArray = result;
        adapter = new FriendsListAdapter(mFriendsArray,getApplicationContext(),R.layout.friends_list_item);
        mListViewFriends.setAdapter(adapter);
        mLayoutYesCount.setVisibility(View.VISIBLE);
        mLayoutNoCount.setVisibility(View.GONE);
        hideProgressDialog();
    }

    @Override
    public void noCountFriend() {
        mLayoutNoCount.setVisibility(View.VISIBLE);
        mLayoutYesCount.setVisibility(View.GONE);
        hideProgressDialog();
    }
}
