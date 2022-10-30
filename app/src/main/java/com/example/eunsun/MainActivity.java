package com.example.eunsun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프레퍼런스
        SharedPreferences pref = getSharedPreferences("person_info", 0); // 프레퍼런스
        SharedPreferences.Editor editor = pref.edit();

        EditText loginIdEditText = (EditText) findViewById(R.id.loginIdEditText);
        EditText loginPasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        AppCompatButton loginButton = (AppCompatButton) findViewById(R.id.loginButton);
        AppCompatButton signButton = (AppCompatButton) findViewById(R.id.signButton);
        AppCompatButton noSignButton = (AppCompatButton) findViewById(R.id.noSignButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginIdEditText.getText().toString().length()<=7){
                    Toast.makeText(MainActivity.this, "아이디를 올바르게 입력하세요(8자 이상).", Toast.LENGTH_SHORT).show();
                    loginIdEditText.requestFocus();
                    return;
                }
                if((loginPasswordEditText.getText().toString().length()<=7)||(loginPasswordEditText.getText().toString().length()>=17)||(!checkPassword(loginPasswordEditText))){
                    Toast.makeText(MainActivity.this, "영문, 숫자, 특수문자를 포함해서 비밀번호를 올바르게 입력하세요(8자-16자).", Toast.LENGTH_SHORT).show();
                    loginPasswordEditText.requestFocus();
                    return;
                }

                String id = loginIdEditText.getText().toString();
                String password = loginPasswordEditText.getText().toString();
                String specialKey = "\\\\";

                // 1eunsun2020dmstjs20des!\esssun\01083928392\Kookmin Univsersity
                // 1. "index" : index(int) 2. "index(int->String)": id 3. "id" : OneData(나머지 회원정보) 4. "currLoginId" : id

                if(pref.getString(id,"").length()>=1){ // key : id로 가져온 값이 존재한다면 -> 회원가입한 회원이라면
                    // 비밀번호 검사
                    String prefOneData = pref.getString(id,"");
                    Log.d("TEST1", "prefOneData: "+prefOneData);
                    String[] splitOnedata = prefOneData.split(specialKey);
                    for(int i=0; i<splitOnedata.length; i++){
                        Log.d("TEST", "splitOneData: "+i+" "+ splitOnedata[i]);
                    }
//                    Log.d("TEST2", "splitOneData[0]: "+splitOnedata[0]);
//                    Log.d("TEST3", "splitOneData[1]: "+splitOnedata[1]);
//                    Log.d("TEST4", "splitOneData[2]: "+splitOnedata[2]);
                    if(splitOnedata[0].equals(password)){
                        editor.putString("currLoginId", id); //currLoginId 저장
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                        startActivity(intent);
                        finish();
                    } else{
                        Toast.makeText(MainActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        loginPasswordEditText.requestFocus();
                        return;
                    }
                } else{
                    Toast.makeText(MainActivity.this, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        signButton.setOnClickListener(new View.OnClickListener() { // 회원가입 버튼 누를시
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });

        noSignButton.setOnClickListener(new View.OnClickListener() { // 회원가입 없이 앱 이용하기 버튼 누를시
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private boolean checkPassword(EditText passwordEditText){
        String passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$";
        String password = passwordEditText.getText().toString().trim();
        if (password.matches(passwordValidation)){
            return true;
        } else {
            return false;
        }
    }
}