package com.a.roombooking.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.R;

public class MainActivity extends AppCompatActivity {
    Button btn_admnlogin,btn_userlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Dashboard");


        btn_admnlogin=(Button)findViewById(R.id.btn_admnlogin);
        btn_userlogin=(Button)findViewById(R.id.btn_userlogin);

        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        btn_admnlogin.setTypeface(fontstyle);
        btn_userlogin.setTypeface(fontstyle);


        btn_admnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        btn_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}
