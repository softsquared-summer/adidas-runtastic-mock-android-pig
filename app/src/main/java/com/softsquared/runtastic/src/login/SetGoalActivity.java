package com.softsquared.runtastic.src.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class SetGoalActivity extends BaseActivity implements View.OnClickListener {
    RadioButton[] mExercise = new RadioButton[5];
    RadioButton[] mGoalTime = new RadioButton[5];
    RadioButton[] mUnit = new RadioButton[3];

    DateFormat mDateAndTime = DateFormat.getDateTimeInstance();
    Calendar mSelectDate;
    DatePickerDialog.OnDateSetListener dateSetListener;
    DatePickerDialog mOpenSetDateDialog;

    String mAssignDate;
    String mShowDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        initVIew();
        mSelectDate = Calendar.getInstance();
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
        } else if(id == R.id.set_goal_rbt_day || id == R.id.set_goal_rbt_week || id == R.id.set_goal_rbt_month || id == R.id.set_goal_rbt_year || id == R.id.set_goal_rbt_assign) {
            int pos = Integer.parseInt(v.getContentDescription().toString());
            for (int i = 0; i < 5; i++) {
                if (pos == i) mGoalTime[i].setChecked(true);
                else mGoalTime[i].setChecked(false);
            }
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
        }
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
