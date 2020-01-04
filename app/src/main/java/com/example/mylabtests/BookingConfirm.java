package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingConfirm extends AppCompatActivity {

    TextView txtName,txtType,txtMobile,txtDept,txtStatus,txtEmail,txtTransid;
    Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        txtTransid=findViewById(R.id.txtbctransid);
        txtName=findViewById(R.id.txtbcName);
        txtDept=findViewById(R.id.txtbcDept);
        txtStatus=findViewById(R.id.txtbcStatus);
        txtType=findViewById(R.id.txtbctype);
        txtMobile=findViewById(R.id.txtbcMobile);
        txtEmail=findViewById(R.id.txtbcEmail);
        Bundle extras=getIntent().getExtras();
        txtTransid.setText("Transaction ID: "+extras.getString("transid"));
        txtName.setText(extras.getString("name"));
        txtMobile.setText(extras.getString("mobile"));
        txtDept.setText(extras.getString("dept"));
        txtStatus.setText(extras.getString("status"));
        txtType.setText(extras.getString("type"));
        txtEmail.setText(extras.getString("email"));
        btnConfirm=findViewById(R.id.confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BookingConfirm.this,UserDashboard.class);
                startActivity(i);
                finish();
            }
        });

    }
}
