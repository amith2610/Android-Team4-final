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
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    JsonArrayRequest arrayRequestB;
    private RequestQueue queueB;
    LinearLayout bookedhistorylist;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bookedhistorylist = (LinearLayout) findViewById(R.id.bookedhistorylist);


        queueB = Volley.newRequestQueue(this);
        String URLB =  "https://sport-resources-booking-api.herokuapp.com/allBookings";
        arrayRequestB = new JsonArrayRequest(Request.Method.GET,
                URLB,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String newResponse = response.toString();
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        BookedResource[] bookedResources = gson.fromJson(newResponse,BookedResource[].class);
                        int len = response.length();
                        for(int i=0;i<len;i++) {

                            add(bookedhistorylist,
                                    bookedResources[i].getUserId(),
                                    bookedResources[i].getResourceName(),
                                    bookedResources[i].getDay(),
                                    bookedResources[i].getStatus(),
                                    bookedResources[i].getReturnDay());

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
                params.put("Authorization", "Bearer "+MainActivity2.accessTkn);
                return params;
            }
        };
        queueB.add(arrayRequestB);



    }




    private void add(LinearLayout bookedhistorylist, String userId, String resourceName, String day, Integer status, Object returnDay) {
        String r = "resource";
        String c = "date";
        LinearLayout bookedset = new LinearLayout(this);
        bookedset.setOrientation(LinearLayout.HORIZONTAL);
        bookedset.setBackground(getResources().getDrawable(R.drawable.lay_bg));
        bookedset.setPadding(18,32,18,32);
        LinearLayout bookedE1 = new LinearLayout(this);
        bookedE1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout bookedE2 = new LinearLayout(this);
        //resourceE1.setBackgroundColor(Color.RED);
        //resourceE2.setBackgroundColor(Color.GREEN);
        bookedE1.setPadding(40, 30, 0, 20);
        bookedE2.setPadding(0, 40, 30, 40);
        //resourceE1.setMinimumWidth(550);
        TextView tvResourse = new TextView(this);
        TextView tvDate = new TextView(this);
        Space space = new Space(this);
        tvResourse.setText(r);
        tvResourse.setTextColor(Color.WHITE);
        tvResourse.setTextSize(18);
        tvResourse.setTypeface(Typeface.SERIF);
        tvDate.setText(c);
        tvDate.setTextColor(Color.WHITE);
        tvDate.setTextSize(12);
        TextView returnbtn = new TextView(this);
        returnbtn.setText("Book");
        returnbtn.setPadding(40, 20, 40, 20);
        returnbtn.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        //bookbtn.setBackgroundColor(Color.WHITE);
        returnbtn.setTextColor(getResources().getColor(R.color.layoutbg));
        bookedE1.addView(tvResourse);
        bookedE1.addView(tvDate);
        bookedE2.addView(returnbtn);
        bookedset.addView(bookedE1);
        bookedset.addView(bookedE2);
        bookedhistorylist.addView(bookedset);
        bookedhistorylist.addView(space, 0, 60);
        bookedhistorylist.setPadding(50, 0, 50, 0);
    }

}
