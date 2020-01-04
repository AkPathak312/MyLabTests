package com.example.mylabtests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LabTests extends AppCompatActivity {
    String[] testname,description,price;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    ListView lablistview;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_tests);
        lablistview=findViewById(R.id.lablistview);
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("labtests");
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Fetching Details...");
        progressDialog.setMessage("Please wait while we get our tests");
        progressDialog.show();
        Log.e("TAG","Hi");

        // final ProgressBar progressBar=new ProgressBar(this);
        //progressBar.setVisibility(View.VISIBLE);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=(int)dataSnapshot.getChildrenCount();
                int j=0;
                testname=new String[i];
                description=new String[i];
                price=new String[i];
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    testname[j]=ds.child("name").getValue()+"";
                    description[j]=ds.child("desc").getValue()+"";
                    price[j]=ds.child("price").getValue()+"";
                    j+=1;
                }
                Log.e("TAG","Hi");

                CustomAdapter customAdapter=new CustomAdapter();
                lablistview.setAdapter(customAdapter);
              //  progressBar.setVisibility(View.INVISIBLE);
               // progressBar.setVisibility(View.GONE);
                progressDialog.hide();
                progressDialog.cancel();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG","Hi");

            }
        });
    }



    //Custom Adapter to Show the test list
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return testname.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.labtestlayout,null);
            TextView txtTestName=convertView.findViewById(R.id.txtTestName);
            TextView txtPrice=convertView.findViewById(R.id.txTestPrice);
            TextView txtDesc=convertView.findViewById(R.id.txtTestDesc);
            Button btnBook=convertView.findViewById(R.id.btnbooknow);
            Log.e("TAG","Hi");
            txtTestName.setText(testname[position]);
            txtPrice.setText("Rs. "+price[position]);
            txtDesc.setText(description[position]);
            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(LabTests.this,BookTest.class);
                    i.putExtra("TestName",testname[position]);
                    i.putExtra("TestAmount",price[position]);
                    startActivity(i);
                }
            });
            return convertView;
        }
    }
}
