package com.ttcs.shinehair.CustomsAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.activity.ChiTietDatLichActivity;

import java.util.ArrayList;

public class DatLichAdapter extends RecyclerView.Adapter<DatLichAdapter.ViewHolder> {
    Context context;
    ArrayList<LichSu> data;

    String type;
    DatabaseHelper databaseHelper;

    public DatLichAdapter(Context context, ArrayList<LichSu> data) {
        this.context = context;
        this.data = data;
    }

    public DatLichAdapter(Context context, ArrayList<LichSu> data, String type) {
        this.context = context;
        this.data = data;
        this.type = type;
    }

    @NonNull
    @Override
    public DatLichAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.layout_quanlylich, parent, false);
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull DatLichAdapter.ViewHolder holder, int position) {
        databaseHelper = new DatabaseHelper( context );
        if (data.get( position ).getEmailkhachhang() != null) {
            holder.txtten.setText( data.get( position ).getEmailkhachhang() );
        }
        if (data.get( position ).getNgaydat() != null) {
            holder.txtngaydat.setText( data.get( position ).getNgaydat() );
        }
        if (data.get( position ).getGiodat() != null) {
            holder.txtgiodat.setText( data.get( position ).getGiodat() );
        }
        String tendichvu = ( databaseHelper.getTenDichVu(data.get(position).getId()) );
        if (tendichvu != null) {
            holder.txttendichvu.setText(tendichvu);
        }
        String tenkhach = databaseHelper.getName(data.get(position).getEmailkhachhang());
        if (tenkhach != null) {
            holder.txtten.setText(tenkhach);
        }

        if (type.equals("xoa")) {
            holder.btnXoa.setVisibility( View.VISIBLE );
        } else if (type.equals("tong")) {
            holder.txtten.setVisibility( View.GONE );
        }
        holder.btnXoa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.deleteDatLich( data.get( position ).getId() )) {
                    data.remove( position );
                    notifyDataSetChanged();
                    Toast.makeText( context, "Xóa thành công", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( context, "Xóa thất bại", Toast.LENGTH_SHORT ).show();
                }


                // databaseHelper.deleteDatLich( data.get( position ).getId() );
                // data.remove( position );
                // notifyDataSetChanged();
            }
        } );

        holder.ln_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, ChiTietDatLichActivity.class );
                intent.putExtra( "id", data.get( position ).getId() );
                context.startActivity( intent );
            }
        } );

        


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txtngaydat, txtgiodat, txttendichvu;
        LinearLayout ln_item;
        Button btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            ln_item = itemView.findViewById( R.id.ln_item );
            txtten = itemView.findViewById( R.id.txtten );
            txtngaydat = itemView.findViewById( R.id.txtngaydat );
            txtgiodat = itemView.findViewById( R.id.txtgiodat );
            txttendichvu = itemView.findViewById( R.id.txttendichvu );
            btnXoa = itemView.findViewById( R.id.btnXoa );
            
        }
    }
}

