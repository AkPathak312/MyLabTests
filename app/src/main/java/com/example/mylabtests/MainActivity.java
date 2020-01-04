package com.example.mylabtests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
               // Toast.makeText(MainActivity.this, "Checking", Toast.LENGTH_SHORT).show();
                if(firebaseUser!=null){
                    Intent i=new Intent(MainActivity.this,UserDashboard.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        //Code handler for TIme wise activity transition.
        /*
         new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i=new Intent(MainActivity.this,UserDashboard.class);
                            startActivity(i);
                            finish();
                        }
                    },2000);

         */

        imageView=findViewById(R.id.imglogo);

    }
}
