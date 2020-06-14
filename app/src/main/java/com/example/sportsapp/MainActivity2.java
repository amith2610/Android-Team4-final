package com.example.sportsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FloatingActionButton addbtn;

        addbtn=(FloatingActionButton) findViewById(R.id.addbtn);
         final LinearLayout list;
         list=(LinearLayout) findViewById(R.id.list);

         addbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 add(list);
             }
         });

    }

    private void add(LinearLayout list) {
        String r="Resourse";
        String c="count";
        LinearLayout newResourse=new LinearLayout(this);
        newResourse.setOrientation(LinearLayout.VERTICAL);
        newResourse.setBackgroundColor(getResources().getColor(R.color.layoutbg));
        newResourse.setPadding(18,32,18,32);
        TextView tvResourse= new TextView(this);
        TextView tvCount= new TextView(this);
        Space space=new Space(this);
        tvResourse.setText(r);
        tvCount.setText(c);
        newResourse.addView(tvResourse);
        newResourse.addView(tvCount);
        list.addView(newResourse);
        list.addView(space,0,60);
        list.setPadding(50,0,50,);




    }

}