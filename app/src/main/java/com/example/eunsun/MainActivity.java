package com.example.eunsun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText loginIdEditText = (EditText) findViewById(R.id.loginIdEditText);
        EditText loginPasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        AppCompatButton loginButton = (AppCompatButton) findViewById(R.id.loginButton);
        AppCompatButton signButton = (AppCompatButton) findViewById(R.id.signButton);
        AppCompatButton noSignButton = (AppCompatButton) findViewById(R.id.noSignButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginIdEditText.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "아이디를 올바르게 입력하세요.", Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
                finish();
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