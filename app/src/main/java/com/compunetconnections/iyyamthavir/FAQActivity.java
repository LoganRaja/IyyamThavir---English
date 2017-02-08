package com.compunetconnections.iyyamthavir;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FAQActivity extends ActionBarActivity {

    ExpandableListView expListView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    String type="";



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));




        if(intent.getStringExtra("type").equals("art"))
        {
            getSupportActionBar().setTitle("CST ");

        }
        else if(intent.getStringExtra("type").equals("bts"))
        {
            getSupportActionBar().setTitle("BTS ");

        }
        else if(intent.getStringExtra("type").equals("ictc"))
        {
            getSupportActionBar().setTitle("ICTC ");

        }
        else if(intent.getStringExtra("type").equals("sti"))
        {
            getSupportActionBar().setTitle("STI");
                   }
        else if(intent.getStringExtra("type").equals("ti"))
        {
            getSupportActionBar().setTitle("Condom ");

        }
        else if(intent.getStringExtra("type").equals("pptct"))
        {
            getSupportActionBar().setTitle("PPTCT ");

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);









        expListView = (ExpandableListView) findViewById(R.id.faqlistview);
        prepareListData();
       /* Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        expListView.setIndicatorBoundsRelative(width-150,width);*/
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild,"faq");
        expListView.setAdapter(listAdapter);
        /*expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View clickedView, int groupPosition, long rowId) {
                ImageView groupIndicator = (ImageView) clickedView.findViewById(R.id.help_group_indicator);
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                    groupIndicator.setImageResource(R.drawable.expand);
                } else {
                    parent.expandGroup(groupPosition);
                    groupIndicator.setImageResource(R.drawable.collapse);
                }

                return true;
            }
        });*/
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
                if(type.equals("bts")) {
                    listDataHeader.add("Who can donate blood?");
                    listDataHeader.add("How frequently I can donate blood?");
                    listDataHeader.add("Advantage of blood donation for a donor?");
                    listDataHeader.add("Whom do I contact in case of blood requirement?");
                    //listDataHeader.add("5.\tWhom do I contact in case of blood requirement?");

                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    //List<String> drawerSubMenu5 = new ArrayList<String>();
                    drawerSubMenu1.add("Any healthy person with clean habits between the age of 18 to 65 can donate blood.");
                    drawerSubMenu2.add("You can donate blood once in 3 months.");
                    drawerSubMenu3.add("New blood cells will be generated after donation ");
                    drawerSubMenu4.add("You can contact the nearest blood bank located in the Government Medical College Hospitals, District Head Quarters Hospitals and other Government hospitals.");
                    //drawerSubMenu5.add("In case of blood requirement, the nearest blood bank located in the District Head Quarters / Government Medical College & Hospitals may be contacted or contact the toll free contact number - 1800 419 1800.");

                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                    //listDataChild.put(listDataHeader.get(4), drawerSubMenu5);

                }
                else if(type.equals("ti")) {
                    listDataHeader.add("What are the advantages of condoms?");
                    listDataHeader.add("Are there any side effects of using of condoms?");
                    listDataHeader.add("Where do I get condoms?");
                    listDataHeader.add("Does using double condom is more protective?");
                    listDataHeader.add("Should I use condom while taking STI/HIV treatment?");
                    listDataHeader.add("Does the condom use reduces sexual pleasure?");
                    listDataHeader.add("What precautions need to be taken before using condoms?");
                    //listDataHeader.add("8.\tHow to dispose used condoms?");

                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    List<String> drawerSubMenu5 = new ArrayList<String>();
                    List<String> drawerSubMenu6 = new ArrayList<String>();
                    List<String> drawerSubMenu7 = new ArrayList<String>();
                    //List<String> drawerSubMenu8 = new ArrayList<String>();
                    drawerSubMenu1.add("It prevents the unwanted pregnancy, Sexually Transmitted Infections (STI) and HIV/AIDS.");
                    drawerSubMenu2.add("There are no side effects, except if you are allergic to latex rubber or to the lubricant.");
                    drawerSubMenu3.add("Male condoms are available at all government hospitals, ICTC, ART, STI clinics, pharmacies and supermarkets.");
                    drawerSubMenu4.add("No, only one condom is sufficient.");
                    drawerSubMenu5.add("Yes, in order to avoid infection to your partner");
                    drawerSubMenu6.add("No, Condom use will not reduce sexual pleasure");
                    drawerSubMenu7.add("Check the expiry date on the pack and any tear before you use it. ");
                    //drawerSubMenu8.add("Remove it carefully, tie a knot to prevent spilling, wrap it up in tissue and dispose.");

                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                    listDataChild.put(listDataHeader.get(4), drawerSubMenu5);
                    listDataChild.put(listDataHeader.get(5), drawerSubMenu6);
                    listDataChild.put(listDataHeader.get(6), drawerSubMenu7);
                    //listDataChild.put(listDataHeader.get(7), drawerSubMenu8);

                }
                else if(type.equals("art"))
                {
                    listDataHeader.add("What is HIV?");
                    listDataHeader.add("What is the difference between HIV and AIDS?");
                    listDataHeader.add("How do I know whether I have HIV?");
                    listDataHeader.add("Where can I avail treatment for HIV/AIDS?");
                    listDataHeader.add("What is the use of taking ART medicine?");
                    //listDataHeader.add("6.\tWhere can I have test for HIV?");
                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    List<String> drawerSubMenu5 = new ArrayList<String>();
                    //List<String> drawerSubMenu6 = new ArrayList<String>();
                    drawerSubMenu1.add("HIV stands for Human Immunodeficiency Virus (HIV). It is a virus which destroys the human immune system.");
                    drawerSubMenu2.add("HIV is the virus and AIDS is the disease which is caused due to HIV.");
                    drawerSubMenu3.add("HIV can be detected through blood test at free of cost in all Integrated Counselling and Testing Centres (ICTC) located at Government Hospitals.");
                    drawerSubMenu4.add("Free treatment is available in all ART Centres located at Government Hospitals.");
                    drawerSubMenu5.add("ART medicines will improve the immunity and prolong the life of HIV infected people.");
                    //drawerSubMenu6.add("HIV Counselling and testing services (Integrated Counselling and Testing Centres - ICTC) are available at all Government Hospitals, District Headquarters Hospital and Government Primary Health Centres. ");

                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                    listDataChild.put(listDataHeader.get(4), drawerSubMenu5);
                    //listDataChild.put(listDataHeader.get(5), drawerSubMenu6);
                }
                else if (type.equals("pptct")){
                    listDataHeader.add("What is PPTCT?");
                    listDataHeader.add("Can a HIV infected couple have HIV free baby?");
                    listDataHeader.add("How do I know the HIV status of a newborn?");
                    listDataHeader.add("Does HIV spread through HIV infected mother while breastfeeding?");
                    listDataHeader.add("If the first baby is HIV positive, can the couple have the second baby as HIV negative?");

                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    List<String> drawerSubMenu5 = new ArrayList<String>();
                    drawerSubMenu1.add("PPTCT is Prevention of Parent To Child Transmission (HIV) programme focusing on prevention of HIV infection from HIV positive pregnant women to her new born.");
                    drawerSubMenu2.add("Yes, HIV infected couple can have HIV free baby.");
                    drawerSubMenu3.add("The HIV status of a new born can be known through a test done from 6 weeks to 6 months.");
                    drawerSubMenu4.add("Yes, HIV spreads through breastfeeding of HIV infected mother. But the transmission is very minimal if the mother is on Anti Retroviral Treatment (ART).");
                    drawerSubMenu5.add("Yes, a couple can have HIV negative baby even though the first baby is HIV positive.");

                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                    listDataChild.put(listDataHeader.get(4), drawerSubMenu5);

                }
                else if(type.equals("ictc"))
                {
                    listDataHeader.add("What is HIV?");
                    listDataHeader.add("What is the difference between HIV and AIDS?");
                    listDataHeader.add("How do I know whether I have HIV?");
                    listDataHeader.add("Where can I avail treatment for HIV/AIDS?");
                    listDataHeader.add("What is the use of taking ART medicine?");
                    //listDataHeader.add("6.\tWhere can I have test for HIV?");
                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    List<String> drawerSubMenu5 = new ArrayList<String>();
                    //List<String> drawerSubMenu6 = new ArrayList<String>();
                    drawerSubMenu1.add("HIV stands for Human Immunodeficiency Virus (HIV). It is a virus which destroys the human immune system.");
                    drawerSubMenu2.add("HIV is the virus and AIDS is the disease which is caused due to HIV.");
                    drawerSubMenu3.add("HIV can be detected through blood test at free of cost in all Integrated Counselling and Testing Centres (ICTC) located at Government Hospitals.");
                    drawerSubMenu4.add("Free treatment is available in all ART Centres located at Government Hospitals.");
                    drawerSubMenu5.add("ART medicines will improve the immunity and prolong the life of HIV infected people.");
                    //drawerSubMenu6.add("HIV Counselling and testing services (Integrated Counselling and Testing Centres - ICTC) are available at all Government Hospitals, District Headquarters Hospital and Government Primary Health Centres. ");

                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                    listDataChild.put(listDataHeader.get(4), drawerSubMenu5);
                    //listDataChild.put(listDataHeader.get(5), drawerSubMenu6);
                }
                else if(type.equals("sti")) {
                    listDataHeader.add("What is the relationship between STI (Sexually Transmitted Infection) and HIV?");
                    listDataHeader.add("What are the Symptoms of STI?");
                    listDataHeader.add("Is STI curable?");
                    listDataHeader.add("If an antenatal mother has STI, will it affect the baby?");
                    List<String> drawerSubMenu1 = new ArrayList<String>();
                    List<String> drawerSubMenu2 = new ArrayList<String>();
                    List<String> drawerSubMenu3 = new ArrayList<String>();
                    List<String> drawerSubMenu4 = new ArrayList<String>();
                    drawerSubMenu1.add("A person who has STI is more likely to become infected with HIV, if they have unprotected sex (sex without a condom) with HIV infected person.");
                    drawerSubMenu2.add("The symptoms are ulcer in the genitals, discharge from vagina and urethra, lower abdominal pain in women, pain or burning sensation while urinating and painful scrotal swelling in men.");
                    drawerSubMenu3.add("Yes, it is curable.");
                    drawerSubMenu4.add("Yes, if the mother has STI, there are chances for the transmission of STI to the baby during pregnancy or during labour. If the mother is treated, the baby will be protected.");
                    listDataChild.put(listDataHeader.get(0), drawerSubMenu1);
                    listDataChild.put(listDataHeader.get(1), drawerSubMenu2);
                    listDataChild.put(listDataHeader.get(2), drawerSubMenu3);
                    listDataChild.put(listDataHeader.get(3), drawerSubMenu4);
                }

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
