package com.compunetconnections.iyyamthavir;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;

import static com.google.android.gms.internal.zzip.runOnUiThread;

public class AutoStartUp extends Service {










    HttpPost httppost;
    HttpResponse response;
    String myJSON="",macAddress;
    ProgressDialog dialog = null;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    ArrayList<NameValuePair> nameValuePairs;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();


        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", macAddress));
        new ARTQuestionPost().execute("");

        Thread myThread = null;
        Runnable runnable = new CountDownRunner();
        myThread = new Thread(runnable);
     //   myThread.start();
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    new ARTQuestionPost().execute("");
                } catch (Exception e) {
                }
            }
        });
    }


    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }





    private class ARTQuestionPost extends AsyncTask<String, Void, String> {
        @Override
        protected  void onPreExecute()
        {

        }
        @Override
        protected String doInBackground(String... params) {
            StrictMode.setThreadPolicy(policy);
            try {
                HttpClient httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://iyyam.compunet.in/notification.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                response=httpclient.execute(httppost);
                myJSON = EntityUtils.toString(response.getEntity());
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
            //dialog.dismiss();
            if(result.equals("failed")) {

            }
            else if(result.equals("networkerror")) {

            }
            else if(result.equals("0")) {
            }
            else
            {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                @SuppressWarnings("deprecation")
                Notification notification = new Notification(R.drawable.ilogo,
                        "New Message", System.currentTimeMillis());
                Intent notificationIntent = new Intent(AutoStartUp.this, StartActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(AutoStartUp.this, 0,
                        notificationIntent, 0);

                notification.setLatestEventInfo(AutoStartUp.this, "Message Count",
                        result, pendingIntent);
                notificationManager.notify(9999, notification);
            }
        }

    }





}
