package com.compunetconnections.iyyamthavir;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class StartActivity extends ActionBarActivity {
    Button treatment, symptoms, prognosis, prevention, main;
    LinearLayout startmain, my_query, blood_bank;
    SqlliteDBIyyam sqlliteDBIyyam;
    CheckBox checkBox;

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
    }
}
       /* treatment=(Button) findViewById(R.id.treatment);
        symptoms=(Button) findViewById(R.id.symptoms);
        prognosis =(Button) findViewById(R.id.prognosis);
        prevention =(Button) findViewById(R.id.prevention);
        my_query =(LinearLayout) findViewById(R.id.my_query);
        blood_bank =(LinearLayout) findViewById(R.id.blood_bank);
        main =(Button) findViewById(R.id.main);
        startmain=(LinearLayout)findViewById(R.id.startmain);


        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        actionBar.setLogo(R.drawable.ilogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,TreatmentActivity.class);
                startActivity(intent);
            }
        });
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,SymptomsActivity.class);
                startActivity(intent);
            }
        });
        prognosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,PrognosisActivity.class);
                startActivity(intent);
            }
        });
        prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,PreventionActivity.class);
                startActivity(intent);
            }
        });
        *//*my_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(StartActivity.this,ART_Query_Activity.class);
                startActivity(intent);
            }
        });*//*
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        blood_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*new AlertDialog.Builder(StartActivity.this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*//*
                Intent intent=new Intent(StartActivity.this,BTSMainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = new Intent(this, BootComplete.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        startmain.setAlpha((float) 0.2);
    }

    @Override
    public void onResume() {
        super.onResume();
        startmain.setAlpha((float) 1.0);
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
               *//* new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*//*
                break;
            case R.id.contactus:
                new AlertDialog.Builder(this)
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
};*/