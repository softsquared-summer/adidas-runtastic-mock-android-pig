package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.MainActivity;

public class AddShoesCompleteActivity extends BaseActivity {

    String mModelName,mBrandName,mImageUrl;
    TextView mInfo;
    Button mBtnComplete;
    ImageView mIvShoesImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes_complete);
        mInfo = findViewById(R.id.add_complete_tv_name);
        mIvShoesImg = findViewById(R.id.add_complete_step_shoes_img);

        Intent sneakersInfo = getIntent();
        mModelName = sneakersInfo.getStringExtra("modelName");
        mBrandName = sneakersInfo.getStringExtra("brandName");
        mImageUrl = sneakersInfo.getStringExtra("shoesImage");

        Glide.with(getApplicationContext()).load(mImageUrl).into(mIvShoesImg);
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
