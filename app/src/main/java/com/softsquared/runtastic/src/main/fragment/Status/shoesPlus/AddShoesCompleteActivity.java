package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.MainActivity;

public class AddShoesCompleteActivity extends BaseActivity {

    String mModelName,mBrandName;
    TextView mInfo;
    Button mBtnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes_complete);
        mInfo = findViewById(R.id.add_complete_tv_name);

        Intent sneakersInfo = getIntent();
        mModelName = sneakersInfo.getStringExtra("modelName");
        mBrandName = sneakersInfo.getStringExtra("brandName");

        mInfo.setText(mBrandName + " " + mModelName + " " + getString(R.string.shoes_add_complete));

        mBtnComplete = findViewById(R.id.add_complete_btn_complete);
        mBtnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
