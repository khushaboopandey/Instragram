package com.example.instragram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {
   private TextView textWelcome;
   private Button BtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textWelcome = findViewById(R.id.textwelcome);
        BtnLogout = findViewById(R.id.btnlogout);

        textWelcome.setText("Welcome! " + ParseUser.getCurrentUser().get("username"));

        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
               // FancyToast.makeText(WelcomeActivity.this,  " LogOut successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,  false).show();
                finish(); //It Will take you to previous activity.
            }
        });


    }
}