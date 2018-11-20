package com.example.abhirami.clogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackComplaint extends AppCompatActivity {
    ArrayList<String> complaints=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_complaint);


        complaints.add("Complaint1:");
        complaints.add("Complaint2:");
        complaints.add("Complaints3:");

        final String sendingstring[]={"abc","def","ghi"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, complaints);
        ListView listView=(ListView)findViewById(R.id.complaintlist);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               Intent viewcomplaintIntent=new Intent(TrackComplaint.this,ViewComplaint.class);
               viewcomplaintIntent.putExtra("complaintno",sendingstring);
               startActivity(viewcomplaintIntent);

            }
        });
//https://console.firebase.google.com/u/0/project/clogger-1a818/database/clogger-1a818/data
    }
}
