package com.ttcs.shinehair.CustomsAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ttcs.shinehair.Activity.DichVuDetailActivity;
import com.ttcs.shinehair.Activity.SuaThongTinDichVuActivity;
import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.ViewHolder> {
    private final String type;
    private Context context;
    private ArrayList<DichVu> data;
    private OnItemClickListener listener;
    private DatabaseHelper databaseHelper;
    ViewPager viewPager;
    private int selectedPosition = -1;
    private boolean isButtonSelected = false;
    private int value = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public void setDichVuList(ArrayList<DichVu> dichVuList) {
        this.data = dichVuList;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public DichVuAdapter(Context context, ArrayList<DichVu> data, String type) {
        this.context = context;
        this.data = data;

        databaseHelper = new DatabaseHelper(context);
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dich_vu, parent, false);

        SharedPreferences sharedPreferences = context.getSharedPreferences("dichvu", 0);
        int id = sharedPreferences.getInt("id", 0);
        boolean dachon = sharedPreferences.getBoolean("dachondichvu", false);

        if (dachon) {
            // đã chọn dịch vụ, set lại trạng thái đã chọn
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getID() == id) {
                    selectedPosition = i;
                    break;
                }
            }
        }

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DichVu dichVu = data.get(position);
        holder.tvTenDichVu.setText(dichVu.getTenDichVu());
        holder.tvGiaDichVu.setText(dichVu.getGiaDichVu());

        byte[] hinhanh = databaseHelper.getHinhAnh(data.get(position).getID());
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            holder.img_dich_vu.setImageBitmap(bitmap);
        } else {
            holder.img_dich_vu.setImageResource(R.drawable.logo_new);
        }
//        holder.tvChiTiet.setText(dichVu.getChiTiet());
        if (selectedPosition == position) {
            holder.btnChonDV.setText("Đã chọn");

        } else {
            holder.btnChonDV.setText("Chọn");
        }
        sharedPreferences = context.getSharedPreferences("dichvu", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        
//        for (int i = 0; i < getItemCount(); i++) {
//            // Nếu ID của dịch vụ đã được chọn trùng với ID của dịch vụ trong danh sách thì hiển thị nút "Đã chọn"
//            if (data.get(i).getID() == 1) {
//                holder.btnChonDV.setText("Đã chọn");
//            }
//        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDichVu, tvGiaDichVu, tvChiTiet;
        Button btndel,btnThongTin,btnChonDV,btnSuaDV;
        ImageView img_dich_vu;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);


            tvTenDichVu = itemView.findViewById(R.id.txt_ten_dich_vu);
            tvGiaDichVu = itemView.findViewById(R.id.txt_gia_dich_vu);
            btnThongTin = itemView.findViewById( R.id.btn_xemTT );
            btnChonDV = itemView.findViewById( R.id.btn_chonDV );
            btnSuaDV = itemView.findViewById( R.id.btn_suaDV );
            btndel = itemView.findViewById(R.id.btn_xoaDV);
            viewPager = itemView.findViewById( R.id.view_pager );
            img_dich_vu = itemView.findViewById( R.id.img_dich_vu );



            if(type.equals("hienthi")) {
                btnThongTin.setVisibility( View.VISIBLE );
            } else if (type.equals( "sua" )) {
                btnSuaDV.setVisibility( View.VISIBLE );
            } else if (type.equals( "xoa" )) {
                btndel.setVisibility( View.VISIBLE );
            } else if (type.equals( "chon" )) {
                btnChonDV.setVisibility( View.VISIBLE );
            }

            btnSuaDV.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = data.get(getAdapterPosition()).getID();
                    if (listener != null) {
                        listener.onItemClick(id);
                    }
                    // Truyền ID sang activity mới
                    Intent intent = new Intent(context, SuaThongTinDichVuActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            } );
            btnThongTin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ...
                    int id = data.get(getAdapterPosition()).getID();
                    if (listener != null) {
                        listener.onItemClick(id);
//                        btnThongTin.setText("Đã chọn");
                    }

                    // Truyền ID sang activity mới
                    Intent intent = new Intent(context, DichVuDetailActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });

            btnChonDV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    int id = data.get(position).getID();

                    if (position == selectedPosition) {
                        // Item đã được chọn, hủy chọn
                        selectedPosition = -1;
                        notifyDataSetChanged();
                        editor.clear();
                        editor.commit();
                    } else {
                        selectedPosition = position;
                        notifyDataSetChanged();
                        editor.putInt("id", id);
                        editor.putBoolean("dachondichvu", true);
                        editor.apply();
                        editor.commit();
                    }
                }
            });



            btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = data.get(getAdapterPosition()).getID();
                    if (listener != null) {
                        listener.onItemClick(id);
                    }
                    // Alert dialog to confirm delete
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa dịch vụ");
                    builder.setMessage("Bạn có chắc chắn muốn xóa dịch vụ này không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean result = databaseHelper.deleteData(id);
                            if (result) {
                                data.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), data.size());
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            });
        }
    }


}
