package com.softsquared.runtastic.src.main.fragment.Status;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.SneakersListAdapter;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.SneakersListItem;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.ManageShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.models.ShoesDelRequest;
import com.softsquared.runtastic.src.main.fragment.Status.services.ManageShoesActivityService;

import java.util.ArrayList;

public class ManageShoesActivity extends BaseActivity implements ManageShoesActivityView {
    ArrayList<SneakersListItem> mSneakersArray = new ArrayList<>();

    SwipeMenuListView mListViewSneakers;
    SneakersListAdapter adapter;

    ImageButton mBtnBack;

    double width;
    int divideWidth;
    SwipeMenuCreator creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shoes);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x * 0.2;
        divideWidth = (int) width;

        showProgressDialog();
        mListViewSneakers = findViewById(R.id.manage_shoes_list_view);
        mBtnBack = findViewById(R.id.manage_shoes_back);
        final ManageShoesActivityService service = new ManageShoesActivityService(this);
        service.getArrayUserSneakers();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(189,
                        68, 68)));
                deleteItem.setWidth(divideWidth);
                deleteItem.setTitle("삭제");
                deleteItem.setTitleColor(getColor(R.color.colorWhite));
                deleteItem.setTitleSize(14);
                menu.addMenuItem(deleteItem);
            }
        };
        mListViewSneakers.setMenuCreator(creator);
        mListViewSneakers.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mListViewSneakers.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int shoesNumber = Integer.parseInt(mSneakersArray.get(position).getSneakersNo());
                        ShoesDelRequest request = new ShoesDelRequest(shoesNumber);
                        mSneakersArray.remove(position);
                        service.deleteUserShoes(request);

                        service.getArrayUserSneakers();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
    }

    @Override
    public void getArrayShoes(ArrayList<SneakersListItem> result) {
        mSneakersArray = result;
        adapter = new SneakersListAdapter(mSneakersArray, getApplicationContext(), R.layout.manage_shoes_list_item);
        mListViewSneakers.setAdapter(adapter);
        hideProgressDialog();
    }

    @Override
    public void deleteSuccess(String message) {
        adapter = new SneakersListAdapter(mSneakersArray, getApplicationContext(), R.layout.manage_shoes_list_item);
        mListViewSneakers.setAdapter(adapter);
    }
}
