package com.example.eunsun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        ImageButton backImageButton = (ImageButton) findViewById(R.id.backImageButton);
        TextView myIdTextView = (TextView) findViewById(R.id.myIdTextView);
        TextView myNameTextView = (TextView) findViewById(R.id.myNameTextView);
        TextView myPhoneNumberTextView = (TextView) findViewById(R.id.myPhoneNumberTextView);
        TextView myAddressTextView = (TextView) findViewById(R.id.myAddressTextView);

        String specialKey = "\\\\";

        String id = pref.getString("currLoginId","");
        String prefOneData = pref.getString(id,"");
        Log.d("MyPage", "prefOneData: "+prefOneData);

        String[] splitOnedata = prefOneData.split(specialKey);
        String name = splitOnedata[2].toString();
        String phoneNumber = splitOnedata[4].toString();
        String address = splitOnedata[6].toString();

        Log.d("MyPage", "info: "+id+name+phoneNumber+address);

        myIdTextView.setText(id);
        myNameTextView.setText(name);
        myPhoneNumberTextView.setText(phoneNumber);
        myAddressTextView.setText(address);

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, ThirdActivity.class);
                intent.putExtra("isLogined",true);
                startActivity(intent);
                finish();
            }
        });
    }
}
