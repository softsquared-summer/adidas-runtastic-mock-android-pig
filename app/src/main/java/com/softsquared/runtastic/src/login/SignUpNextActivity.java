package com.softsquared.runtastic.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.adapter.SignUpExpandableAdapter;
import com.softsquared.runtastic.src.login.adapter.SignUpParentItem;
import com.softsquared.runtastic.src.login.interfaces.SignUpNextActivityView;
import com.softsquared.runtastic.src.login.models.Goal;
import com.softsquared.runtastic.src.login.models.SetBodyRequest;
import com.softsquared.runtastic.src.login.models.SetGoalRequest;

import java.util.ArrayList;

public class SignUpNextActivity extends BaseActivity implements SignUpNextActivityView{
    ExpandableListView mElv;
    ArrayList<SignUpParentItem> mParentList = new ArrayList<>();
    SignUpExpandableAdapter adapter;
    TextView mTvName;
    Button mBtnSetGoal;
    EditText mEtHeight, mEtWeight;


    int REQUEST_CODE = 1;

    Goal mGoal;

    int mUserNo;
    String mFname;

    String mStringGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_next);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        mFname = getIntent().getStringExtra("name");
        mUserNo = getIntent().getIntExtra("userNo",0);
        Log.e("[Log.e] userNo is : " ,mUserNo + "");

        mTvName = findViewById(R.id.sign_up_next_tv_name);
        mTvName.setText(mFname + getString(R.string.sign_welcome));

        mElv = findViewById(R.id.sign_up_next_elv_list);

        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_cal_kcal)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_permit)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle,getString(R.string.sign_goal)));
        mParentList.get(0).setV(inflater.inflate(R.layout.sign_up_next_child_item,null));
        mParentList.get(1).setV(inflater.inflate(R.layout.sign_up_next_child_item_permit,null));
        mParentList.get(2).setV(inflater.inflate(R.layout.sign_up_next_child_item_goal,null));

        mBtnSetGoal = mParentList.get(2).getV().findViewById(R.id.sign_up_next_child_goal_btn_set_goal);
        mEtHeight = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_et_height);
        mEtWeight = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_et_weight);

        adapter = new SignUpExpandableAdapter(getApplicationContext(),mParentList);

        mElv.setAdapter(adapter);
        setButtonTools();
        mElv.expandGroup(0,false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                mGoal = (Goal) data.getSerializableExtra("goal");
                Log.d("response goal : ",mGoal.getExerciseType() + " " + mGoal.getTermType() + " " + mGoal.getTermValue() + " " + mGoal.getMeasureType() + " " + mGoal.getMeasureValue());
                if(mGoal.getExerciseType() == 1) {
                    if(mGoal.getMeasureType() == 1){
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " km";
                    } else if(mGoal.getMeasureType() == 2) {
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if(mGoal.getExerciseType() == 2) {
                    if(mGoal.getMeasureType() == 1){
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " km";
                    } else if(mGoal.getMeasureType() == 2) {
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if(mGoal.getExerciseType() == 3) {
                    if(mGoal.getMeasureType() == 1){
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " km";
                    } else if(mGoal.getMeasureType() == 2) {
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if(mGoal.getExerciseType() == 4) {
                    if(mGoal.getMeasureType() == 1){
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " km";
                    } else if(mGoal.getMeasureType() == 2) {
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " 회";
                    }
                } else {
                    if(mGoal.getMeasureType() == 1){
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " km";
                    } else if(mGoal.getMeasureType() == 2) {
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " 회";
                    }
                }
                mBtnSetGoal.setText(mStringGoal);
        }
    }

    public void setButtonTools(){
        mElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int groupCount = adapter.getGroupCount();
                // 한 그룹이 열리면 나머지 그룹들은 닫힌다.
                for (int i = 0; i < groupCount; i++) {
                    if (!(i == groupPosition)) {
                        mElv.collapseGroup(i);
                    }
                }
                if(groupPosition == 0){
                    Button contBtn = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_btn_continue);
                    contBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mElv.collapseGroup(0);
                            mElv.expandGroup(1,true);
                        }
                    });
                } else if(groupPosition == 1) {
                    Button contBtn = mParentList.get(1).getV().findViewById(R.id.sign_up_next_child_permit_btn_continue);
                    contBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mElv.collapseGroup(1);
                            mElv.expandGroup(2,true);
                        }
                    });
                } else if(groupPosition == 2){
                    Button contBtn = mParentList.get(2).getV().findViewById(R.id.sign_up_next_child_goal_btn_continue);
                    contBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            redirectMainActivity();
                        }
                    });
                    Button btnSetGoal = mParentList.get(2).getV().findViewById(R.id.sign_up_next_child_goal_btn_set_goal);
                    btnSetGoal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            redirectSetGoalActivity();
                        }
                    });
                }
            }
        });
    }

    public void redirectMainActivity(){
        tryPostBody();
        tryPostGoal();
    }

    public void redirectSetGoalActivity() {
        Intent intent = new Intent(this, SetGoalActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    public void tryPostBody() {
        showProgressDialog();
        String height = mEtHeight.getText().toString();
        String weight = mEtWeight.getText().toString();

        Log.e("[Log.e] height,weight: ",height + ", " + weight);

        SetBodyRequest bodyRequest = new SetBodyRequest(mUserNo,height,1,weight,1);
        final SignUpNextService signUpNextService = new SignUpNextService(this);

        signUpNextService.tryPostSetBody(bodyRequest);
    }

    public void tryPostGoal() {
        showProgressDialog();
        SetGoalRequest goalRequest = new SetGoalRequest(mUserNo,mGoal.getExerciseType(),mGoal.getTermType(),mGoal.getTermValue(),mGoal.getMeasureType(),mGoal.getMeasureValue());
        final SignUpNextService signUpNextService = new SignUpNextService(this);

        signUpNextService.tryPostSetGoal(goalRequest);
    }

    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        Log.e("[Log.e] validateSuccess","message : " + text);
        Log.e("[Log.e] validateSuccess","code : " + code);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
