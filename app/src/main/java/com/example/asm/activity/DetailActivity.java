 package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm.R;
import com.example.asm.model.Sanpham;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    Button btnMua;
    ImageView imgHinhanhsp;
    TextView txtTensp, txtGiasp, txtMotasp;

    String name, price, desc, image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTensp = findViewById(R.id.txtTensp_detail);
        txtGiasp = findViewById(R.id.txtGiasp_Detail);
        txtMotasp = findViewById(R.id.txtMota_Detail);
        imgHinhanhsp = findViewById(R.id.imgHinhanhsp_Detail);
        btnMua = findViewById(R.id.btnDetailMua);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        name = extras.getString("tensanpham");
        price = extras.getString("giasanpham");
        desc = extras.getString("motasanpham");
        image = extras.getString("hinhanhsanpham");

        txtTensp.setText(name);
        txtGiasp.setText(price);
        txtMotasp.setText(desc);
        Picasso.with(getApplicationContext()).load(image)
                .placeholder(R.drawable.imgload)
                .error(R.drawable.imgerror)
                .into(imgHinhanhsp);
        Log.d("aaaaaaaaa", "name "+name);
        Log.d("aaaaaaaaa", "gia "+price);
        Log.d("aaaaaaaaa", "mota"+desc);
        Log.d("aaaaaaaaa", "anh"+image);

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(DetailActivity.this, OrderActivity.class);

                // Đính kèm dữ liệu vào Intent
                orderIntent.putExtra("tensanpham", txtTensp.getText().toString());
                orderIntent.putExtra("giasanpham", txtGiasp.getText().toString());
                orderIntent.putExtra("motasanpham", txtMotasp.getText().toString());
                orderIntent.putExtra("hinhanhsanpham", image);

                startActivity(orderIntent);
            }
        });

    }
}