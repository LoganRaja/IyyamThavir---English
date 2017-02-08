package com.compunetconnections.iyyamthavir;

/**
 * Created by girid on 10/26/2015.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private Context mContext;
    String[][] message;
    ArrayList<String> list = new ArrayList<String>();
    public MyRecyclerAdapter(Context context, String[][] message) {
        this.mContext = context;
        this.message=message;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_coustomlist, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
       if(message[i][0].equals("answer")) {
           customViewHolder.textView.setGravity(Gravity.RIGHT);
           customViewHolder.textView.setBackgroundResource(R.color.green);
       }
        if(message[i][0].equals("question")) {
            customViewHolder.textView.setGravity(Gravity.LEFT);
            customViewHolder.textView.setBackgroundResource(R.color.gray);
        }
        customViewHolder.textView.setText(message[i][1]);
        customViewHolder.textView.setTag(customViewHolder);
    }
    @Override
    public int getItemCount() {
        return message.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.message);

        }
    }
}
