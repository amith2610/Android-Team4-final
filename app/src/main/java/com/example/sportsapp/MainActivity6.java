package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity6 extends AppCompatActivity {
    EditText roll;
    Button submit;
    JSONObject id;
    private RequestQueue queueE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        roll = findViewById(R.id.roll);
        submit = findViewById(R.id.button2);
        String URLE = "https://sport-resources-booking-api.herokuapp.com/forgot_password";
        queueE = Volley.newRequestQueue(this);
        id = new JSONObject();
        try {
            id.put("id",roll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}