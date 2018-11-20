package com.example.abhirami.clogger;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.images.internal.LoadingImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LogNewComplaint extends AppCompatActivity {
    String phoneNo;
    String department;
    String location;
    String additionalinformation;
    String supervisorid;
    ArrayList<String> complaintlist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_new_complaint);

    }
    public void submitnewcomplaint(View view)
    {
        EditText editText2=(EditText)findViewById(R.id.getdepartment);
        String department=editText2.getText().toString();
        EditText editText3=(EditText)findViewById(R.id.getLocation);
        String location=editText3.getText().toString();
        EditText editText4=(EditText)findViewById(R.id.getinformation);
        String additionalinfo=editText4.getText().toString();
        FirebaseUser currentuser=FirebaseAuth.getInstance().getCurrentUser();
        Complaint newComplaint = new Complaint(department,location,additionalinfo,currentuser.getUid());


        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =  database.getReference();
        DatabaseReference ref=databaseReference.child("Supervisor").child(department);

        // Attach a listener to read supervisor id
         ValueEventListener supervisoridlistener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                supervisorid = dataSnapshot.getValue().toString();
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        };
         ref.addValueEventListener(supervisoridlistener);
        newComplaint.supervisorid=supervisorid;
        ref.removeEventListener(supervisoridlistener);

        ValueEventListener gettinguser=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dts:dataSnapshot.getChildren())
                {
                    Log.v("Fiind",dts.getValue().toString());
                    complaintlist.add(dts.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        DatabaseReference uref=databaseReference.child("User").child(currentuser.getUid()).child("complaintlist");
        uref.addValueEventListener(gettinguser);
        Log.v("Fiind",complaintlist.toString());
        //add new complaint
        DatabaseReference postsRef = databaseReference.child("Complaint");
        DatabaseReference newPostRef = postsRef.push();
        newComplaint.complaintid=newPostRef.getKey();
        newPostRef.setValue(newComplaint);


        //get complaintlist of user

//        DatabaseReference newdbr=FirebaseDatabase.getInstance().getReference().child("User").child(currentuser.getUid()).child("complaintlist");
//        newdbr.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.v("Fiind","indatasnap");
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    Log.v("Fiind",postSnapshot.getValue().toString());
//                    complaintlist.add(postSnapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.v("Fiind","database error");
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
//
////        newdbr.addValueEventListener(complaintlistlistenser);
////
//        complaintlist.add(newComplaint.getComplaintid());
//        Map<String, Object> complaintlistUpdates = new HashMap<>();
//        complaintlistUpdates.put("complaintlist", complaintlist);
//        DatabaseReference newdbrr=FirebaseDatabase.getInstance().getReference().child("User").child(currentuser.getUid()).child("complaintlist");
//
//        newdbrr.setValue(complaintlist);
//

        finish();



//        DatabaseReference mRef =  database.getReference().child("Complaint");
//        String key=mRef.push().getKey();
//        newComplaint.complaintid=key;
//        mRef.push().setValue(newComplaint);
//        Log.v("complaintt",key);


        //startActivity(new Intent(LogNewComplaint.this,UserPage.class));


    }
}
