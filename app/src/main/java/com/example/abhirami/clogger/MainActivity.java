package com.example.abhirami.clogger;

import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public EditText loginEmailId, logInpasswd;
    Button btnLogIn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    String valueofuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmailId=findViewById(R.id.email);
        logInpasswd=findViewById(R.id.password);
        btnLogIn=findViewById(R.id.loginbutton);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(MainActivity.this, UserPage.class);
                    startActivity(I);
                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = loginEmailId.getText().toString();
                String userPaswd = logInpasswd.getText().toString();
                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    logInpasswd.setError("Enter Password!");
                    logInpasswd.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                 user.getUid();


                                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                                DatabaseReference ref = database.child("User").child(user.getUid()).child("type");

                                ValueEventListener postListener = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        valueofuser=dataSnapshot.getValue().toString();
                                        Log.v("Fiind",valueofuser);
                                        if(valueofuser.equals("user"))
                                            startActivity(new Intent(MainActivity.this, UserPage.class));
                                        else if(valueofuser.equals("worker"))
                                            startActivity(new Intent(MainActivity.this, WorkerPage.class));
                                        else if(valueofuser.equals("supervisor"))
                                            startActivity(new Intent(MainActivity.this, SupervisorPage.class));
                                        else
                                            Toast.makeText(MainActivity.this,"Unknown",Toast.LENGTH_SHORT).show();
                                                                            }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.v("Fiind","database error");
                                    }
                                };
                                ref.addValueEventListener(postListener);



                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void userpageactivity(View view)
    {
        Intent userpageintent=new Intent(this,UserPage.class);
        startActivity(userpageintent);
    }

    public void supervisorpageactivity(View view)
    {
        Intent supervisorpageintent=new Intent(this, SupervisorPage.class);
        startActivity(supervisorpageintent);
    }

    public void workerpageactivity(View view)
    {
        Intent workerpageintent=new Intent(this, WorkerPage.class);
        startActivity(workerpageintent);
    }
}
