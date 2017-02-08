package com.compunetconnections.iyyamthavir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by welcome on 3/7/2016.
 */
public class ActivityWhatAreWe extends ActionBarActivity {
    String type="";
    TextView heading;
    JustifiedTextView body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_are_we);

        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        if(intent.getStringExtra("type").equals("art"))
        {
            getSupportActionBar().setTitle("CST ");
          heading=(TextView)findViewById(R.id.heading_art);
            body=(JustifiedTextView)findViewById(R.id.body_art);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        else if(intent.getStringExtra("type").equals("bts"))
        {
            getSupportActionBar().setTitle("BTS ");
            heading=(TextView)findViewById(R.id.heading_bts);
            body=(JustifiedTextView)findViewById(R.id.body_bts);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        else if(intent.getStringExtra("type").equals("ictc"))
        {
            getSupportActionBar().setTitle("ICTC ");
            heading=(TextView)findViewById(R.id.heading_ictc);
            body=(JustifiedTextView)findViewById(R.id.body_ictc);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        else if(intent.getStringExtra("type").equals("sti"))
        {
            getSupportActionBar().setTitle("STI");
            heading=(TextView)findViewById(R.id.heading_sti);
            body=(JustifiedTextView)findViewById(R.id.body_sti);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        else if(intent.getStringExtra("type").equals("ti"))
        {
            getSupportActionBar().setTitle("Condom ");
            heading=(TextView)findViewById(R.id.heading_ti);
            body=(JustifiedTextView)findViewById(R.id.body_ti);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        else if(intent.getStringExtra("type").equals("pptct"))
        {
            getSupportActionBar().setTitle("PPTCT ");
            heading=(TextView)findViewById(R.id.heading_pptct);
            body=(JustifiedTextView)findViewById(R.id.body_pptct);
            heading.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
