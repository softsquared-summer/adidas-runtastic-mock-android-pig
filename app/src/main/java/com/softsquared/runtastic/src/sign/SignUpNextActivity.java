package com.softsquared.runtastic.src.sign;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.MainActivity;
import com.softsquared.runtastic.src.sign.adapter.SignUpExpandableAdapter;
import com.softsquared.runtastic.src.sign.adapter.SignUpParentItem;
import com.softsquared.runtastic.src.sign.interfaces.SignUpNextActivityView;
import com.softsquared.runtastic.src.sign.models.Goal;
import com.softsquared.runtastic.src.sign.models.SetBodyRequest;
import com.softsquared.runtastic.src.sign.models.SetGoalRequest;

import java.util.ArrayList;

public class SignUpNextActivity extends BaseActivity implements SignUpNextActivityView {
    ExpandableListView mElv;
    ArrayList<SignUpParentItem> mParentList = new ArrayList<>();
    SignUpExpandableAdapter adapter;
    TextView mTvName;
    Button mBtnSetGoal;
    Button mBtnHeight, mBtnWeight;


    int REQUEST_CODE = 1;
    int mHeight = 183;
    int mWeight = 75;
    Boolean mBodySuccess = false;
    Boolean mGoalSuccess = false;

    Goal mGoal = new Goal(1, 1, 1, "1", "1");

    int mUserNo;
    String mFirstName;

    String mStringGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_next);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        mFirstName = getIntent().getStringExtra("name");
        mUserNo = getIntent().getIntExtra("userNo", 0);
        Log.e("[Log.e] userNo is : ", mUserNo + "");

        mTvName = findViewById(R.id.sign_up_next_tv_name);
        mTvName.setText(mFirstName + getString(R.string.sign_welcome));

        mElv = findViewById(R.id.sign_up_next_elv_list);

        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle, getString(R.string.sign_cal_kcal)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle, getString(R.string.sign_permit)));
        mParentList.add(new SignUpParentItem(R.drawable.sign_up_circle, getString(R.string.sign_goal)));
        mParentList.get(0).setV(inflater.inflate(R.layout.sign_up_next_child_item, null));
        mParentList.get(1).setV(inflater.inflate(R.layout.sign_up_next_child_item_permit, null));
        mParentList.get(2).setV(inflater.inflate(R.layout.sign_up_next_child_item_goal, null));

        mBtnSetGoal = mParentList.get(2).getV().findViewById(R.id.sign_up_next_child_goal_btn_set_goal);
        mBtnHeight = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_btn_height);
        mBtnWeight = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_btn_weight);

        adapter = new SignUpExpandableAdapter(getApplicationContext(), mParentList);

        mElv.setAdapter(adapter);
        setButtonTools();
        mElv.expandGroup(0, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                mGoal = (Goal) data.getSerializableExtra("goal");
                Log.d("response goal : ", mGoal.getExerciseType() + " " + mGoal.getTermType() + " " + mGoal.getTermValue() + " " + mGoal.getMeasureType() + " " + mGoal.getMeasureValue());
                if (mGoal.getExerciseType() == 1) {
                    if (mGoal.getMeasureType() == 1) {
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " km";
                    } else if (mGoal.getMeasureType() == 2) {
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "러닝: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if (mGoal.getExerciseType() == 2) {
                    if (mGoal.getMeasureType() == 1) {
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " km";
                    } else if (mGoal.getMeasureType() == 2) {
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "걷기: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if (mGoal.getExerciseType() == 3) {
                    if (mGoal.getMeasureType() == 1) {
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " km";
                    } else if (mGoal.getMeasureType() == 2) {
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "하이킹: " + mGoal.getMeasureValue() + " 회";
                    }
                } else if (mGoal.getExerciseType() == 4) {
                    if (mGoal.getMeasureType() == 1) {
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " km";
                    } else if (mGoal.getMeasureType() == 2) {
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "싸이클링: " + mGoal.getMeasureValue() + " 회";
                    }
                } else {
                    if (mGoal.getMeasureType() == 1) {
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " km";
                    } else if (mGoal.getMeasureType() == 2) {
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " 시간";
                    } else {
                        mStringGoal = "운동: " + mGoal.getMeasureValue() + " 회";
                    }
                }
                mBtnSetGoal.setText(mStringGoal);
        }
    }

    public void setButtonTools() {
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
                if (groupPosition == 0) {
                    Button contBtn = mParentList.get(0).getV().findViewById(R.id.sign_up_next_child_btn_continue);
                    contBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mElv.collapseGroup(0);
                            mElv.expandGroup(1, true);
                        }
                    });
                } else if (groupPosition == 1) {
                    Button contBtn = mParentList.get(1).getV().findViewById(R.id.sign_up_next_child_permit_btn_continue);
                    contBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mElv.collapseGroup(1);
                            mElv.expandGroup(2, true);
                        }
                    });
                } else if (groupPosition == 2) {
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

        mBtnHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHeightDialog();
            }
        });

        mBtnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDialog();
            }
        });
    }

    public void openHeightDialog(){
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("신장 (cm)");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(230);
        numberPicker.setMinValue(140);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mHeight = numberPicker.getValue();
                mBtnHeight.setText(mHeight + "cm");
            }
        });
        d.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();
    }

    public void openWeightDialog() {
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("체중 (kg)");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(150);
        numberPicker.setMinValue(40);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mWeight = numberPicker.getValue();
                mBtnWeight.setText(mWeight + "kg");
            }
        });
        d.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();
    }


    public void redirectMainActivity() {
        tryPostBody();
        tryPostGoal();

    }

    public void redirectSetGoalActivity() {
        Intent intent = new Intent(this, SetGoalActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void tryPostBody() {
        showProgressDialog();
        String height = mHeight + " ";
        String weight = mWeight + " ";

        Log.e("[Log.e] height,weight: ", height + ", " + weight);

        SetBodyRequest bodyRequest = new SetBodyRequest(mUserNo, height, 1, weight, 1);
        final SignUpNextService signUpNextService = new SignUpNextService(this);

        signUpNextService.tryPostSetBody(bodyRequest);
    }

    public void tryPostGoal() {
        showProgressDialog();
        SetGoalRequest goalRequest = new SetGoalRequest(mUserNo, mGoal.getExerciseType(), mGoal.getTermType(), mGoal.getTermValue(), mGoal.getMeasureType(), mGoal.getMeasureValue());
        final SignUpNextService signUpNextService = new SignUpNextService(this);

        signUpNextService.tryPostSetGoal(goalRequest);
    }

    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        Log.e("[Log.e] validateSuccess", "message : " + text);
        Log.e("[Log.e] validateSuccess", "code : " + code);

    }

    @Override
    public void setBodySuccess(String text) {
        Log.e("[Log.e] setBodySuccess", "message : " + text);
        mBodySuccess = true;
    }

    @Override
    public void setGoalSuccess(String text) {
        Log.e("[Log.e] setGoalSuccess", "message : " + text);
        mGoalSuccess = true;
        if(mGoalSuccess) { // 추후에 수정
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
