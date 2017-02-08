package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;


public class TreatmentActivity extends Activity {
    Button art,pptct,sti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treatment_activity);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        art=(Button) findViewById(R.id.art);
        pptct=(Button) findViewById(R.id.pptct);
        sti=(Button) findViewById(R.id.sti);
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artmainintent=new Intent(TreatmentActivity.this,ARTMainActivity.class);
                startActivity(artmainintent);
            }
        });
        pptct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artmainintent=new Intent(TreatmentActivity.this,PPTCTMainActivity.class);
                startActivity(artmainintent);
            }
        });
        sti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artmainintent=new Intent(TreatmentActivity.this,STIMainActivity.class);
                startActivity(artmainintent);
            }
        });
    }
}
