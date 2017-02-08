package com.compunetconnections.iyyamthavir;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PPTCTMainActivity extends ActionBarActivity {




    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView art, bts, ictc, ti;
    LinearLayout my_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pptct_fragment_try);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        art = (TextView) findViewById(R.id.art);
        bts = (TextView) findViewById(R.id.bts);
        ictc = (TextView) findViewById(R.id.ictc);
        ti = (TextView) findViewById(R.id.ti_pptct);
        my_query=(LinearLayout)findViewById(R.id.my_query);


        my_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PPTCTMainActivity.this, ART_Query_Activity.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
            }
        });







        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PPTCTMainActivity.this, ActivityWhatAreWe.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
            }
        });
        ictc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PPTCTMainActivity.this, FAQActivity.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
            }
        });
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Services.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
               /* new AlertDialog.Builder(TIMainActivity.this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PPTCTMainActivity.this, LocationActivity.class);
                intent.putExtra("type","ictc");
                startActivity(intent);

                /*Intent intent = new Intent(PPTCTMainActivity.this, LocationActivity.class);
                intent.putExtra("type","pptct");
                startActivity(intent);*/
               /* new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.other_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
