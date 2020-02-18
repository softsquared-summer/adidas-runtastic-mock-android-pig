package com.softsquared.runtastic.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.adapter.SignUpExpandableAdapter;
import com.softsquared.runtastic.src.login.adapter.SignUpParentItem;
import com.softsquared.runtastic.src.login.interfaces.SignUpNextActivityView;

import java.util.ArrayList;

public class SignUpNextActivity extends BaseActivity implements SignUpNextActivityView{
    ExpandableListView mElv;
    ArrayList<SignUpParentItem> mParentList = new ArrayList<>();
    SignUpExpandableAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_next);

        mElv = findViewById(R.id.sign_up_next_elv_list);

        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_cal_kcal)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_permit)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_goal)));

        adapter = new SignUpExpandableAdapter(getApplicationContext(),mParentList);

        mElv.setAdapter(adapter);
        mElv.expandGroup(0,true);


        mElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int groupCount = adapter.getGroupCount();

                // 한 그룹을 클릭하면 나머지 그룹들은 닫힌다.
                for (int i = 0; i < groupCount; i++) {
                    if (!(i == groupPosition)) {
                        mElv.collapseGroup(i);
                    }
                }
            }
        });

    }



}
