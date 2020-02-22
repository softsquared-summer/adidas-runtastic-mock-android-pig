package com.softsquared.runtastic.src.sign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class SignUpExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<SignUpParentItem> mParentList;
    private LayoutInflater inflater;

    public SignUpExpandableAdapter(Context mContext, ArrayList<SignUpParentItem> mParentList) {
        this.mContext = mContext;
        this.mParentList = mParentList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mParentList.size();
    }


    @Override
    public SignUpParentItem getGroup(int groupPosition) {
        return mParentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.sign_up_next_parent_item,null);
        }

        TextView title = convertView.findViewById(R.id.sign_up_next_list_parent_text);
        ImageView icon = convertView.findViewById(R.id.sign_up_next_list_parent_icon);

        SignUpParentItem parentItem = mParentList.get(groupPosition);

        title.setText(parentItem.getTitle());

        if(isExpanded){
            icon.setImageResource(R.drawable.sign_up_circle_check);
        }else{
            icon.setImageResource(R.drawable.sign_up_circle);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(mParentList.get(groupPosition).getV() == null){
            switch(groupPosition) {
                case 0:
                    mParentList.get(0).setV(inflater.inflate(R.layout.sign_up_next_child_item,null));
                    break;
                case 1:
                    mParentList.get(1).setV(inflater.inflate(R.layout.sign_up_next_child_item_permit,null));
                    break;
                case 2:
                    mParentList.get(2).setV(inflater.inflate(R.layout.sign_up_next_child_item_goal,null));
                    break;
            }
        }

        return mParentList.get(groupPosition).getV();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public ArrayList<SignUpParentItem> getParentList(){
        return this.mParentList;
    }


}
