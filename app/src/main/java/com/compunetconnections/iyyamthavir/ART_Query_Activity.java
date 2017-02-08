package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ART_Query_Activity extends ActionBarActivity {

    ImageView answer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquery);
        Intent intent=getIntent();
        if(intent.getStringExtra("type").equals("art"))
        {
            getSupportActionBar().setTitle("CST Queries");

        }
        else if(intent.getStringExtra("type").equals("bts"))
        {
            getSupportActionBar().setTitle("BTS Queries");

        }
        else if(intent.getStringExtra("type").equals("ictc"))
        {
            getSupportActionBar().setTitle("ICTC Queries");

        }
        else if(intent.getStringExtra("type").equals("sti"))
        {
            getSupportActionBar().setTitle("STI Queries");

        }
        else if(intent.getStringExtra("type").equals("ti"))
        {
            getSupportActionBar().setTitle("Condom Queries");

        }
        else if(intent.getStringExtra("type").equals("pptct"))
        {
            getSupportActionBar().setTitle("PPTCT Queries");

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        answer=(ImageView)findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                int simState = telMgr.getSimState();
                if (simState == TelephonyManager.SIM_STATE_ABSENT) {
                    Toast.makeText(ART_Query_Activity.this, "Sim card not found in your device", Toast.LENGTH_LONG).show();
                } else {
                    String url = "tel:18004191800";
                    Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    startActivity(callintent);
                }
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
