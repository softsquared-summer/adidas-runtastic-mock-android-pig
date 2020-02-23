package com.softsquared.runtastic.src.main.Profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.Profile.ProfileSettingActivity;

import java.util.ArrayList;

public class ProfileSettingListAdapter extends BaseAdapter {
    ArrayList<ProfileSettingItem> mList = new ArrayList<>();
    Context context;
    int layout;
    LayoutInflater inf;

    public ProfileSettingListAdapter(ArrayList<ProfileSettingItem> mList, Context context, int layout) {
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
    public ProfileSettingItem getItem(int position) {
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

        ImageView icon = convertView.findViewById(R.id.profile_setting_item_icon);
        ImageView inIcon = convertView.findViewById(R.id.profile_setting_iv_in);
        TextView title = convertView.findViewById(R.id.profile_setting_tv_title);
        TextView sub = convertView.findViewById(R.id.profile_setting_tv_sub);

        ProfileSettingItem item = mList.get(position);

        icon.setImageResource(item.getIcon());
        inIcon.setImageResource(item.getInIcon());
        title.setText(item.getTitle());
        sub.setText(item.getUnit());


        return convertView;
    }

}
