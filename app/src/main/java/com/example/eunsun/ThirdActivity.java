package com.example.eunsun;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eunsun.adapter.ProductAdapter;
import com.example.eunsun.model.Product;

public class ThirdActivity extends AppCompatActivity {

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        init();
        getData();

        ImageButton mypageImageButton = (ImageButton) findViewById(R.id.mypageImageButton);

        mypageImageButton.setOnClickListener(new View.OnClickListener() { // 마이페이지 버튼 누를시
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.productRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
    }

    private void getData(){
        Product data = new Product(R.drawable.product1, "Apple 정품 아이폰 14 Pro Max 자급제");
        productAdapter.addItem(data);
        data = new Product(R.drawable.product2, "Apple 2021 아이맥 24");
        productAdapter.addItem(data);
        data = new Product(R.drawable.product3, "Apple 2022 애플워치 SE");
        productAdapter.addItem(data);
        data = new Product(R.drawable.product4, "Apple 2021 맥북프로 16 ");
        productAdapter.addItem(data);
        data = new Product(R.drawable.product5, "샤인 LED 수납형 서랍형 슈퍼싱글 퀸 침대");
        productAdapter.addItem(data);
        data = new Product(R.drawable.product6, "티파니 스마일 스몰 18K 로즈골드 목걸이");
        productAdapter.addItem(data);
        Log.d("test", "test: "+data);
    }
}