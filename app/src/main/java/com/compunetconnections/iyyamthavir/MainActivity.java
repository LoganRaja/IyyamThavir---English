package com.compunetconnections.iyyamthavir;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends ActionBarActivity {
    ViewPager pager;
    ViewPagerAdapterBanking adapter;
    SlidingTabLayout bankingtabs;
    ExpandableListView expListView;
    CharSequence Titles[] = {"  STI  ","  ICTC   ","  PPTCT   ","  CST  ","  BTS   ","  CONDOM   "};
    int Numboftabs = 6;
    TextView art, bts, ictc, sti, ti;

    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapterBanking(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
       /* getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);*/
        // Assiging the Sliding Tab Layout View
        bankingtabs = (SlidingTabLayout) findViewById(R.id.bankingtabs);
        bankingtabs.setCustomTabView(R.layout.custom_tab, R.id.customText);
        bankingtabs.setViewPager(pager); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // Setting Custom Color for the Scroll bar indicator of the Tab View

        bankingtabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

 }
    public void show(String str) {
        try {
            String myJSON=null;
            JSONArray mJsonArray = new JSONArray(myJSON);
            JSONObject mJsonObject = new JSONObject();

            for (int i = 0; i <mJsonArray.length(); i++) {
                mJsonObject = mJsonArray.getJSONObject(i);

            }

        }
        catch (JSONException e)
        {
            int d = 0;
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:
                TelephonyManager telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                int simState = telMgr.getSimState();
                if (simState == TelephonyManager.SIM_STATE_ABSENT) {
                    Toast.makeText(this, "Sim card not found your device", Toast.LENGTH_LONG).show();
                } else {
                    String url = "tel:18004191800";
                    Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    startActivity(callintent);
                }
                break;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                String shareBody = "Hey! I am using Iyyam Thavir app -The best app for clearing all personal doubt .\nGo ahead, give it a try! https://play.google.com/store/apps/details?id=com.compunet.stuforia";
                sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Iyyam Thavir App ( share link )");
                sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sendIntent, "Share via"));
                break;
            case R.id.help:
                new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();
                break;
            case R.id.about:
                Intent aboutintent = new Intent(this,AboutusActivity.class);
                startActivity(aboutintent);
               /* new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
                break;
            case R.id.contactus:
                new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();
                break;

            case android.R.id.home:
                finish();
               break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}