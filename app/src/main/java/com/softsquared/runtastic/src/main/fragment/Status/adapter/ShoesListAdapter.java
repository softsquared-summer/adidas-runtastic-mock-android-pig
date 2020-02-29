package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.FriendsListItem;

import java.util.ArrayList;

public class ShoesListAdapter extends BaseAdapter {
    ArrayList<ShoesItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public ShoesListAdapter(ArrayList<ShoesItem> mList, Context context, int layout) {
        this.mList = mList;
        this.context = context;
        this.layout = layout;
        this.inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ShoesItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        ShoesItem item = mList.get(position);
        TextView tvName = convertView.findViewById(R.id.shoes_profile_name);
        ImageView ivProfileImg = convertView.findViewById(R.id.shoes_profile_img);


        Glide.with(convertView.getContext()).load(item.getImageUrl()).into(ivProfileImg);
        // 이미지 url에 넣는걸로 수정해야함


        tvName.setText(item.getModelName());



        return convertView;
    }
}
