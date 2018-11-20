package com.example.abhirami.clogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserPage extends AppCompatActivity {


    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        btnLogOut = (Button) findViewById(R.id.userlogout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserPage.this, MainActivity.class);
                startActivity(I);

            }
        });


    }
    public void lognewcomplaint(View view)
    {
        Intent lognewcomplaintintent=new Intent(this,LogNewComplaint.class);
        startActivity(lognewcomplaintintent);
    }

    public void trackcomplaint(View view)
    {
        Intent trackcomplaintsintent=new Intent(this,TrackComplaint.class);
        startActivity(trackcomplaintsintent);
    }
}

