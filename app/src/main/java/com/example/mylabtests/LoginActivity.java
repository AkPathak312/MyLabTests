package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
   // String codesent;
    FirebaseAuth auth;
    EditText mobilenumber;
    String phoneNumber="";
    ProgressDialog progressDialog;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();
        mobilenumber=findViewById(R.id.edtMobileNumber);
    }

    @Override
    public void onBackPressed() {

       if(count==1){
           System.exit((0));
       }
       else {
           count++;
           Toast.makeText(LoginActivity.this, "Click Again to Exit.", Toast.LENGTH_SHORT).show();
       }
    }

    public void sendotp(View view) {
        progressDialog=new ProgressDialog(this);
        //progressDialog.setTitle("Sending. . .");
        progressDialog.setMessage("Try resending if the process is not complete in a minute. Check your internet connection.");
        progressDialog.setCancelable(false);
        progressDialog.show();
        phoneNumber=mobilenumber.getText().toString();
        Toast.makeText(LoginActivity.this, "Sending OTP ...", Toast.LENGTH_SHORT).show();
        if(phoneNumber.isEmpty()||phoneNumber.length()<10)
        {
            Toast.makeText(LoginActivity.this, "Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            progressDialog.cancel();
            progressDialog.hide();
            Toast.makeText(LoginActivity.this, "Error Sending Code.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            progressDialog.cancel();
            progressDialog.hide();
            Intent i=new Intent(LoginActivity.this,VerifyOTP.class);
            i.putExtra("OTP",s);
            i.putExtra("Number",phoneNumber);
            startActivity(i);
            finish();
        }
    };

}

