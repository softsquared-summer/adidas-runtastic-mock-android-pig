package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.models.Sneakers;

public class AddShoes1Step extends BaseActivity {
    RelativeLayout mBtnColorTab;
    net.cachapa.expandablelayout.ExpandableLayout mColorCard;

    TextView mColorName;
    ImageView mColorImg;
    ImageView mShoesImg;

    TextView mTvModelName, mTvBrandName;
    EditText mEtSize, mEtNickName;

    boolean mExpanded;
    String mModelName, mBrandName, mImageUrl;

    Sneakers mSneakers;

    String mNickName = " ";
    String mSizeValue = "260";
    int mSizeType = 5;
    int mColorNo = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes1_step);
        mBtnColorTab = findViewById(R.id.add_first_layout_color);
        mColorCard = findViewById(R.id.add_first_color_card);
        mColorName = findViewById(R.id.add_first_color_name);
        mColorImg = findViewById(R.id.add_first_color_img);
        mTvBrandName = findViewById(R.id.add_first_brand_name);
        mTvModelName = findViewById(R.id.add_first_model_name);
        mEtNickName = findViewById(R.id.add_first_et_nickname);
        mEtSize = findViewById(R.id.add_first_et_size);
        mShoesImg = findViewById(R.id.add_first_step_shoes_img);


        // 운동화 정보 받는부분
        Intent sneakersInfo = getIntent();
        mModelName = sneakersInfo.getStringExtra("modelName");
        mBrandName = sneakersInfo.getStringExtra("brandName");
        mImageUrl = sneakersInfo.getStringExtra("shoesImage");
        mSneakers = (Sneakers) sneakersInfo.getSerializableExtra("sneakers");

        mTvModelName.setText(mModelName);
        mTvBrandName.setText(mBrandName);
        Glide.with(getApplicationContext()).load(mImageUrl).into(mShoesImg);

        mBtnColorTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mExpanded) {
                    mColorCard.expand();
                } else {
                    mColorCard.collapse();
                }
                mExpanded = !mExpanded;
            }
        });
    }

    public void customOnClickInFirstStep (View v) {
        switch (v.getId()) {
            case R.id.add_first_color_white:
                mColorName.setText(getString(R.string.white));
                mColorImg.setImageResource(R.drawable.shoes_white);
                mColorNo = 1;
                break;
            case R.id.add_first_color_gray:
                mColorName.setText(getString(R.string.gray));
                mColorImg.setImageResource(R.drawable.shoes_gray);
                mColorNo = 2;
                break;
            case R.id.add_first_color_black:
                mColorName.setText(getString(R.string.black));
                mColorImg.setImageResource(R.drawable.shoes_black);
                mColorNo = 3;
                break;
            case R.id.add_first_color_brown:
                mColorName.setText(getString(R.string.brown));
                mColorImg.setImageResource(R.drawable.shoes_brown);
                mColorNo = 4;
                break;
            case R.id.add_first_color_pink:
                mColorName.setText(getString(R.string.pink));
                mColorImg.setImageResource(R.drawable.shoes_pink);
                mColorNo = 5;
                break;
            case R.id.add_first_color_purple:
                mColorName.setText(getString(R.string.purple));
                mColorImg.setImageResource(R.drawable.shoes_purple);
                mColorNo = 6;
                break;
            case R.id.add_first_color_blue:
                mColorName.setText(getString(R.string.blue));
                mColorImg.setImageResource(R.drawable.blue);
                mColorNo = 7;
                break;
            case R.id.add_first_color_green:
                mColorName.setText(getString(R.string.green));
                mColorImg.setImageResource(R.drawable.shoes_green);
                mColorNo = 8;
                break;
            case R.id.add_first_color_red:
                mColorName.setText(getString(R.string.red));
                mColorImg.setImageResource(R.drawable.shoes_red);
                mColorNo = 9;
                break;
            case R.id.add_first_color_orange:
                mColorName.setText(getString(R.string.orange));
                mColorImg.setImageResource(R.drawable.shoes_orange);
                mColorNo = 10;
                break;
            case R.id.add_first_color_yellow:
                mColorName.setText(getString(R.string.yellow));
                mColorImg.setImageResource(R.drawable.shoes_yellow);
                mColorNo = 11;
                break;
            case R.id.add_first_color_ex:
                mColorName.setText(getString(R.string.ex_color));
                mColorImg.setImageResource(R.drawable.shoes_ex);
                mColorNo = 12;
                break;
            case R.id.add_first_step_back:
                finish();
                overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                break;
            case R.id.add_first_step_next:
                if(mEtNickName.getText().toString().length() != 0) {
                    mNickName = mEtNickName.getText().toString();
                }
                if(mEtSize.getText().toString().length() != 0) {
                    mSizeValue = mEtSize.getText().toString();
                }

                mSneakers.setColorNo(mColorNo);
                mSneakers.setNickname(mNickName);
                mSneakers.setSizeValue(mSizeValue);
                mSneakers.setSizeType(mSizeType);

                Log.e("getModelNo", "" + mSneakers.getModelNo()); // o
                Log.e("getSizeType", "" + mSneakers.getSizeType()); // o
                Log.e("getSizeValue", "" + mSneakers.getSizeValue()); // x
                Log.e("getNickname", "" + mSneakers.getNickname()); // x
                Log.e("getColorNo", "" + mSneakers.getColorNo()); // o
                Log.e("getStartedAt", "" + mSneakers.getStartedAt()); // x
                Log.e("getLimitDistance", "" + mSneakers.getLimitDistance()); // o
                Log.e("getInitDistance", "" + mSneakers.getInitDistance()); // o

                Intent intent = new Intent(getApplicationContext(),AddShoes2Step.class);
                intent.putExtra("brandName", mBrandName);
                intent.putExtra("modelName", mModelName);
                intent.putExtra("sneakers",mSneakers);
                intent.putExtra("shoesImage",mImageUrl);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
    }
}
