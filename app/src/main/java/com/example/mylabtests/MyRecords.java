package com.example.mylabtests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MyRecords extends AppCompatActivity {

    String[] type={"Health Package","Lab test","Lab Test"};
    String[] bookedon={"17-11-2019","17-11-2019","18-11-2019"};
    String[] payable={"Rs 5540/-","Rs. 330/-","Rs. 440/-"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_records);
        listView=findViewById(R.id.mylistview);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return type.length;
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
            convertView=getLayoutInflater().inflate(R.layout.myrecords,null);
            TextView txtType=convertView.findViewById(R.id.txtType);
            TextView txtPayable=convertView.findViewById(R.id.txtPayable);
            TextView txtBookedon=convertView.findViewById(R.id.txtBookedOn);
            Button btnMoreinfo=convertView.findViewById(R.id.btnMoreInfo);
            Log.e("TAG","Hi");
            txtType.setText(type[position]);
            txtPayable.setText(payable[position]);
            txtBookedon.setText(bookedon[position]);
            return convertView;
        }
    }
}
