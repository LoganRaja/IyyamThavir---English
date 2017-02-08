package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class NavigationDrawer extends ActionBarActivity implements AdapterView.OnItemClickListener,ActionBar.TabListener{



    private DrawerLayout drawerLayout;


    private ActionBarDrawerToggle drawerListener;
    private NavDrawerListAdapter myadapter;
    RelativeLayout skip;

    ListView listview,listview1;
    SqlliteDBIyyam sqlliteDBIyyam;
    CheckBox checkBox;


    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqlliteDBIyyam = new SqlliteDBIyyam(this);
        Cursor cursor = sqlliteDBIyyam.select();
        if (cursor.getCount() == 0) {
            Intent intent = new Intent(this, TermsAndCondition.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main_try);


    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    listview=(ListView)findViewById(R.id.listview);
        listview1=(ListView)findViewById(R.id.listview1);
       /* Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        skip=(RelativeLayout)findViewById(R.id.skip);
        skip.getLayoutParams().height = height;
        skip.getLayoutParams().width = width;*/
        myadapter = new NavDrawerListAdapter(this,1);
        listview.setAdapter(myadapter);
        myadapter = new NavDrawerListAdapter(this,2);
        listview1.setAdapter(myadapter);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.closed) {
            @Override
            public void onDrawerClosed(View drawerView) {
                int len = listAdapter.getGroupCount();
                for (int i = 0; i < len; i++) {
                    expListView.collapseGroup(i);
                }
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        listview.setOnItemClickListener(this);
        listview1.setOnItemClickListener(this);

        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Iyyam Thavir™");
        drawerListener.syncState();
        displayView(1,0);


        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListData();
       /* Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        expListView.setIndicatorBoundsRelative(width-150,width);*/
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild,"main");
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long iid) {
                // TODO Auto-generated method stub
                drawerLayout.closeDrawers();
                Fragment fragment = null;
                if (groupPosition == 0)
                    switch (childPosition) {

                        case 0:
                            fragment = new Fragment3();
                            getSupportActionBar().setTitle("ICTC");
                            break;
                        case 1:
                            fragment = new Fragment4();
                            getSupportActionBar().setTitle("STI");
                            break;
                        case 2:
                            fragment = new Fragment6();
                            getSupportActionBar().setTitle("PPTCT");
                            break;
                    }
                else if (groupPosition == 1) {
                    switch (childPosition) {

                        case 0:
                            fragment = new Fragment4();
                            getSupportActionBar().setTitle("STI");
                            break;
                        case 1:
                            fragment = new Fragment1();
                            getSupportActionBar().setTitle("CST");
                            break;
                        case 2:
                            fragment = new Fragment6();
                            getSupportActionBar().setTitle("PPTCT");
                            break;
                    }
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frame_container, fragment).commit();
                }

                return false;
            }
        });


        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                int len = listAdapter.getGroupCount();
                for (int i = 0; i < len; i++) {
                    if (i != groupPosition) {
                        expListView.collapseGroup(i);
                    }
                }

            }
        });


    }





    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("DIAGNOSIS");
        listDataHeader.add("TREATMENT");



        List<String> drawerSubMenu1 = new ArrayList<String>();
        List<String> drawerSubMenu2 = new ArrayList<String>();
        drawerSubMenu1.add("ICTC");
        drawerSubMenu1.add("STI");
        drawerSubMenu2.add("STI");
        drawerSubMenu2.add("CST");
        drawerSubMenu2.add("PPTCT");

        listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
        listDataChild.put(listDataHeader.get(1), drawerSubMenu2);





    }








    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.listview:
                displayView(1,position);
                break;
            case R.id.listview1:
                displayView(2,position);
                break;
        }
        drawerLayout.closeDrawers();
    }

    private void displayView(int listview,int position) {
        Fragment fragment = null;
        Intent intent;
        if(listview==1)
            switch (position) {
                case 0:
                    fragment = new FragmentStart();
                    getSupportActionBar().setTitle("Iyyam Thavir™");
                    break;
                case 1:
                    fragment = new Fragment2();
                    getSupportActionBar().setTitle("BTS");
                    break;
                case 2:
                    fragment = new FragmentPrevention();
                    getSupportActionBar().setTitle("Prevention");
                    break;
                case 3:
                    fragment = new FragmentSymptoms();
                    getSupportActionBar().setTitle("Symptoms");
                    break;
            }
        if(listview==2)
            switch (position){
                case 0:
                    fragment = new FragmentAboutUs();
                    getSupportActionBar().setTitle("About Us");
                    break;
                case 1:
                    fragment = new FragmentContactUs();
                    getSupportActionBar().setTitle("Contact Us");
                    break;
                /*new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();
                break;*/
                /*case 2:
                    new AlertDialog.Builder(this)
                            .setTitle("COMING SOON")
                            .setMessage("This feature will be available soon.")
                            .setIcon(android.R.drawable.alert_dark_frame)
                            .setNegativeButton(android.R.string.ok, null).show();
                    break;*/

            }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            /*case R.id.action_search:
            break;*/
            case R.id.call:

                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawers();
                }
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

        }
        if(drawerListener.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }










    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            /*drawerLayout.closeDrawers();*/
            super.onBackPressed();
            return;
        }

        return;

    }

    }



