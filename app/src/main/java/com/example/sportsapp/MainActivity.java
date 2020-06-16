package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button logbtn = (Button) findViewById(R.id.logbtn);
        EditText roll=(EditText) findViewById(R.id.roll);
        EditText pwd=(EditText) findViewById(R.id.pwd);

        String URL = "https://sport-resources-booking-api.herokuapp.com/login";
        data = new JSONObject();
        try {
            data.put("id","160118733012");
            data.put("password","abc123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queue = Volley.newRequestQueue(this);
        objectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String accessTkn = response.getString("access_token");
                            MainActivity2.text.setText(accessTkn);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(objectRequest);
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }



}