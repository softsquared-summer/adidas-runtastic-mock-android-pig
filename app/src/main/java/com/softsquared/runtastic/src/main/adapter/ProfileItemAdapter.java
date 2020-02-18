package com.softsquared.runtastic.src.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class ProfileItemAdapter extends BaseAdapter {

    ArrayList<ProfileItem> mList = new ArrayList<>();
    Context context;
    int layout;
    LayoutInflater inf;

    public ProfileItemAdapter(ArrayList<ProfileItem> mList, Context context, int layout) {
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
    public ProfileItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);

        ImageView ivIcon = convertView.findViewById(R.id.profile_list_item_icon);
        TextView tvTitle = convertView.findViewById(R.id.profile_list_item_title);
        TextView tvExplain = convertView.findViewById(R.id.profile_list_item_explain);
        ProfileItem item = mList.get(position);

        ivIcon.setImageResource(item.getIcon());
        tvTitle.setText(item.getTitle());
        tvExplain.setText(item.getExplain());

        return convertView;
    }
}
