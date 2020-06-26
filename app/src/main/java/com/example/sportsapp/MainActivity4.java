package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.sportsapp.MainActivity2.accessTkn;

public class MainActivity4 extends AppCompatActivity {
    TextView user_id;
    TextView user_pwd;
    private RequestQueue queueC;
    JsonArrayRequest arrayRequestC;
    private JSONObject userid;
    TextView cpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        user_id = (TextView) findViewById(R.id.textView);
        user_pwd = (TextView) findViewById(R.id.textView6);
        queueC = Volley.newRequestQueue(this);
        cpwd = (TextView) findViewById(R.id.cpwd);
        userid = new JSONObject();
        try {
            userid.put("id",MainActivity.sroll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String URLC = "https://sport-resources-booking-api.herokuapp.com/users";
        JsonObjectRequest users = new JsonObjectRequest(Request.Method.GET,
                URLC,
                userid,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Log.d("History", response.getJSONObject(i).getString("id"));
                           // if (response.getJSONObject("id").toString().trim().equals(MainActivity.sroll)) {
                                //tv4.setText(tv4.getText().toString() + "\n" + response.getJSONObject(i).getString("resource_name") + " " + response.getJSONObject(i).getString("day"));
                                user_pwd.setText(response.getString("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + MainActivity2.accessTkn);
                return params;
            }
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", MainActivity.sroll);
                return params;
            }
        };

                queueC.add(users);

         cpwd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openActivity5();
             }
         });

    }
    public void openActivity5() {
        Intent intent3 = new Intent(this,MainActivity5.class);
        startActivity(intent3);

    }
}
