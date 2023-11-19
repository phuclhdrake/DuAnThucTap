package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    EditText edtUser, edtPass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edt_User_login);
        edtPass = findViewById(R.id.edt_Pass_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleLoginVolley();
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLoginVolley() {
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            CheckConnect.ShowToast_Short(getApplicationContext(), "Hãy nhập tài khoản và mật khẩu.");
            return;
        }

        // Prepare data
        final String finalUsername = username;
        final String finalPassword = password;

        // Create request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Define the URL
        String url = Server.API_login;

        // Make a POST request
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            int success = jsonResponse.getInt("success");
                            String message = jsonResponse.getString("message");

                            if (success == 1) {
                                // Lưu thông tin đăng nhập vào SharedPreferences
                                saveLoginInfo(finalUsername);

                                CheckConnect.ShowToast_Short(getApplicationContext(), message);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                CheckConnect.ShowToast_Short(getApplicationContext(), message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getApplicationContext(),"Lỗi: "+ error.getMessage());
                Log.d("111", "VolleyError: "+ error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> mydata = new HashMap<>();
                mydata.put("username", finalUsername);
                mydata.put("pass", finalPassword);
                return mydata;
            }
        };
        // Execute the request
        queue.add(request);
    }

    //
    private void saveLoginInfo(String username) {
        // Lưu tên người dùng vào SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.apply();
    }


}
