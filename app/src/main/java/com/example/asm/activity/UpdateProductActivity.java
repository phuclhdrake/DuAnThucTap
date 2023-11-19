package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class UpdateProductActivity extends AppCompatActivity {
    EditText edtUpdateProductTensp, edtUpdateProductGiasp, edtUpdateProductHinhanhsp, edtUpdateProductMotasp;
    Button btnUpdateProduct;
    String id, name, price, desc, image, idsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        // anh xa --------------
        Anhxa();

        // Get the intent that started this activity
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        id = extras.getString("id");
        name = extras.getString("tensanpham");
        price = extras.getString("giasanpham");
        desc = extras.getString("motasanpham");
        image = extras.getString("hinhanhsanpham");
        idsp = extras.getString("idsanpham");
        Log.d("aaaaaaaa", "id "+id);

        edtUpdateProductTensp.setText(name);
        edtUpdateProductGiasp.setText(String.valueOf(price));
        edtUpdateProductHinhanhsp.setText(image);
        edtUpdateProductMotasp.setText(desc);

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProductVolley();
            }
        });

    }

    private void Anhxa() {
        edtUpdateProductTensp = findViewById(R.id.edt_UpdateTensp);
        edtUpdateProductGiasp = findViewById(R.id.edt_UpdateGiasp);
        edtUpdateProductHinhanhsp = findViewById(R.id.edt_UpdateHinhanhsp);
        edtUpdateProductMotasp = findViewById(R.id.edt_UpdateMotasp);
        btnUpdateProduct = findViewById(R.id.btn_Update_product);
    }

    // ham update du lieu
    private void UpdateProductVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //b3. url
        String url = Server.update_product;
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CheckConnect.ShowToast_Short(getApplicationContext(), "Update thành công");
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getApplicationContext(), error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> mydata = new HashMap<>();
                mydata.put("id", String.valueOf(id));
                mydata.put("tensanpham", edtUpdateProductTensp.getText().toString());
                mydata.put("giasanpham", edtUpdateProductGiasp.getText().toString());
                mydata.put("hinhanhsanpham", edtUpdateProductHinhanhsp.getText().toString());
                mydata.put("motasanpham", edtUpdateProductMotasp.getText().toString());
                mydata.put("idsanpham", String.valueOf(idsp));
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

}