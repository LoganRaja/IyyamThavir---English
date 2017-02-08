package com.compunetconnections.iyyamthavir;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }



    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    LinearLayout my_query;
    HashMap<String, List<String>> listDataChild;
    TextView art, bts, ictc, ti;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.art_fragment_try, container, false);
        art = (TextView) v.findViewById(R.id.art);
        bts = (TextView) v.findViewById(R.id.bts);
        ictc = (TextView) v.findViewById(R.id.ictc);
        ti = (TextView) v.findViewById(R.id.ti);
        my_query = (LinearLayout) v.findViewById(R.id.my_query);


        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityWhatAreWe.class);
                intent.putExtra("type","art");
                startActivity(intent);
            }
        });
        ictc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FAQActivity.class);
                intent.putExtra("type","art");
                startActivity(intent);
            }
        });
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Services.class);
                intent.putExtra("type","art");
                startActivity(intent);
               /* AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("type","art");
                startActivity(intent);
               /* new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });


        my_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ART_Query_Activity.class);
                intent.putExtra("type","art");
                startActivity(intent);
               /* new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });

       /* bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BTSActivity.class);
                startActivity(intent);
            }
        });

        sti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), STIActivity.class);
                startActivity(intent);
            }
        });
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TIActivity.class);
                startActivity(intent);
            }
        });*/
      /*  expListView = (ExpandableListView) v.findViewById(R.id.lvExp);
        prepareListData();


        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                *//*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*//*
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                *//*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*//*

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long iid) {
                // TODO Auto-generated method stub
               *//* Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*//*

                switch (childPosition) {

                }
                return false;
            }
        });*/
        return v;
    }

}
