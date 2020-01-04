package com.example.mylabtests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOTP extends AppCompatActivity {
    FirebaseAuth auth;
    String phone;
    EditText edtotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        auth=FirebaseAuth.getInstance();
        edtotp=findViewById(R.id.edtotp);
    }

    public void verifyOTP(View view) {
        String otp=edtotp.getText().toString();
        Bundle extras=getIntent().getExtras();
        String sentotp=extras.getString("OTP");
        phone=extras.getString("Mobile");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentotp, otp);
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        final ProgressDialog progressDialog=new ProgressDialog(VerifyOTP.this);
        progressDialog.setMessage("Verifying...");
        progressDialog.show();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            progressDialog.hide();
                            progressDialog.cancel();

                        } else {
                            Toast.makeText(VerifyOTP.this, "OTP not Verified", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
