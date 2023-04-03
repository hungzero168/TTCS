package com.ttcs.shinehair.CustomsAdapter;

import android.content.Context;
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

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.Users;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    Context context;
    ArrayList<Users> data = new ArrayList<>();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DatabaseHelper databaseHelper;
    private int selectedPosition = -1;

    public StaffAdapter(Context context, ArrayList<Users> data) {
        this.context = context;
        this.data = data;
    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from( context ).inflate( R.layout.layout_staff,parent,false );
//        ViewHolder viewHolder = new ViewHolder( view );
//        return viewHolder;
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_staff, parent, false);


        sharedPreferences = context.getSharedPreferences("nhanvien", Context.MODE_PRIVATE);
        String emailnv = sharedPreferences.getString( "emailnv", "");
        boolean emailCheck = sharedPreferences.getBoolean("dachonnhanvien", false);

        if (emailCheck) {
            // đã chọn dịch vụ, set lại trạng thái đã chọn
            for (int i = 0; i < data.size(); i++) {
                if (emailnv.equals(data.get(i).getEmail() )) {
                    selectedPosition = i;
                    break;
                }
            }
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.ViewHolder holder, int position) {
//        if (holder != null && holder.txtname != null) {
            holder.txtname.setText(data.get(position).getName());
            byte[] img1 = data.get(position).getHinh();
        if (img1 != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img1, 0, img1.length);
            holder.img.setImageBitmap(bitmap);
        }
//        else {
//            holder.img.setImageResource(R.drawable.logo_new);
//        }
        if (selectedPosition == position) {
            holder.btnchon.setText("Đã chọn");

        } else {
            holder.btnchon.setText("Chọn");
        }
        sharedPreferences = context.getSharedPreferences("nhanvien", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        Button btnchon;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            txtname = itemView.findViewById( R.id.txtname );
            btnchon = itemView.findViewById( R.id.btnchon );
            img = itemView.findViewById( R.id.img );

            btnchon.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String emailnv = data.get( position ).getEmail();
                    int idnhanvien = data.get( position ).getId();

                    if (position == selectedPosition) {
                        // Item đã được chọn, hủy chọn
                        selectedPosition = -1;
//                        btnChonDV.setText("Chọn");
                        notifyDataSetChanged();
                        editor.clear();
                        editor.commit();
                    } else {
                        selectedPosition = position;
                        notifyDataSetChanged();
                        editor.putString( "emailnv", emailnv);
                        editor.putInt( "idnhanvien",idnhanvien );
                        editor.putBoolean("dachonnhanvien", true);
                        editor.apply();
                        editor.commit();
                    }
                }
            } );
        }
    }
}
