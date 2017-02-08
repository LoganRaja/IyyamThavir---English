package com.compunetconnections.iyyamthavir;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class LocationActivity extends FragmentActivity implements GoogleMap.OnMyLocationChangeListener ,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Marker mMarker;
    ArrayList<LatLng> markerPoints;
    String currentlocation=null;
    LatLng loc=null;
    TextView from,toaddress,distance_and_time;
    Polyline polyline=null;


Button backPressed;

    HttpPost httppost;
    HttpResponse response;
    String myJSON="",macAddress,type,heading;
    ProgressDialog dialog = null;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    ArrayList<NameValuePair> nameValuePairs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        from = (TextView) findViewById(R.id.from);
        toaddress=(TextView)findViewById(R.id.to);
        distance_and_time=(TextView)findViewById(R.id.distance_and_time);
        backPressed=(Button)findViewById(R.id.back_pressed);



        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        heading=intent.getStringExtra("heading");
        if(type.equals("pptct"))
        {
            type="ictc";
        }

        if(type.equals("art"))
        {
            from.setText("CST Locations");
        }
        else if(type.equals("bts"))
        {
            from.setText("BTS Locations");
        }
        else if(type.equals("ictc"))
        {
            if (heading.equals("pptct")){
            from.setText("PPTCT Locations");}
            else{
                from.setText("ICTC Locations");
            }
        }
        else if(type.equals("sti"))
        {
            from.setText("STI Locations");
        }
        else if(type.equals("ti"))
        {
            from.setText("Condom Locations");
        }
        /*else if(type.equals("pptct"))
        {
            from.setText("ICTC Locations");
        }*/



        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                       /* Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                        intent.putExtra("enabled", true);
                        this.ctx.sendBroadcast(intent);

                        String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                        if (!provider.contains("gps")) { //if gps is disabled
                            final Intent poke = new Intent();
                            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                            poke.setData(Uri.parse("3"));
                            this.ctx.sendBroadcast(poke);
                        }*/
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful   in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationChangeListener(this);
                mMap.setOnMarkerClickListener(this);
            }
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        Log.e("enter","function start");
        try {
            Log.e("try","try start");
            InputStream is = getAssets().open("location.json");
            Log.e("file","file start");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("catch", String.valueOf(ex));
            return null;
        }
        Log.e("json",json);
        return json;


    }


    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onMyLocationChange(Location location) {



        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        heading=intent.getStringExtra("heading");
        if(type.equals("art"))
        {
            from.setText("CST Locations");
        }
        else if(type.equals("bts"))
        {
            from.setText("BTS Locations");
        }
        else if(type.equals("ictc"))
        {
            if (heading.equals("pptct")){
                from.setText("PPTCT Locations");}
            else{
                from.setText("ICTC Locations");
            }
        }
        else if(type.equals("sti"))
        {
            from.setText("STI Locations");
        }
        else if(type.equals("ti"))
        {
            from.setText("Condom Locations");
        }
        /*else if(type.equals("pptct"))
        {
            from.setText("PPTCT Locations");
        }*/

        backPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loc = new LatLng(location.getLatitude(), location.getLongitude());
        Geocoder geocoder =new Geocoder(this, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();


            try {
                JSONArray mJsonArray = new JSONArray(loadJSONFromAsset());
                JSONObject mJsonObject = new JSONObject();
                for (int i = 0; i < mJsonArray.length(); i++) {
                    mJsonObject = mJsonArray.getJSONObject(i);
                    if(!mJsonObject.getString(type).equals("0"))
                    //if(!mJsonObject.getString("location").isEmpty()&&mJsonObject.getString("district_name").trim().equalsIgnoreCase(address.get(0).getLocality()))
                    {
                        String[] latlong=mJsonObject.getString("location").split(",");
                        LatLng latilong = new LatLng(Double.parseDouble(latlong[0]),Double.parseDouble(latlong[1]));
                        Marker mMarker = mMap.addMarker(new MarkerOptions().position(latilong).title(mJsonObject.getString("faclity_name")).icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                }
            } catch (JSONException e) {
                Log.e("catch", String.valueOf(e));
            }

             //This is the complete address.
        } catch (IOException e) {}
        catch (NullPointerException e) {}
       // LatLng loc1 = new LatLng(13.1062, 80.2867);
        if(currentlocation==null){
            mMarker = mMap.addMarker(new MarkerOptions().position(loc));

            /*if(intent.getStringExtra("type").equals("ictc"))
            {
                for(int i=0;i<5;i++)
                {
                    if(i==0){
                        loc1= new LatLng(13.115258, 80.223144);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title(" Government Peripheral Hospital, Periyar Nagar").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==1){
                        loc1= new LatLng(13.113555, 80.296131);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Govt. Raja Sir Ramasamy Mudaliyar Lying in Hospital, Royapuram").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==2){
                        loc1= new LatLng(13.08268, 80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title(" GOVT. STANLEY MEDICAL COLLAGE HOSPITAL ICTC ").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==3){
                        loc1= new LatLng(13.08268, 80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title(" Government Siddha Medical College").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==4){
                        loc1= new LatLng(13.073226, 80.260921);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("TNSACS Office").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                }
            }
            if(intent.getStringExtra("type").equals("bts"))
            {
                for(int i=0;i<4;i++)
                {
                    if(i==0){
                        loc1= new LatLng(13.08268, 80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("CAPACS GOVERNMENT ROYAPETTAH HOSPITAL Blood Bank").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==1){
                        loc1= new LatLng(13.078034, 80.223612);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title(" The Tamilnadu Dr.M.G.R.Medical University").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==2){
                        loc1= new LatLng(13.08279, 80.270729);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Landsteiner Lakshmi Memorial Research Foundation Blood Bank").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==3){
                        loc1= new LatLng(13.082680, 80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("MADRAS EGMORE LIONS BLOOD BANK AND RESEARCH FOUNDATIONS").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                }
            }
            if(intent.getStringExtra("type").equals("art"))
            {
                for(int i=0;i<5;i++)
                {
                    if(i==0){
                        loc1= new LatLng(13.074006, 80.276767);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Rajiv Gandhi Government General Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==1){
                        loc1= new LatLng(13.106054, 80.285952);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Government Stanley Medical College Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==2){
                        loc1= new LatLng(13.076523, 80.237811);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Government Kilpauk Medical College & Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==3){
                        loc1= new LatLng(13.073226, 80.260921);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Institute of Obstetrics and Gynecology & Government Hospital for Women and Children").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==4){
                        loc1= new LatLng(13.073358, 80.256306);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Institute of Child Health and Hospital for Children").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                }
            }
            if(intent.getStringExtra("type").equals("sti"))
            {
                for(int i=0;i<3;i++)
                {
                    if(i==0){
                        loc1= new LatLng(13.08268, 80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("DSRC Government Kilpauk Medical College").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==1){
                        loc1= new LatLng(13.053852, 80.264622);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title(" GOVERNMENT ROYAPETTAH HOSPITAL").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }
                    if(i==2){
                        loc1= new LatLng(13.08268,80.270718);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("GOVERNMENT STANLEY HOSPITAL").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }

                }
            }
            if(intent.getStringExtra("type").equals("ti"))
            {
                for(int i=0;i<1;i++)
                {
                    if(i==0){
                        loc1= new LatLng(13.073226,80.260921);
                        Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("TNSACS Office").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
                    }

                }
            }*/
           // Marker    mMarker = mMap.addMarker(new MarkerOptions().position(loc1).title("Stanley Medical College").icon(BitmapDescriptorFactory.fromResource(R.drawable.facility)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 11.0f));
            currentlocation="notnull";
        }

    }


    public void addressFinder(LatLng location)
    {
        Geocoder geocoder =new Geocoder(this, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geocoder.getFromLocation(location.latitude, location.longitude, 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();
            for (int i=0; i<maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                builder.append(addressStr);
                builder.append(" ");
            }

            String fnialAddress = builder.toString();
            if(Pattern.compile(Pattern.quote("chennai"), Pattern.CASE_INSENSITIVE).matcher(fnialAddress).find())
            {
                Log.e("address",fnialAddress);
            }
            else
            {
                Log.e("else address",fnialAddress);
            }
                toaddress.setText(fnialAddress);
        } catch (IOException e) {}
        catch (NullPointerException e) {}
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.e("exception", String.valueOf(e));
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
    if(polyline!=null)
        polyline.remove();
        LatLng origin = marker.getPosition();
        LatLng dest = loc;
        addressFinder(origin);
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, dest);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);

        return false;
    }



    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if(result.size()<1){
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){    // Get distance from the list
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);
            }

            distance_and_time.setText(distance + "  " + duration);

            // Drawing polyline in the Google Map for the i-th route
            polyline= mMap.addPolyline(lineOptions);
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
                httppost = new HttpPost("http://iyyam.compunet.in/location_address.php");
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
            Log.e("result",result);
            //dialog.dismiss();
            if(result.equals("failed")) {

            }
            else if(result.equals("networkerror")) {

            }
            else if(result.equals("0")) {
            }
            else
            {

            }
        }

    }

}
