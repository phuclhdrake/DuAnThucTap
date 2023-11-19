package com.example.asm.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm.R;
import com.example.asm.activity.AddProductActivity;
import com.example.asm.activity.AdminActivity;
import com.example.asm.activity.UpdateProductActivity;
import com.example.asm.model.Sanpham;
import com.example.asm.ultil.CheckConnect;
import com.example.asm.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    ArrayList<Sanpham> arrayListSanpham;
    Context context;
    int productId;

    public SanphamAdapter(ArrayList<Sanpham> arrayListSanpham, Context context) {
        this.arrayListSanpham = arrayListSanpham;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_product, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arrayListSanpham.get(position);
        holder.txtTensp.setText(sanpham.getTenSP());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(sanpham.getGiaSP()) + "Đ");
        holder.txtMota.setText(sanpham.getMoTaSP());
        Picasso.with(context).load(sanpham.getHinhAnhSP())
                .placeholder(R.drawable.imgload)
                .error(R.drawable.imgerror)
                .into(holder.imgSanpham);
        holder.btnImgDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xoa o day
                productId = sanpham.getId();
                deleteProductVolley(productId, position);
                //showDeleteDialog(position);
            }
        });

        holder.btnImgEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckConnect.ShowToast_Short(context, "check");
                //update product
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", String.valueOf(sanpham.getId()));
                intent.putExtra("tensanpham", sanpham.getTenSP());
                intent.putExtra("giasanpham", String.valueOf(sanpham.getGiaSP()));
                intent.putExtra("hinhanhsanpham", sanpham.getHinhAnhSP());
                intent.putExtra("motasanpham", sanpham.getMoTaSP());
                intent.putExtra("idsanpham", String.valueOf(sanpham.getIdSP()));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtTensp, txtGia, txtMota;
        public ImageView imgSanpham, btnImgEditProduct, btnImgDeleteProduct;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgSanpham = itemView.findViewById(R.id.img_admin_product);
            txtTensp = itemView.findViewById(R.id.txtTensp_admin_product);
            txtGia = itemView.findViewById(R.id.txtGia_admin_product);
            txtMota = itemView.findViewById(R.id.txtMota_admin_product);

            btnImgEditProduct = itemView.findViewById(R.id.btn_editProduct_img);
            btnImgDeleteProduct = itemView.findViewById(R.id.btn_deleteProduct_img);
        }
    }

    private void deleteProductVolley(int productId, int position) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Server.delete_product;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CheckConnect.ShowToast_Short(context,"Xóa thành công id: " +productId);
                        removeItem(position); // Loại bỏ sản phẩm khỏi danh sách
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_Short(context, error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> mydata = new HashMap<>();
                mydata.put("id", String.valueOf(productId));
                return mydata;
            }
        };
        queue.add(request);
    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa sản phẩm");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProductVolley(productId, position);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void removeItem(int position) {
        arrayListSanpham.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrayListSanpham.size());
    }

}
