package com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class ReceiveListAdapter extends BaseAdapter {
    ArrayList<ReceiveFriendItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public ReceiveListAdapter(ArrayList<ReceiveFriendItem> mList, Context context, int layout) {
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
    public ReceiveFriendItem getItem(int position) {
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
        ImageView profileImg = convertView.findViewById(R.id.alarm_list_item_profile_img);
        TextView name = convertView.findViewById(R.id.alarm_list_item_name);

        ReceiveFriendItem item = mList.get(position);
        String nameStr = item.getFirstName() + " " + item.getLastName();

        profileImg.setImageResource(R.drawable.friend_profile_common);
        name.setText(nameStr);

        return convertView;
    }
}
