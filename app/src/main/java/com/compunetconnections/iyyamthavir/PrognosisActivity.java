package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;


public class PrognosisActivity extends Activity {
    Button bts,ti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prognosis_activity);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        bts=(Button) findViewById(R.id.bts);
        ti=(Button) findViewById(R.id.ti);
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artmainintent=new Intent(PrognosisActivity.this,ICTCMainActivity.class);
                startActivity(artmainintent);
            }
        });
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artmainintent=new Intent(PrognosisActivity.this,STIMainActivity.class);
                startActivity(artmainintent);
            }
        });
    }
}
