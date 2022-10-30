package com.example.eunsun;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eunsun.model.Product;

public class ViewHolderProduct extends RecyclerView.ViewHolder {

    ImageView productImageView;
    TextView productTextView;

    public ViewHolderProduct(@NonNull View itemView){
        super(itemView);

        productImageView = itemView.findViewById(R.id.productImageView);
        productTextView = itemView.findViewById(R.id.productTextView);
    }

    public void onBind(Product data){
        productImageView.setImageResource(data.getImage());
        productTextView.setText(data.getTitle());
    }
}
