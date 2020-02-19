package com.softsquared.runtastic.src.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
        int viewType = groupPosition;
        View firstView = inflater.inflate(R.layout.sign_up_next_child_item,null);
        View secondView = inflater.inflate(R.layout.sign_up_next_child_item_permit,null);
        View thirdView = inflater.inflate(R.layout.sign_up_next_child_item_goal,null);

        if(convertView == null){
            switch(viewType) {
                case 0:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item,null);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item_permit,null);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item_goal,null);
                    break;
            }
        } else {
            switch(viewType) {
                case 0:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item,null);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item_permit,null);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.sign_up_next_child_item_goal,null);
                    break;
            }
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setExpandableListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            return;
        }

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            view = listAdapter.getGroupView(i, false, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
            if(((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                View listItem = null;
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    listItem = listAdapter.getChildView(i, j, false, listItem, listView);
                    listItem.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, View.MeasureSpec.UNSPECIFIED));
                    listItem.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
