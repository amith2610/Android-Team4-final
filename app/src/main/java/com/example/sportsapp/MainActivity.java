package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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


        Button login = (Button) findViewById(R.id.login);
        EditText rollno=(EditText) findViewById(R.id.rollno);
        EditText password=(EditText) findViewById(R.id.password);

        login.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        EditText rollno=(EditText) findViewById(R.id.rollno);




                    }
                }



        );

    }
}