package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
    JSONObject testJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bookedlist = (LinearLayout) findViewById(R.id.bookedlist);
        text = (TextView) findViewById(R.id.bookedmsg);
        availablelist=(LinearLayout) findViewById(R.id.availablelist);
        text3 = (TextView) findViewById(R.id.textView3);




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

                        text3.setText(response.toString());

                        }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        text3.setText(error.toString());
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
                params.put("id", "160118733012");
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
                book(MainActivity.sroll,unit.getResourceName());


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
        data=new JSONObject();
        String URL1= "https://sport-resources-booking-api.herokuapp.com/bookResource";
        bookqueue=Volley.newRequestQueue(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String date = sdf.format(new Date());


        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        String time = sd.format(new Date());


        try {
            data.put("id","160118733012");
            data.put("name","Basket Balls");
            data.put("day", "2020-06-19");
            data.put("reservation_time","12:10:00");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST,
                URL1, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                text.setText("Booking Successful");


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText(error.toString());
                Toast toast=Toast.makeText(getApplicationContext(),"Booking Unsuccessful",Toast.LENGTH_SHORT);
                toast.show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id",sroll);
                params.put("name",resourceName);
                params.put("day", date);
                params.put("reservation_time","12:10:00");
                return params;
            }
        };
        bookqueue.add(objectRequest);




    }


}
