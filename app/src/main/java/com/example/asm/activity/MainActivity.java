package com.example.asm.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.asm.R;
import com.example.asm.fragment.FragmentCart;
import com.example.asm.fragment.FragmentHome;
import com.example.asm.fragment.FragmentPerson;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ham anh xa
        init();

        // Đổ fragment_home vào FragmentContainerView
        fragmentManager.beginTransaction().replace(R.id.fragmnet_context, FragmentHome.newInstance(null, null)).commit();

        // Hàm bắt sự kiện bottomNavigation
        bottomNavigationOnclick();
    }

    // Ánh x=ạ
    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        fragmentManager = getSupportFragmentManager();
    }

    // Bắt sự kiện bottomNavigation
    public void bottomNavigationOnclick() {
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            try {
                if (item.getItemId() == R.id.nav_Home) {
                    fragmentManager.beginTransaction().replace(R.id.fragmnet_context, FragmentHome.newInstance(null, null)).commit();
                } else if (item.getItemId() == R.id.nav_Cart) {
                    fragmentManager.beginTransaction().replace(R.id.fragmnet_context, FragmentCart.newInstance(null, null)).commit();
                } else if (item.getItemId() == R.id.nav_Person) {
                    fragmentManager.beginTransaction().replace(R.id.fragmnet_context, FragmentPerson.newInstance(null, null)).commit();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999) {
            Log.e("code", requestCode + "aaaaa");
            fragmentManager.beginTransaction().replace(R.id.fragmnet_context, FragmentHome.newInstance(null, null)).commit();
        }
    }

    @Override
    public void onBackPressed() {
        // Kiểm tra xem người dùng đang ở màn hình nào
        if (bottomNavigationView.getSelectedItemId() == R.id.nav_Home
                || bottomNavigationView.getSelectedItemId() == R.id.nav_Cart
                || bottomNavigationView.getSelectedItemId() == R.id.nav_Person) {
            showExitDialog();
        } else {
            super.onBackPressed();
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát ứng dụng");
        builder.setMessage("Bạn có muốn thoát ứng dụng không?");
        builder.setPositiveButton("Có", (dialog, which) -> {
            finish(); // Đóng ứng dụng
        });
        builder.setNegativeButton("Không", (dialog, which) -> {
            dialog.dismiss(); // Đóng hộp thoại
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}