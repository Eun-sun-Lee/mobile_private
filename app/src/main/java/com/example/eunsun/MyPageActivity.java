package com.example.eunsun;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //프레퍼런스
        SharedPreferences pref = getSharedPreferences("person_info", 0); // 프레퍼런스
        SharedPreferences.Editor editor = pref.edit();

        TextView myIdTextView = (TextView) findViewById(R.id.myIdTextView);
        TextView myNameTextView = (TextView) findViewById(R.id.myNameTextView);
        TextView myPhoneNumberTextView = (TextView) findViewById(R.id.myPhoneNumberTextView);
        TextView myAddressTextView = (TextView) findViewById(R.id.myAddressTextView);


    }
}
