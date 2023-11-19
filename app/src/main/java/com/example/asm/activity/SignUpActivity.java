package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;
import com.squareup.picasso.Downloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    ImageButton imgBtnBack;

    EditText edtUsername, edtPassword, edtRePassword;

    TextView txtError;

    Button btnSignup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtUsername = findViewById(R.id.edt_user_singup);
        edtPassword = findViewById(R.id.edt_password_singup);
        edtRePassword = findViewById(R.id.edt_repassword_singup);
        txtError = findViewById(R.id.txt_error_singup);
        btnSignup = findViewById(R.id.btn_singup_singup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to handle signup
                handleSignup();
            }
        });
    }

    private void handleSignup() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String rePassword = edtRePassword.getText().toString().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            setError("Không được để trống !");
            return;
        }

        if (username.length() > 20 || password.length() > 20 || rePassword.length() > 20) {
            setError("Độ dài không được quá 20 ký tự !");
            return;
        }

        if (!password.equals(rePassword)) {
            setError("Confirm password phải giống với password !");
            return;
        }

        // Prepare data
        final String finalUsername = username;
        final String finalPassword = password;

        // Create request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Define the URL
        String url = Server.API_signup;

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
                                CheckConnect.ShowToast_Short(getApplicationContext(), message);
                                finish();
                            } else {
                                CheckConnect.ShowToast_Short(getApplicationContext(), message);
                                setError(message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            setError("Lỗi parse JSON: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getApplicationContext(),"Lỗi: "+ error.getMessage());
                setError(error.toString());
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


    private void setError(String errorMessage) {
        if (!errorMessage.isEmpty()) {
            txtError.setText(errorMessage);
            txtError.setTextColor(getResources().getColor(android.R.color.holo_red_light)); // Set text color to red
        }
    }

}