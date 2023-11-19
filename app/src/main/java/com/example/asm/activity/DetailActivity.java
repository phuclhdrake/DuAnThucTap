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
        String name = extras.getString("tensanpham");
        String price = extras.getString("giasanpham");
        String desc = extras.getString("motasanpham");
        String image = extras.getString("hinhanhsanpham");

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
                Intent intent = new Intent(DetailActivity.this, OrderActivity.class);
                startActivity(intent);
                /////demo
            }
        });

    }
}