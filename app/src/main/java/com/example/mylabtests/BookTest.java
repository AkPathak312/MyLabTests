package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookTest extends AppCompatActivity {
    TextView txtBookedtest;
    TextView name,mobile,city,age,emailid;
    RadioButton rbmale,rbfemale;
    Button btnbooktest;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference= FirebaseDatabase.getInstance().getReference("bookedtests/"+uid);
        Bundle extra=getIntent().getExtras();
        final String str=extra.getString("TestName");
        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        txtBookedtest=findViewById(R.id.txtbookingfor);
        txtBookedtest.setText("Booked Test: "+str);
        String price=extra.getString("TestAmount");
        btnbooktest=findViewById(R.id.btnBookTest);
        btnbooktest.setText("Book Now (Rs. "+price+"/-)");
        name=findViewById(R.id.txtbtname);
        age=findViewById(R.id.txtbtAge);
        city=findViewById(R.id.txtbtCity);
        mobile=findViewById(R.id.txtbtMobile);
        emailid=findViewById(R.id.txtbtEmail);
        rbmale=findViewById(R.id.rbbtmale);
        rbfemale=findViewById(R.id.rbbtfemale);

        btnbooktest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(rbfemale.isChecked()){
                    gender="Female";
                }else{
                    gender="Male";
                }
                String transid=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String dept=str;
                String doa=new  SimpleDateFormat("yyyy-MM-dd").format(new Date());
                AppointmentModel appointmentModel=new AppointmentModel("Lab Test"
                ,transid,name.getText().toString()
                        ,gender,
                        age.getText().toString(),
                         mobile.getText().toString(),
                        emailid.getText().toString(),
                        city.getText().toString(),
                        dept,
                        doa,"Pending");

                reference.child(transid).setValue(appointmentModel);
                Intent i=new Intent(BookTest.this,BookingConfirm.class);
                i.putExtra("type","Lab Test");
                i.putExtra("name",name.getText().toString());
                i.putExtra("mobile",mobile.getText().toString());
                i.putExtra("email",emailid.getText().toString());
                i.putExtra("dept",dept);
                i.putExtra("status","Pending");
                i.putExtra("transid",transid);
                startActivity(i);
                finish();
            }
        });

    }
}
