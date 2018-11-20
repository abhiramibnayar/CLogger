package com.example.abhirami.clogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewComplaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint);
        Intent startintent=getIntent();
        String[] sendstring=new String[3];
        sendstring=startintent.getStringArrayExtra("complaintno");
        Log.v("pos0",sendstring[0]);
        Log.v("pos1",sendstring[1]);
        Log.v("pos2",sendstring[2]);

        TextView textView1=findViewById(R.id.displayID);
        textView1.setText(sendstring[0]);
        TextView textView2=findViewById(R.id.displaycomplainer);
        textView2.setText(sendstring[1]);
        TextView textView3=findViewById(R.id.displaysupervisor);
        textView3.setText(sendstring[2]);


    }
}
