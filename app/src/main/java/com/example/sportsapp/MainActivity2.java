package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {
    static TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FloatingActionButton addbtn;
        text = (TextView) findViewById(R.id.bookedmsg);
        addbtn=(FloatingActionButton) findViewById(R.id.addbtn);
         final LinearLayout availablelist;
         availablelist=(LinearLayout) findViewById(R.id.availablelist);

         addbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 add(availablelist);
             }
         });

    }

    private void add(LinearLayout list) {
        String r="Resourse";
        String c="Count(5/5)";
        LinearLayout resourceset=new LinearLayout(this);
        resourceset.setOrientation(LinearLayout.HORIZONTAL);
        resourceset.setBackground(getResources().getDrawable(R.drawable.lay_bg));
        //resourceset.setPadding(18,32,18,32);
        LinearLayout resourceE1=new LinearLayout(this);
        resourceE1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout resourceE2=new LinearLayout(this);
        //resourceE1.setBackgroundColor(Color.RED);
        //resourceE2.setBackgroundColor(Color.GREEN);
        resourceE1.setPadding(40,30,0,20);
        resourceE2.setPadding(350,40,30,40);
        TextView tvResourse= new TextView(this);
        TextView tvCount= new TextView(this);
        Space space=new Space(this);
        tvResourse.setText(r);
        tvResourse.setTextColor(Color.WHITE);
        tvResourse.setTextSize(18);
        tvResourse.setTypeface(Typeface.SERIF);
        tvCount.setText(c);
        tvCount.setTextColor(Color.WHITE);
        tvCount.setTextSize(12);
        TextView bookbtn=new TextView(this);
        bookbtn.setText("Book");
        bookbtn.setPadding(40,20,40,20);
        bookbtn.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        //bookbtn.setBackgroundColor(Color.WHITE);
        bookbtn.setTextColor(getResources().getColor(R.color.layoutbg));
        resourceE1.addView(tvResourse);
        resourceE1.addView(tvCount);
        resourceE2.addView(bookbtn);
        resourceset.addView(resourceE1);
        resourceset.addView(resourceE2);
        list.addView(resourceset);
        list.addView(space,0,60);
        list.setPadding(50,0,50,0);




    }

}