package com.compunetconnections.iyyamthavir;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class ART_Query_Activity1 extends ActionBarActivity {




    private static final String TAG=ART_Query_Activity1.class.getName();
    private static ArrayList<Activity> activities=new ArrayList<Activity>();











    HttpPost httppost;
    HttpResponse response;
    String myJSON="",macAddress,update_viewdetails=null;
    ProgressDialog dialog = null;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    EditText question;
    Button answer;
    ArrayList<NameValuePair> nameValuePairs;
    ArrayList<NameValuePair> nameValuePairs1;
    ArrayList<String> list = new ArrayList<String>();
    String[][] myStringArray ;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    String type,type_insert,finish="";
    Thread myThread = null;
    BigTask runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquery);
        activities.add(this);





        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        if(intent.getStringExtra("type").equals("art"))
        {
            getSupportActionBar().setTitle("ART Queries");
            type="ART";
            type_insert="1";
        }
        else if(intent.getStringExtra("type").equals("bts"))
        {
            getSupportActionBar().setTitle("BTS Queries");
            type="BTS";
            type_insert="2";
        }
        else if(intent.getStringExtra("type").equals("ictc"))
        {
            getSupportActionBar().setTitle("ICTC  Queries");
            type="ICTC";
            type_insert="3";
        }
        else if(intent.getStringExtra("type").equals("sti"))
        {
            getSupportActionBar().setTitle("STI Queries");
            type="STI";
            type_insert="4";
        }
        else if(intent.getStringExtra("type").equals("ti"))
        {
            getSupportActionBar().setTitle("Condom Queries");
            type="TI";
            type_insert="5";
        }



        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", macAddress));
        nameValuePairs.add(new BasicNameValuePair("type", type));
        new SelectMessage().execute("");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        question=(EditText)findViewById(R.id.question);
        answer=(Button)findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!question.getText().toString().isEmpty()) {
                nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("question", question.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("user_id", macAddress));
                nameValuePairs.add(new BasicNameValuePair("type", type_insert));
                StrictMode.setThreadPolicy(policy);
                    new ARTQuestionPost().execute("");
                    String[][] temp = new String[myStringArray.length + 1][10];
                    System.arraycopy(myStringArray, 0, temp, 0, myStringArray.length);
                    myStringArray = temp;
                    myStringArray[temp.length - 1][0] = "question";
                    myStringArray[temp.length - 1][1] = question.getText().toString();
                    adapter = new MyRecyclerAdapter(ART_Query_Activity1.this, myStringArray);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.scrollToPosition(myStringArray.length - 1);
                    question.setText("");
                    if (runnable.isalive())
                        runnable.stopBigTask();
                }
            }
        });


    }

/*    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    nameValuePairs1 = new ArrayList<NameValuePair>();
                    nameValuePairs1.add(new BasicNameValuePair("user_id",macAddress ));
                    new SelectUnViewedMessage().execute("");
                }
                catch (Exception e) {
                    Log.e("select un viewed", String.valueOf(e));
                }
            }
        });
    }


    class CountDownRunner extends Thread implements Runnable {
        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e("exception 1", String.valueOf(e));
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    Log.e("exception 2", String.valueOf(e));
                }
            }
        }
    }*/
public class BigTask implements Runnable {
    private Thread bigTaskThread;
    public void startBigTask() {
        bigTaskThread = new Thread(this);
        bigTaskThread.start();
        Log.e("start", "start");
    }

    public void stopBigTask() {

        // Interrupt the thread so it unblocks any blocking call
        bigTaskThread.interrupt();

        // Change the states of variable
        bigTaskThread = null;

        if(finish.equals("finish"))
        {

            finish();
           // finish();
        }
        Log.e("stop", "stop");
    }
    boolean  isalive() {
        if( bigTaskThread != null)
        return  true;
    return  false;
    }
    // Method 1: Check the state of variable
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (currentThread == bigTaskThread) {
            try {
                nameValuePairs1 = new ArrayList<NameValuePair>();
                nameValuePairs1.add(new BasicNameValuePair("user_id", macAddress));
                nameValuePairs1.add(new BasicNameValuePair("type", type));
                new SelectUnViewedMessage().execute("");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("run", "run");
        }

    }
}

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }


    private class ARTQuestionPost extends AsyncTask<String, Void, String> {
        @Override
        protected  void onPreExecute()
        {
            /*dialog = ProgressDialog.show(ART_Query_Activity.this, "",
                    "sending...", true);*/
        }
        @Override
        protected String doInBackground(String... params) {
            String responseStr="";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://iyyam.compunet.in/ask_question.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response=httpclient.execute(httppost);
                myJSON = response.toString();
                myJSON  =myJSON.replaceAll("\\s+", "");

            }
            catch (Exception ex) {
                ex.printStackTrace();
                myJSON="networkerror";
              //  dialog.dismiss();
            }
            return myJSON;
        }
        @Override
        protected void onPostExecute(String result) {
            //dialog.dismiss();
            if(result.equals("networkerror")) {

            }
            else
            {
               // runnable.startBigTask();
                /*String[][] temp = new String[myStringArray.length +1][10];
                System.arraycopy(myStringArray, 0, temp, 0, myStringArray.length);
                myStringArray = temp;
                myStringArray[temp.length-1][0]="question";
                myStringArray[temp.length-1][1]=question.getText().toString();
                adapter = new MyRecyclerAdapter(ART_Query_Activity.this, myStringArray);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.scrollToPosition(myStringArray.length-1);
                Toast.makeText(getApplicationContext(),"your question is posted answer will get soon",Toast.LENGTH_LONG).show();*/
            }
        }

    }







    private class SelectMessage extends AsyncTask<String, Void, String> {
        @Override
        protected  void onPreExecute()
        {
            dialog = ProgressDialog.show(ART_Query_Activity1.this, "",
                    "verifying...", true);
        }
        @Override
        protected String doInBackground(String... params) {
            String responseStr="";

            try {
                HttpClient httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://iyyam.compunet.in/select_message.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response=httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                myJSON= responseStr.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("error", String.valueOf(ex));
                myJSON="networkerror";
                dialog.dismiss();
            }
            return myJSON;
        }
        @Override
        protected void onPostExecute(String result) {

            dialog.dismiss();
            if(result.equals("networkerror")) {
                Toast.makeText(getApplicationContext(), "your network is not connected please verify", Toast.LENGTH_LONG).show();
            }
            else {
                if (result.equals("failed")) {
                    Toast.makeText(getApplicationContext(), "user does not have any referral member", Toast.LENGTH_LONG).show();

                }
                else
                {
                    try {
                        JSONArray mJsonArray = new JSONArray(result);
                        myStringArray=new String[mJsonArray.length()][2];
                        JSONObject mJsonObject = new JSONObject();
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            if(!mJsonObject.getString("question").toString().isEmpty()) {
                                myStringArray[i][0] = "question";
                                myStringArray[i][1] = mJsonObject.getString("question");
                            }
                            else{
                                myStringArray[i][0] = "answer";
                                myStringArray[i][1] = mJsonObject.getString("answer");
                                if(mJsonObject.getString("view_details").toString().isEmpty())
                                {
                                    update_viewdetails=mJsonObject.getString("id")+","+update_viewdetails;
                                }
                            }

                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("errror", String.valueOf(e));
                    }
                    /*Runnable runnable = new CountDownRunner();
                    myThread = new Thread(runnable);
                    myThread.start();*/




                    adapter = new MyRecyclerAdapter(ART_Query_Activity1.this, myStringArray);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.scrollToPosition(myStringArray.length-1);
                    if(update_viewdetails!=null) {
                        update_viewdetails = update_viewdetails.replaceAll(",null", "");
                        nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("id", update_viewdetails));
                        new UpdateDetails().execute("");
                    }
                    else
                    {



                    }
                }
            }



            runnable = new BigTask();;
            if(runnable.isalive())
            {
                runnable.stopBigTask();
                runnable.startBigTask();
            }
            else {
                runnable.startBigTask();
            }
           /* nameValuePairs1 = new ArrayList<NameValuePair>();
            nameValuePairs1.add(new BasicNameValuePair("user_id", macAddress));
            nameValuePairs1.add(new BasicNameValuePair("type", type));
            new SelectUnViewedMessage().execute("");*/
        }







        private class UpdateDetails extends AsyncTask<String, Void, String> {
            @Override
            protected  void onPreExecute()
            {

            }
            @Override
            protected String doInBackground(String... params) {
                String responseStr="";
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    httppost = new HttpPost("http://iyyam.compunet.in/update_viewed_message.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response=httpclient.execute(httppost);
                    responseStr = EntityUtils.toString(response.getEntity());
                    myJSON = responseStr.toString();
                    myJSON  =myJSON.replaceAll("\\s+", "");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    myJSON="networkerror";

                }
                return myJSON;
            }
            @Override
            protected void onPostExecute(String result) {
                Log.e("result update",result);
                if(result.equals("networkerror")) {
                    Toast.makeText(getApplicationContext(),"networkerror",Toast.LENGTH_LONG).show();
                }
                else
                {

                   // Toast.makeText(getApplicationContext(),result+update_viewdetails,Toast.LENGTH_LONG).show();
                }
            }

        }

    }


    private class SelectUnViewedMessage extends AsyncTask<String, Void, String> {
        @Override
        protected  void onPreExecute()
        {
            if(runnable.isalive())
             runnable.stopBigTask();
        }
        @Override
        protected String doInBackground(String... params) {
            String responseStr="";

            try {
                HttpClient httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://iyyam.compunet.in/select_unviewed_message.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
                response=httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                myJSON= responseStr.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("error", String.valueOf(ex));

                Log.e("doinbackerror","doinbackerror");
                myJSON="networkerror";
            }
            Log.e("doinback","doinback");
            return myJSON;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e("onpost", "onpost" + result);

            //Toast.makeText(getApplicationContext(),"image uplaod",Toast.LENGTH_SHORT).show();
            if(result.equals("networkerror")) {

            }
            else {
                if (result.equals("failed")) {


                }
                else
                {
                    try {
                        JSONArray mJsonArray = new JSONArray(result);
                        String[][] temp = new String[myStringArray.length +mJsonArray.length()][10];
                        System.arraycopy(myStringArray, 0, temp, 0, myStringArray.length);
                        myStringArray = temp;
                        JSONObject mJsonObject = new JSONObject();
                        for (int i = 0; i < mJsonArray.length(); i++) {
                             mJsonObject = mJsonArray.getJSONObject(i);
                            if(!mJsonObject.getString("question").toString().isEmpty()) {
                                myStringArray[myStringArray.length-mJsonArray.length()+i][0] = "question";
                                myStringArray[myStringArray.length-mJsonArray.length()+i][1] = mJsonObject.getString("question");
                            }
                            else{
                                myStringArray[myStringArray.length-mJsonArray.length()+i][0] = "answer";
                                myStringArray[myStringArray.length-mJsonArray.length()+i][1] = mJsonObject.getString("answer");
                            }

                        }
                        adapter = new MyRecyclerAdapter(ART_Query_Activity1.this, myStringArray);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.scrollToPosition(myStringArray.length-1);
                    }
                    catch (Exception e)
                    {
                        Log.e("Exception", String.valueOf(e));
                    }

                }
            }



            if (!runnable.isalive()&&!finish.equals("finish"))
                runnable.startBigTask();




        }

    }


















    private class ClearMessage extends AsyncTask<String, Void, String> {
        @Override
        protected  void onPreExecute()
        {
            if (runnable.isalive())
                runnable.stopBigTask();
            dialog = ProgressDialog.show(ART_Query_Activity1.this, "",
                    "Clearing...", true);
        }
        @Override
        protected String doInBackground(String... params) {
            String responseStr="";

            try {
                HttpClient httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://iyyam.compunet.in/clear_message.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response=httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                myJSON= responseStr.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("error", String.valueOf(ex));
                myJSON="networkerror";
                dialog.dismiss();
            }
            return myJSON;
        }
        @Override
        protected void onPostExecute(String result) {

            dialog.dismiss();
            if(result.equals("networkerror")) {
                Toast.makeText(getApplicationContext(), "your network is not connected please verify", Toast.LENGTH_LONG).show();
            }
            else {
                if (result.equals("failed")) {
                    // Toast.makeText(getApplicationContext(), "user does not have any referral member", Toast.LENGTH_LONG).show();

                }
                else {
                    myStringArray=new String[0][2];
                    adapter = new MyRecyclerAdapter(ART_Query_Activity1.this, myStringArray);
                    mRecyclerView.setAdapter(adapter);

                }

                if (!runnable.isalive()&&!finish.equals("finish"))
                    runnable.startBigTask();
            }

        }



    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_query, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.clear:
                nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("user_id", macAddress));
                nameValuePairs.add(new BasicNameValuePair("type", type));
                StrictMode.setThreadPolicy(policy);
                new ClearMessage().execute("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        finish="finish";
        if (runnable.isalive())
            runnable.stopBigTask();
        else
        {
            runnable.startBigTask();
            runnable.stopBigTask();
        }
        // finish();
        // onDestroy();
        }

}
