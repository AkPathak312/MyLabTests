package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecordsDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_dashboard);
    }

    public void gotomyappointments(View view) {
        Intent i= new Intent(RecordsDashboard.this,MyRecords.class);
        startActivity(i);
    }
}
