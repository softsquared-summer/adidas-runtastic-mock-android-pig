package com.softsquared.runtastic.src.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.NumberPickerDialog;
import com.softsquared.runtastic.src.login.models.Goal;

import java.text.DateFormat;
import java.util.Calendar;

public class SetGoalActivity extends BaseActivity implements View.OnClickListener {
    int RESULT_OK = 1;

    RadioButton[] mExercise = new RadioButton[5];
    RadioButton[] mGoalTime = new RadioButton[5];
    RadioButton[] mUnit = new RadioButton[3];
    Button mBtnBack,mBtnComplete;

    Calendar mSelectDate;
    DatePickerDialog.OnDateSetListener dateSetListener;



    // 넘겨줘야할 데이터들
    int mExerciseType,mTermType,mMeasureType;
    String mTermValue,mMeasuerValue;

    String mAssignDate;
    String mShowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        initVIew();
        mSelectDate = Calendar.getInstance();
        mExerciseType = 1;
        mTermType = 1;
        mMeasureType = 1;
        mMeasuerValue = "0";
        mTermValue = "default";
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        return super.onCreateDescription();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.set_goal_rbt_running || id == R.id.set_goal_rbt_walking || id == R.id.set_goal_rbt_hiking || id == R.id.set_goal_rbt_cycling || id == R.id.set_goal_rbt_exercise) {
            int pos = Integer.parseInt(v.getContentDescription().toString());
            for (int i = 0; i < 5; i++) {
                if (pos == i) mExercise[i].setChecked(true);
                else mExercise[i].setChecked(false);
            }
            mExerciseType = pos+1;
        } else if(id == R.id.set_goal_rbt_day || id == R.id.set_goal_rbt_week || id == R.id.set_goal_rbt_month || id == R.id.set_goal_rbt_year || id == R.id.set_goal_rbt_assign) {
            int pos = Integer.parseInt(v.getContentDescription().toString());
            for (int i = 0; i < 5; i++) {
                if (pos == i) mGoalTime[i].setChecked(true);
                else mGoalTime[i].setChecked(false);
            }
            mTermType = pos+1;
            if(id == R.id.set_goal_rbt_assign) {
                setDateListener();
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetGoalActivity.this,R.style.DateDialogTheme,dateSetListener,
                        mSelectDate.get(Calendar.YEAR),
                        mSelectDate.get(Calendar.MONTH),
                        mSelectDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(mSelectDate.getTimeInMillis());
                datePickerDialog.show();
            }
        } else if(id == R.id.set_goal_rbt_distance || id == R.id.set_goal_rbt_unit_time || id == R.id.set_goal_rbt_unit_count) {
            int pos = Integer.parseInt(v.getContentDescription().toString());
            for (int i = 0; i < 3; i++) {
                if (pos == i) mUnit[i].setChecked(true);
                else mUnit[i].setChecked(false);
            }
            mMeasureType = pos+1;
            if(id == R.id.set_goal_rbt_distance) {
                makeDistanceDialog();
            } else if(id == R.id.set_goal_rbt_unit_time) {
                makeTimeDialog();
            } else if(id == R.id.set_goal_rbt_unit_count) {
                makeCountDialog();
            }
        }
    }

    public void customOnClick(View v){
        switch (v.getId()) {
            case R.id.set_goal_btn_back:
                finish();
                break;
            case R.id.set_goal_btn_complete:
                setGoalComplete();
                break;
            default:
                break;
        }
    }

    private void setGoalComplete() {
        Intent intent = new Intent();
        Goal goal = new Goal(mExerciseType,mTermType,mMeasureType,mTermValue,mMeasuerValue);
        intent.putExtra("goal", goal);
        Log.d("post goal : ",goal.getExerciseType() + " " + goal.getTermType() + " " + goal.getTermValue() + " " + goal.getMeasureType() + " " + goal.getMeasureValue());
        setResult(RESULT_OK,intent);
        finish();
    }

    private void setDateListener(){
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mShowDate = year + "년" + month + "월" + dayOfMonth + "일";
                mAssignDate = year +""+ month+"" + dayOfMonth + "";
                showCustomToast(mAssignDate);
                mGoalTime[4].setText(mShowDate);
            }
        };

    }

    public void makeDistanceDialog(){
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("거리 설정 (km)");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("tag", "onClick: " + numberPicker.getValue());
                mMeasuerValue = numberPicker.getValue()+"";
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

    public void makeTimeDialog() {
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("시간 설정 (시간)");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("tag", "onClick: " + numberPicker.getValue());
                mMeasuerValue = numberPicker.getValue()+"";
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

    public void makeCountDialog() {
        final AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
        d.setTitle("횟수 설정 (회)");
        d.setView(dialogView);
        final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            }
        });
        d.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("tag", "onClick: " + numberPicker.getValue());
                mMeasuerValue = numberPicker.getValue()+"";
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

    private void initVIew() {
        mExercise[0] = findViewById(R.id.set_goal_rbt_running);
        mExercise[0].setOnClickListener(this);
        mExercise[1] = findViewById(R.id.set_goal_rbt_walking);
        mExercise[1].setOnClickListener(this);
        mExercise[2] = findViewById(R.id.set_goal_rbt_hiking);
        mExercise[2].setOnClickListener(this);
        mExercise[3] = findViewById(R.id.set_goal_rbt_cycling);
        mExercise[3].setOnClickListener(this);
        mExercise[4] = findViewById(R.id.set_goal_rbt_exercise);
        mExercise[4].setOnClickListener(this);

        mGoalTime[0] = findViewById(R.id.set_goal_rbt_day);
        mGoalTime[0].setOnClickListener(this);
        mGoalTime[1] = findViewById(R.id.set_goal_rbt_week);
        mGoalTime[1].setOnClickListener(this);
        mGoalTime[2] = findViewById(R.id.set_goal_rbt_month);
        mGoalTime[2].setOnClickListener(this);
        mGoalTime[3] = findViewById(R.id.set_goal_rbt_year);
        mGoalTime[3].setOnClickListener(this);
        mGoalTime[4] = findViewById(R.id.set_goal_rbt_assign);
        mGoalTime[4].setOnClickListener(this);

        mUnit[0] = findViewById(R.id.set_goal_rbt_distance);
        mUnit[0].setOnClickListener(this);
        mUnit[1] = findViewById(R.id.set_goal_rbt_unit_time);
        mUnit[1].setOnClickListener(this);
        mUnit[2] = findViewById(R.id.set_goal_rbt_unit_count);
        mUnit[2].setOnClickListener(this);
    }

}
