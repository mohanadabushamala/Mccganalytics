package com.example.ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private Button clothes ,
            devises ,

            Beautifying ,

            mobile;
    

    private int clickclothes = 0;
    private int clickdevice = 0;
    private int clickbe = 0;
    private int clickmobile = 0;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
/////////////
        clothes = findViewById(R.id.button);
        devises = findViewById(R.id.button2);
        Beautifying = findViewById(R.id.button3);
        mobile = findViewById(R.id.button4);


        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , Main2Activity.class);
                intent.putExtra("cl" , "Clothes");
                startActivity(intent);
                clickclothes++;

                if (clickclothes == 5){
                    setUserPropertyclothes();
                    Toast.makeText(MainActivity.this, "you are intrested of Clothes category", Toast.LENGTH_SHORT).show();
                    clickclothes = 0;
                }


            }
        });
        Beautifying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Be.class);
                intent.putExtra("be" , "Beautifying");
                startActivity(intent);
                clickbe++;
                if (clickbe == 5){
                    setUserPropertyBeautifying();
                    Toast.makeText(MainActivity.this, "you are intrested of Beautifying category", Toast.LENGTH_SHORT).show();
                    clickbe = 0;
                }

            }
        });
        devises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Device.class);
                intent.putExtra("de" , "Device");
                startActivity(intent);
                clickdevice++;
                if (clickdevice == 5){
                    setUserPropertydevice();
                    Toast.makeText(MainActivity.this, "you are intrested of Devices category", Toast.LENGTH_SHORT).show();
                    clickdevice = 0;
                }

            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Mobile.class);
                intent.putExtra("mo" , "Mobile");
                startActivity(intent);
                clickmobile++;
                if (clickmobile == 5){
                    setUserPropertymobile();
                    Toast.makeText(MainActivity.this, "you are intrested of Mobile category", Toast.LENGTH_SHORT).show();
                    clickmobile = 0;
                }

            }
        });
    }
    private void setUserPropertyBeautifying () {
        mFirebaseAnalytics.setUserProperty("interest", "Beautifying");
    }
    private void setUserPropertyclothes () {
        mFirebaseAnalytics.setUserProperty("interest", "Clothes");
    }
    private void setUserPropertydevice () {
        mFirebaseAnalytics.setUserProperty("interest", "Desvice");
    }
    private void setUserPropertymobile () {
        mFirebaseAnalytics.setUserProperty("interest", "Mobile");
    }

    }


