package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button logbtn = (Button) findViewById(R.id.logbtn);
        EditText roll=(EditText) findViewById(R.id.roll);
        EditText pwd=(EditText) findViewById(R.id.pwd);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }



}