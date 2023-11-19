package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm.R;
import com.squareup.picasso.Picasso;

public class OrderActivity extends AppCompatActivity {
    TextView txtProductName, txtProductPrice, txtProductDesc;
    ImageView imgProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtProductName = findViewById(R.id.txt_productName_oder);
        txtProductPrice = findViewById(R.id.txt_productPrice_oder);
        txtProductDesc = findViewById(R.id.txt_productDesc_oder);
        imgProduct = findViewById(R.id.img_product_oder);

        Intent intent = getIntent();
        String tenSanPham = intent.getStringExtra("tensanpham");
        String giaSanPham = intent.getStringExtra("giasanpham");
        String moTaSanPham = intent.getStringExtra("motasanpham");
        String hinhAnhSanPham = intent.getStringExtra("hinhanhsanpham");

        // Gắn dữ liệu vào các View
        txtProductName.setText(tenSanPham);
        txtProductPrice.setText(giaSanPham);
        txtProductDesc.setText(moTaSanPham);
        // Load hình ảnh sử dụng Picasso (hoặc thư viện hình ảnh khác)
        Picasso.with(getApplicationContext()).load(hinhAnhSanPham)
                .placeholder(R.drawable.imgload)
                .error(R.drawable.imgerror)
                .into(imgProduct);
    }
}