package com.example.eunsun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageButton backImageButton = (ImageButton) findViewById(R.id.backImageButton); // 이미지 버튼
        EditText idEditText = (EditText) findViewById(R.id.idEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        EditText passwordEditText2 = (EditText) findViewById(R.id.passwordEditText2);
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        EditText addressEditText = (EditText) findViewById(R.id.addressEditText); // 에디트 텍스트
        TextView idCheckTextView = (TextView) findViewById(R.id.idCheckTextView);
        TextView passwordCheckTextView = (TextView) findViewById(R.id.passwordCheckTextView);
        TextView passwordCheckTextView2 = (TextView) findViewById(R.id.passwordCheckTextView2); // 텍스트뷰
        RadioButton privateRadioButton = (RadioButton) findViewById(R.id.privateRadioButton);
        RadioButton privateRadioButton2 = (RadioButton) findViewById(R.id.privateRadioButton2); // 라디오 버튼
        TextView privateTextView = (TextView) findViewById(R.id.privateTextView);
        TextView privateTextView2 = (TextView) findViewById(R.id.privateTextView2); // 텍스트뷰
        AppCompatButton signButton = (AppCompatButton) findViewById(R.id.signButton); // app 버튼

        //프레퍼런스
        SharedPreferences pref = getSharedPreferences("person_info", 0); // 프레퍼런스
        SharedPreferences.Editor editor = pref.edit();

        idEditText.addTextChangedListener(new TextWatcher() { // 아이디
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                idCheckTextView.setVisibility(View.INVISIBLE);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        passwordEditText2.addTextChangedListener(new TextWatcher() { // 비밀번호가 일치한다면
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(passwordEditText.getText().toString().equals(passwordEditText2.getText().toString())){
                    passwordCheckTextView2.setVisibility(View.INVISIBLE);
                } else{
                    passwordCheckTextView2.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() { // 비밀번호 유효성 확인
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean passwordValid = false;
                passwordValid = checkPassword(passwordEditText);
                if ((passwordValid==false)||(passwordEditText.getText().toString().length()<=7)||(passwordEditText.getText().toString().length()>=17)){
                    passwordCheckTextView.setVisibility(View.VISIBLE);
                } else{
                    passwordCheckTextView.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        privateTextView.setMovementMethod(new ScrollingMovementMethod());
        privateTextView2.setMovementMethod(new ScrollingMovementMethod());

        backImageButton.setOnClickListener(new View.OnClickListener() { // 뒤로가기 버튼 누를시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signButton.setOnClickListener(new View.OnClickListener() { // 가입하기 버튼 누를시

            @Override
            public void onClick(View view) { // 가입하기 버튼 누를시
                if(idEditText.getText().toString().length()<=7){
                    Toast.makeText(SecondActivity.this, "아이디를 올바르게 입력하세요(최소 8자 이상).", Toast.LENGTH_SHORT).show();
                    idEditText.requestFocus(); // 1. "index" : index(int) 2. "index(int->String)": "id" 3. "id" : "OneData(나머지 회원정보)"
                    return;
                }
                String idCheck = idEditText.getText().toString();
                if(pref.getString(idCheck,"").length()>=1){ // id가 이미 존재하는 아이디이면
                    Toast.makeText(SecondActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    idCheckTextView.setVisibility(View.VISIBLE);
                    return;
                } else{
                    idCheckTextView.setVisibility(View.INVISIBLE);
                }
                if((passwordEditText.getText().toString().length()<=7)||(passwordEditText.getText().toString().length()>=17)||(!checkPassword(passwordEditText))){
                    Toast.makeText(SecondActivity.this, "영문, 숫자, 특수문자를 포함해서 비밀번호를 올바르게 입력하세요(8자-16자).", Toast.LENGTH_SHORT).show();
                    passwordEditText.requestFocus();
                    return;
                }
                if((passwordEditText2.getText().toString().length()<=7)||(passwordEditText2.getText().toString().length()>=17)){
                    Toast.makeText(SecondActivity.this, "비밀번호 재확인이 필요합니다.", Toast.LENGTH_SHORT).show();
                    passwordEditText2.requestFocus();
                    return;
                }
                if ((nameEditText.getText().toString().length()<=1)||(phoneNumberEditText.getText().toString().length()<=6)||(addressEditText.getText().toString().length()<=5)){
                    Toast.makeText(SecondActivity.this,"이름, 전화번호, 주소를 모두 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((privateRadioButton.isChecked()&&privateRadioButton2.isChecked())){
                } else{
                    Toast.makeText(SecondActivity.this,"이용약관과 개인정보 수집 및 이용에 대해 모두 동의해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                String specialKey = "\\\\"; // special Key : \\
                int prefIndex = pref.getInt("index",0);
                Integer index = prefIndex+1;
                editor.putInt("index",index); // "index" : 1,2.... -> index 1부터 시작
                editor.putString(index.toString(),id); // "1" : "eunsun208080","2": "minsuk12",....
                String oneData = String.join(specialKey, password, name, phoneNumber, address);
                editor.putString(id,oneData);
                editor.apply();

                Integer prefIdx = pref.getInt("index",0);
                String prefId = pref.getString(prefIdx.toString(),"");
                String prefOneData = pref.getString(prefId,"");

                Log.d("TAG","pref: "+prefIdx+prefId+prefOneData); // pref: 1eunsun2080dmstjs415834!\eunsun\01064858365\Seoul gundae
                // 2eunsun2020dmstjs20des!\esssun\01083928392\Kookmin Univsersity

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkPassword(EditText passwordEditText){
        String passwordValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,15}.$";
        String password = passwordEditText.getText().toString().trim();
        if (password.matches(passwordValidation)){
            return true;
        } else {
            return false;
        }
    }

}


