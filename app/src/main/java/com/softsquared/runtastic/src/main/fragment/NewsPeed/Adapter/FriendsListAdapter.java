package com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class FriendsListAdapter extends BaseAdapter {
    ArrayList<FriendsListItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public FriendsListAdapter(ArrayList<FriendsListItem> mList, Context context, int layout) {
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
    public FriendsListItem getItem(int position) {
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
        ImageView ivProfileImg = convertView.findViewById(R.id.friends_list_profile_img);
        TextView tvName = convertView.findViewById(R.id.friends_list_name);
        ivProfileImg.setBackground(new ShapeDrawable(new OvalShape()));
        ivProfileImg.setClipToOutline(true);

        FriendsListItem item = mList.get(position);
        Glide.with(convertView.getContext()).load(item.getProfileImage()).into(ivProfileImg);
        tvName.setText(item.getFirstName() + " " + item.getLastName());


        return convertView;
    }
}
