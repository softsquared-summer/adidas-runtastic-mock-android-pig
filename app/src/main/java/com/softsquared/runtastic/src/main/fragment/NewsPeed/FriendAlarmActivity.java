package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveFriendItem;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveListAdapter;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityView;

import java.util.ArrayList;

public class FriendAlarmActivity extends BaseActivity implements FriendAlarmActivityView {
    ListView mLvReceiveLIst;
    ArrayList<ReceiveFriendItem> mReceiveArray = new ArrayList<>();
    ReceiveListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_alarm);
        showProgressDialog();
        final FriendAlarmActivityService service = new FriendAlarmActivityService(this);
        service.getReceiveList();


    }

    public void customOnClickInFriendAlarm(View v) {
        switch (v.getId()) {
            case R.id.alarm_friends_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
        Log.e("[Log.e] validateSuccess",message);
    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
    }

    @Override
    public void getArrayReceive(ArrayList<ReceiveFriendItem> result) {
        for(int i = 0 ; i < result.size(); i++) {
            mReceiveArray.add(result.get(i));
        }
        mLvReceiveLIst = findViewById(R.id.alarm_friends_list_request);
        adapter = new ReceiveListAdapter(mReceiveArray,getApplicationContext(),R.layout.friend_alarm_list_item);
        mLvReceiveLIst.setAdapter(adapter);
        hideProgressDialog();
    }
}
