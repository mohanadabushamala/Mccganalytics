package com.example.ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Device extends AppCompatActivity {
    private TextView title;
    FirebaseFirestore db;
    FirebaseAnalytics mFirebaseAnalytics;
//////////////////
    private static int count = 0;
    private boolean time = true;
    Thread t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        t=new Thread(){
            @Override
            public void run(){

                while(time){

                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                count++;
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        trackScreen();



        title = findViewById(R.id.de);

        String de = getIntent().getStringExtra("de");
        title.setText(de);
        final ArrayList<ListItem> Items = new ArrayList<ListItem>();
        Items.add(new ListItem("mobiles" , "nice and good"));
        Items.add(new ListItem("A Battery" , "nice and good"));
        Items.add(new ListItem("Air Core" , "nice and good"));
        Items.add(new ListItem("Align" , "nice and good"));
        Items.add(new ListItem("Alternation" , "nice and good"));
        Items.add(new ListItem("Ammeter" , "nice and good"));
        Items.add(new ListItem("mobiles" , "nice and good"));
        Items.add(new ListItem("A Battery" , "nice and good"));
        Items.add(new ListItem("Air Core" , "nice and good"));
        Items.add(new ListItem("Align" , "nice and good"));
        Items.add(new ListItem("Alternation" , "nice and good"));
        Items.add(new ListItem("Ammeter" , "nice and good"));
        Items.add(new ListItem("mobiles" , "nice and good"));
        Items.add(new ListItem("A Battery" , "nice and good"));
        Items.add(new ListItem("Air Core" , "nice and good"));
        Items.add(new ListItem("Align" , "nice and good"));

        final MyCustemAdapter myCustemAdapter = new MyCustemAdapter(Items);
        ListView listItem = findViewById(R.id.list_de);
        listItem.setAdapter(myCustemAdapter);
    }

    class MyCustemAdapter extends BaseAdapter {

        ArrayList<ListItem> Items = new ArrayList<ListItem>();
        MyCustemAdapter(ArrayList<ListItem> Items){
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int position) {
            return Items.get(position).Name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View views = layoutInflater.inflate(R.layout.row , null);
            TextView textView = views.findViewById(R.id.name);
            TextView textView1 = views.findViewById(R.id.des);

            textView.setText(Items.get(position).Name);
            textView1.setText(Items.get(position).Desc);

            return views;
        }
    }

    private void trackScreen(){
        mFirebaseAnalytics.setCurrentScreen(this , "Device Page" , null);
    }
    private void setUserProperty () {
        mFirebaseAnalytics.setUserProperty("offer-device", "high");
    }
    @Override
    protected void onPause() {
        super.onPause();
        time = false;
        db = FirebaseFirestore.getInstance();
        addData();
        Toast.makeText(this, count+"s", Toast.LENGTH_SHORT).show();
        count = 0;
    }
    private void addData () {
        Map<String, String> user = new HashMap<>();
        user.put("devisewait", count+"s");


        db.collection("DeviseTimer")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Device.this, "Success Add", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(Device.this, "Failure Add", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
