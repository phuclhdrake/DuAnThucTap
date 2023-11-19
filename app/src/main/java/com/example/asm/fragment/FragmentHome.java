package com.example.asm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.SlideAdapter;
import com.example.asm.SlideItem;
import com.example.asm.activity.DetailActivity;
import com.example.asm.activity.LoginActivity;
import com.example.asm.activity.SignUpActivity;
import com.example.asm.adapter.HomeSanphamAdapter;
import com.example.asm.adapter.SanphamAdapter;
import com.example.asm.model.Sanpham;
import com.example.asm.myInterface.IClickItemHomeProduct;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    ViewPager2 viewPager2;
    RecyclerView rcv_home;
    int id = 0;
    String tensanpham = "";
    int giasanpham = 0;
    String hinhanhsanpham = "";
    String motasanpham = "";
    int idsanpham = 0;
    ArrayList<Sanpham> mangSanpham;
    HomeSanphamAdapter sanphamAdapter;

    private Handler slideHandler = new Handler();
    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        cvProduct = view.findViewById(R.id.cv_product);

        viewPager2 = view.findViewById(R.id.viewPagerSlide);
        List<SlideItem> slideItem = new ArrayList<>();
        slideItem.add(new SlideItem(R.drawable.img01));
        slideItem.add(new SlideItem(R.drawable.img02));
        slideItem.add(new SlideItem(R.drawable.img03));
        slideItem.add(new SlideItem(R.drawable.img04));
        slideItem.add(new SlideItem(R.drawable.img05));
        slideItem.add(new SlideItem(R.drawable.img06));
        slideItem.add(new SlideItem(R.drawable.img07));
        slideItem.add(new SlideItem(R.drawable.img08));
//        slideItem.add(new SlideItem(hinhanhsanpham));

        viewPager2.setAdapter(new SlideAdapter(slideItem, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(4);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.86f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, 2000);
            }
        });

        rcv_home = view.findViewById(R.id.rcv_Home);
        mangSanpham = new ArrayList<>();
        sanphamAdapter = new HomeSanphamAdapter(mangSanpham, getContext() );

        rcv_home.setHasFixedSize(true);
        rcv_home.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcv_home.setAdapter(sanphamAdapter);

        return view;
    }

    private void getDuLieuSP() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //b3. url get all sp
        String url = Server.get_all_sanpham;
        //b4. Xac dinh loai request
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray products = response.getJSONArray("Sanpham");
                    mangSanpham.clear();

                    for (int i = 0; i < products.length(); i++) {
                        JSONObject product = products.getJSONObject(i);
                        id = product.getInt("id");
                        tensanpham = product.getString("tensanpham");
                        giasanpham = product.getInt("giasanpham");
                        hinhanhsanpham = product.getString("hinhanhsanpham");
                        motasanpham = product.getString("motasanpham");
                        idsanpham = product.getInt("idsanpham");
                        mangSanpham.add(new Sanpham(id, tensanpham, giasanpham, hinhanhsanpham, motasanpham, idsanpham));
                        sanphamAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(getContext(), "loi ne");
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }


    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 2000);
        if (CheckConnect.haveNetworkConnection(getContext())) {
            CheckConnect.ShowToast_Short(getContext(), "Loading...");
            getDuLieuSP();
        } else {
            CheckConnect.ShowToast_Short(getContext(), "Không có kết nối internet !");
            getActivity().finish();
        }
    }
}