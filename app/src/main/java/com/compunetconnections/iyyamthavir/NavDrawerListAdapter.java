package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by girid on 9/7/2015.
 */
class NavDrawerListAdapter extends BaseAdapter {
    String[] name2;
    private Context context;

    public NavDrawerListAdapter(Context context, int listview){
        this.context=context;
        if(listview==1)
        name2=context.getResources().getStringArray(R.array.values);
        else
            name2=context.getResources().getStringArray(R.array.values1);
    }

    @Override
    public int getCount() {
        return name2.length;
    }

    @Override
    public Object getItem(int position) {
        return name2[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    row = mInflater.inflate(R.layout.customlist,parent,false);
        }
        else {
            row=convertView;
        }
        ImageView imgIcon = (ImageView) row.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) row.findViewById(R.id.textView);
        txtTitle.setText(name2[position]);

        return row;
    }

}