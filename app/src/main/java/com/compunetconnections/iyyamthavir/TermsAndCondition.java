package com.compunetconnections.iyyamthavir;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class TermsAndCondition extends ActionBarActivity {
    CheckBox checkBox;
    Button sumbit_button;
    SqlliteDBIyyam sqlliteDBIyyam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_condition);
        sqlliteDBIyyam=new SqlliteDBIyyam(this);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        sumbit_button=(Button)findViewById(R.id.submit_button);
        sumbit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    sqlliteDBIyyam.insert();
                    Intent intent = new Intent(TermsAndCondition.this, NavigationDrawer.class);
                    startActivity(intent);
                    finish();
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
