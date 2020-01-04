package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookAppointments extends AppCompatActivity {

    TextView txtName,txtAge,txtNumber,txtEmail,txtCity;
    RadioButton rbmale,rbfemale;
    Button btnbook;
    Spinner spndept;
    private DatabaseReference reference;
    private String type,transid,name,gender,age,mobile,email,city,dept,doa,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointments);
        btnbook=findViewById(R.id.btnBook);
        txtName=findViewById(R.id.txtName);
        txtNumber=findViewById(R.id.txtMobile);
        txtAge=findViewById(R.id.txtAge);
        txtEmail=findViewById(R.id.txtEmail);
        txtCity=findViewById(R.id.txtCity);
        rbmale=findViewById(R.id.rbmale);
        rbfemale=findViewById(R.id.rbfemale);
        spndept=findViewById(R.id.spindept);
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference= FirebaseDatabase.getInstance().getReference("bookedappointments/"+uid);


        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=txtName.getText()+"";
                Date today= Calendar.getInstance().getTime();
                String t=new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                if(rbfemale.isChecked()){
                    gender="Female";
                }else{
                    gender="Male";
                }
                age=txtAge.getText().toString();
                mobile=txtNumber.getText().toString();
                email=txtEmail.getText().toString();
                city=txtCity.getText().toString();
                dept=spndept.getSelectedItem().toString();
                status="Scheduled";
                type="Appointment";
                doa=today.toString();
                transid=t;
                // ProgressDialog progressDialog=new ProgressDialog(BookAppointments.this);
                //  progressDialog.setMessage("Hold -On !");
               //   progressDialog.show();
                if(name.isEmpty()||age.isEmpty()||mobile.isEmpty()||email.isEmpty()||city.isEmpty()||gender.isEmpty()){
                    Snackbar.make(v,"One or more empty Fields",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(mobile.length()<10){
                    Snackbar.make(v,"Invalid mobile number",Snackbar.LENGTH_LONG).show();
                    return;
                }
                AppointmentModel appointmentModel=new AppointmentModel(type,transid,name,gender,age,mobile,email,city,dept,doa,status);
                reference.child(t).setValue(appointmentModel);
                Intent i=new Intent(BookAppointments.this,BookingConfirm.class);
                i.putExtra("type",type);
                i.putExtra("name",name);
                i.putExtra("mobile",mobile);
                i.putExtra("email",email);
                i.putExtra("dept",dept);
                i.putExtra("status",status);
                i.putExtra("transid",transid);
                startActivity(i);
                finish();

            }
        });
    }
}
