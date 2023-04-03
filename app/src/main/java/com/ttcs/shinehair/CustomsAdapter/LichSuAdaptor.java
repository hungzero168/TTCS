package com.ttcs.shinehair.CustomsAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class LichSuAdaptor extends RecyclerView.Adapter<LichSuAdaptor.ViewHolder> {
    Context context;
    ArrayList<LichSu> data;
    String isButtonVisible;

    public LichSuAdaptor(Context context, ArrayList<LichSu> data) {
        this.context = context;
        this.data = data;
    }

    public LichSuAdaptor(Context context, ArrayList<LichSu> data, String isButtonVisible) {
        this.context = context;
        this.data = data;
        this.isButtonVisible = isButtonVisible;
    }

    @NonNull
    @Override
    public LichSuAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.layout_lichsu,parent,false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuAdaptor.ViewHolder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("username", null);

        LichSu lichSu = data.get(position);
        holder.txttendichvu.setText(String.valueOf(lichSu.getIdtendichvu()));
        holder.txtngaydat.setText(lichSu.getNgaydat());
        holder.txtgiodat.setText(lichSu.getGiodat());
        holder.txtgia.setText(lichSu.getGia());

        if (isButtonVisible.equals("huy")) {
            holder.buttonCancel.setVisibility(View.VISIBLE);
            holder.btnxacnhan.setVisibility(View.GONE);
        } else if (isButtonVisible.equals("xacnhan")){
            holder.btnxacnhan.setVisibility( View.VISIBLE );
            holder.buttonCancel.setVisibility( View.GONE );
        }else if (isButtonVisible.equals( "xem" )){
            holder.buttonCancel.setVisibility(View.GONE);
            holder.btnxacnhan.setVisibility(View.GONE);

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txttendichvu;
        private TextView txtngaydat;
        private TextView txtgiodat;
        private TextView txtgia;
        private Button buttonCancel,btnxacnhan;
        private DatabaseHelper db;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txttendichvu = itemView.findViewById( R.id.txttendichvu );
            txtngaydat = itemView.findViewById( R.id.txtngaydat );
            txtgiodat = itemView.findViewById( R.id.txtgiodat );
            txtgia = itemView.findViewById( R.id.txtgia );
            buttonCancel = itemView.findViewById( R.id.buttonCancel );
            btnxacnhan = itemView.findViewById( R.id.btnXacNhan );
            db = new DatabaseHelper(context);


            SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
            String email = sharedPreferences.getString("username", null);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        LichSu lichSu = data.get(position);
                        int id = lichSu.getId();

                        if (db.updateHuyLichDat(id, "0")) {
//
                            Toast.makeText(context, "Đã hủy lịch", Toast.LENGTH_SHORT).show();
//                            notifyItemChanged(position); // cập nhật lại item được click
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    } else {
                        Log.e("Adapter", "Invalid position");
                    }
                }
            });

            btnxacnhan.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        LichSu lichSu = data.get(position);
                        int id = lichSu.getId();

                        if (db.updateHuyLichDat(id, "2")) {

                            Toast.makeText(context, "Xác nhân lịch thành công", Toast.LENGTH_SHORT).show();
//                            notifyItemChanged(position); // cập nhật lại item được click
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    } else {
                        Log.e("Adapter", "Invalid position");
                    }
                }
            } );

        }
    }
}
