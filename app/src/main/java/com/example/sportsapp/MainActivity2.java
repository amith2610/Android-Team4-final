package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    static String accessTkn;
    String aT;
    static TextView text;
    static JSONObject idBookingLog;
    private RequestQueue queue;
    JsonArrayRequest arrayRequest;
    LinearLayout availablelist;
    LinearLayout bookedlist;
    TextView bookedmsg;
    JSONObject data;
    private RequestQueue bookqueue;
    TextView text3;
    TextView text5;
    Boolean value;
    int bookPress;
    BookedResource userBookedData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bookedlist = (LinearLayout) findViewById(R.id.bookedlist);
        text = (TextView) findViewById(R.id.bookedmsg);
        availablelist=(LinearLayout) findViewById(R.id.availablelist);
        userBookedData = new BookedResource();




        text3 = (TextView) findViewById(R.id.textView3);
        text5 = (TextView) findViewById(R.id.textView5);




        String URL = "https://sport-resources-booking-api.herokuapp.com/ResourcesPresent";
        queue = Volley.newRequestQueue(this);
        arrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String newResponse = response.toString();
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Resource[] resources = gson.fromJson(newResponse,Resource[].class);
                        int len = resources.length;
                        for(int i=0;i<len;i++){
                            Resource unit = resources[i];
                            add(availablelist,unit);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }
        };

        queue.add(arrayRequest);
        Booked();


    }

    private void Booked() {
        idBookingLog = new JSONObject();

        try {
            idBookingLog.put("id",MainActivity.sroll);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String URL_bookingLog = "https://sport-resources-booking-api.herokuapp.com/userBookingslog";

        JsonObjectRequest objectRequest_bookingLog = new JsonObjectRequest(Request.Method.POST,
                URL_bookingLog,
                idBookingLog,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        value = true;
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        userBookedData = gson.fromJson(String.valueOf(response),
                                BookedResource.class);
                        text.setText(userBookedData.getResourceName());

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        value = false;

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + accessTkn);
                return params;
            }
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", MainActivity.sroll);
                return params;
            }
        };
        queue.add(objectRequest_bookingLog);


    }

    private void add(LinearLayout list, final Resource unit) {
        String r = unit.getResourceName();
        String c = "Count: " + unit.getResourcesAvailable().toString() + "/" + unit.getCount().toString();
        LinearLayout resourceset = new LinearLayout(this);
        resourceset.setOrientation(LinearLayout.HORIZONTAL);
        resourceset.setBackground(getResources().getDrawable(R.drawable.lay_bg));
        resourceset.setPadding(18,32,18,32);
        LinearLayout resourceE1 = new LinearLayout(this);
        resourceE1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout resourceE2 = new LinearLayout(this);
        //resourceE1.setBackgroundColor(Color.RED);
        //resourceE2.setBackgroundColor(Color.GREEN);
        resourceE1.setPadding(40, 30, 0, 20);
        resourceE2.setPadding(0, 40, 30, 40);
        resourceE1.setMinimumWidth(550);
        TextView tvResourse = new TextView(this);
        TextView tvCount = new TextView(this);
        Space space = new Space(this);
        tvResourse.setText(r);
        tvResourse.setTextColor(Color.WHITE);
        tvResourse.setTextSize(18);
        tvResourse.setTypeface(Typeface.SERIF);
        tvCount.setText(c);
        tvCount.setTextColor(Color.WHITE);
        tvCount.setTextSize(12);
        TextView bookbtn = new TextView(this);
        bookbtn.setText("Book");
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value){
                    Toast.makeText(getApplicationContext(),"BOOKING UNSUCCESSFUL",Toast.LENGTH_LONG).show();

                }
                else {
<<<<<<< HEAD

                        book(MainActivity.sroll,unit.getResourceName());

                    }
=======
                   book(MainActivity.sroll,unit.getResourceName());

                }
>>>>>>> 2378129e51bc02e9c0babbe45cd95ec530fb7cdf

                }


        });
        bookbtn.setPadding(40, 20, 40, 20);
        bookbtn.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        //bookbtn.setBackgroundColor(Color.WHITE);
        bookbtn.setTextColor(getResources().getColor(R.color.layoutbg));
        resourceE1.addView(tvResourse);
        resourceE1.addView(tvCount);
        resourceE2.addView(bookbtn);
        resourceset.addView(resourceE1);
        resourceset.addView(resourceE2);
        list.addView(resourceset);
        list.addView(space, 0, 60);
        list.setPadding(50, 0, 50, 0);
        bookedmsg = (TextView) findViewById(R.id.bookedmsg);
    }



    private void book(final String sroll, final String resourceName) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String date = sdf.format(new Date());
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        String time = sd.format(new Date());


<<<<<<< HEAD
        JSONObject bookingDetails = new JSONObject();

=======





        JSONObject bookingDetails = new JSONObject();

>>>>>>> 2378129e51bc02e9c0babbe45cd95ec530fb7cdf
        try {
            bookingDetails.put("id",sroll);
            bookingDetails.put("name",resourceName);
            bookingDetails.put("day",date);
            bookingDetails.put("reservation_time", "12:10:00");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String URL1= "https://sport-resources-booking-api.herokuapp.com/bookResource";
        bookqueue=Volley.newRequestQueue(this);




        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST,
                URL1,
                bookingDetails,
                new Response.Listener<JSONObject>() {
<<<<<<< HEAD
                    @Override
                    public void onResponse(JSONObject response) {
=======
            @Override
            public void onResponse(JSONObject response) {
>>>>>>> 2378129e51bc02e9c0babbe45cd95ec530fb7cdf

                        text.setText("Booking Successful");


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                if (value || bookPress==1) {
//
//                    Toast.makeText(getApplicationContext(),"BOOKING UNSUCCESSFUL",Toast.LENGTH_LONG).show();
//                    if (bookPress==0){
//                        bookPress+=1;
//                    }
//                    }
//                else {
//                    Toast.makeText(getApplicationContext(),"BOOKING SUCCESSFUL",Toast.LENGTH_LONG).show();
//                }

                Toast.makeText(getApplicationContext(),"BOOKING SUCCESSFUL",Toast.LENGTH_LONG).show();
                Booked();



            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

<<<<<<< HEAD
                Map<String, String> map = new HashMap<String, String>();
                map.put("id",sroll);
                map.put("name",resourceName);
                map.put("day",date);
                map.put("reservation_time","12:10:00");
                return map;
=======
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id",sroll);
                    map.put("name",resourceName);
                    map.put("day",date);
                    map.put("reservation_time","12:10:00");
                    return map;
>>>>>>> 2378129e51bc02e9c0babbe45cd95ec530fb7cdf
            }


        };
        bookqueue.add(objectRequest);




    }


}
