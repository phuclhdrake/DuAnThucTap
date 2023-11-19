package com.example.asm.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.activity.AdminActivity;
import com.example.asm.activity.LoginActivity;
import com.example.asm.activity.SignUpActivity;
import com.example.asm.model.Sanpham;
import com.example.asm.model.User;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentPerson extends Fragment {

    ProgressBar progressBar;
    Button btnAdmin, btnLogout, btnEditPass;
    TextView txtUserName;
    int id;
    String name;
    String address;
    String email;
    String username;
    int role;

    ArrayList<User> mangUser;
    public FragmentPerson() {
        // Required empty public constructor
    }

    public static FragmentPerson newInstance(String param1, String param2) {
        FragmentPerson fragment = new FragmentPerson();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        txtUserName = view.findViewById(R.id.txt_username_person);
        progressBar = view.findViewById(R.id.progressBar);

        mangUser = new ArrayList<>();

        btnAdmin = view.findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa thông tin đăng nhập
                clearLoginInfo();

                // Chuyển người dùng về màn hình đăng nhập
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnEditPass = view.findViewById(R.id.btnEditPass);
        btnEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // Sử dụng phương thức getSavedUsername ở đây
        String userNameLogin = getSavedUsername();
        if (userNameLogin.length() > 0){
            txtUserName.setText(userNameLogin);
        }
        Log.d("uuuuu", "getSavedUsername: "+ userNameLogin);
        getDuLieuUsers(userNameLogin);

        return view;
    }

    private String getSavedUsername() {
        // Sử dụng getActivity() thay vì getContext()
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return prefs.getString("username", "");
    }

    private void getDuLieuUsers(String usernameLogin) {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //b3. url get all sp
        String url = Server.API_account + "?username=" + usernameLogin;
        //b4. Xac dinh loai request
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray users = response.getJSONArray("user");
                            for (int i = 0; i < users.length(); i++) {
                                JSONObject user = users.getJSONObject(i);
                                id = user.getInt("id");
                                name = user.getString("name");
                                address = user.getString("address");
                                email = user.getString("email");
                                username = user.getString("username");
                                role = user.getInt("role");

                                if (role == 1) {
                                    btnAdmin.setVisibility(View.VISIBLE);// Hiển thị Button Admin
                                } else {
                                    btnAdmin.setVisibility(View.GONE); // Ẩn Button Admin
                                }

                                mangUser.add(new User(id, name, address, email, username, role));
                            }
                            hideLoading(); // Move hideLoading outside the for loop
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showLoading(); // Move hideLoading outside the for loop
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showLoading(); // Hide loading in case of an error
                        CheckConnect.ShowToast_Short(getContext(), "Lỗi khi lấy dữ liệu từ API");
                    }
                }
        );
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void clearLoginInfo() {
        // Sử dụng getActivity() thay vì getContext()
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("username");
        // Xóa tất cả các thông tin đăng nhập khác nếu cần
        editor.apply();
    }

}

