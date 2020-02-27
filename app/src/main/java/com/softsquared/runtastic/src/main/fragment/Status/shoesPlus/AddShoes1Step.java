package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class AddShoes1Step extends BaseActivity {
    RelativeLayout mBtnColorTab;
    net.cachapa.expandablelayout.ExpandableLayout mColorCard;

    TextView mColorName;
    ImageView mColorImg;

    boolean mExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes1_step);
        mBtnColorTab = findViewById(R.id.add_first_layout_color);
        mColorCard = findViewById(R.id.add_first_color_card);
        mColorName = findViewById(R.id.add_first_color_name);
        mColorImg = findViewById(R.id.add_first_color_img);

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
                break;
            case R.id.add_first_color_gray:
                mColorName.setText(getString(R.string.gray));
                mColorImg.setImageResource(R.drawable.shoes_gray);
                break;
            case R.id.add_first_color_black:
                mColorName.setText(getString(R.string.black));
                mColorImg.setImageResource(R.drawable.shoes_black);
                break;
            case R.id.add_first_color_brown:
                mColorName.setText(getString(R.string.brown));
                mColorImg.setImageResource(R.drawable.shoes_brown);
                break;
            case R.id.add_first_color_pink:
                mColorName.setText(getString(R.string.pink));
                mColorImg.setImageResource(R.drawable.shoes_pink);
                break;
            case R.id.add_first_color_purple:
                mColorName.setText(getString(R.string.purple));
                mColorImg.setImageResource(R.drawable.shoes_purple);
                break;
            case R.id.add_first_color_blue:
                mColorName.setText(getString(R.string.blue));
                mColorImg.setImageResource(R.drawable.blue);
                break;
            case R.id.add_first_color_green:
                mColorName.setText(getString(R.string.green));
                mColorImg.setImageResource(R.drawable.shoes_green);
                break;
            case R.id.add_first_color_red:
                mColorName.setText(getString(R.string.red));
                mColorImg.setImageResource(R.drawable.shoes_red);
                break;
            case R.id.add_first_color_orange:
                mColorName.setText(getString(R.string.orange));
                mColorImg.setImageResource(R.drawable.shoes_orange);
                break;
            case R.id.add_first_color_yellow:
                mColorName.setText(getString(R.string.yellow));
                mColorImg.setImageResource(R.drawable.shoes_yellow);
                break;
            case R.id.add_first_color_ex:
                mColorName.setText(getString(R.string.ex_color));
                mColorImg.setImageResource(R.drawable.shoes_ex);
                break;
            case R.id.add_first_step_back:
                finish();
            default:
                break;
        }
    }
}
