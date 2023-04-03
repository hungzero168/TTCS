package com.ttcs.shinehair.CustomsAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.Users;
import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.activity.SuaTTaccActivity;

import java.util.ArrayList;

public class TaiKhoanAdaptor extends RecyclerView.Adapter<TaiKhoanAdaptor.ViewHolder> {


    Context context;
    ArrayList<Users> data;

    String type;
    DatabaseHelper databaseHelper ;

    public TaiKhoanAdaptor(Context context, ArrayList<Users> data) {
        this.context = context;
        this.data = data;
    }

    public TaiKhoanAdaptor(Context context, ArrayList<Users> data, String type) {
        this.context = context;
        this.data = data;
        this.type = type;
        this.databaseHelper = new DatabaseHelper( context );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chuyen_nv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanAdaptor.ViewHolder holder, int position) {
        if (data.get(position).getName() != null) {
            if (holder.txttentaikhoan != null) {
                holder.txttentaikhoan.setText(data.get(position).getName());
            }
        } else {
            if (holder.txttentaikhoan != null) {
                holder.txttentaikhoan.setText("");
            }
        }

        if (holder.txtemail != null) {
            holder.txtemail.setText(data.get(position).getEmail());
        }

        if (holder.txtchucVu != null) {
            if (data.get(position).getTypeUser() == 1) {
                holder.txtchucVu.setText("Quản trị");
            } else if (data.get(position).getTypeUser() == 2) {
                holder.txtchucVu.setText("Nhân viên");
            } else {
                holder.txtchucVu.setText("Thành viên");
            }
        }

        if (type.equals("quyen")) {
            holder.btnXacNhan.setVisibility( View.VISIBLE );
            holder.suaTT.setVisibility( View.VISIBLE );

        } else if (type.equals("xoa")) {
            holder.btnXoa.setVisibility(View.VISIBLE);
        } else if (type.equals( "sua" )) {
            holder.btnSua.setVisibility( View.VISIBLE );
        }
        // Kiểm tra null trước khi gọi setAdapter()
        if (holder.spinner != null) {
            String[] chucvu = {"Quản trị", "Nhân viên", "Thành viên"};
            ArrayAdapter<String> adapterChucVu = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, chucvu);
            adapterChucVu.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
            holder.spinner.setAdapter(adapterChucVu);

            holder.spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    holder.ChonChucVu = (String) adapterView.getItemAtPosition(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText( context, "Chọn chức vụ", Toast.LENGTH_SHORT ).show();
                }
            } );
//            DatabaseHelper databaseHelper = new DatabaseHelper( context );
            String email = data.get(position).getEmail(); // truy cập vào phần tử của data trong onBindViewHolder
            holder.btnXacNhan.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.ChonChucVu.equals("Quản trị")) {
                        databaseHelper.updateTypeUser(email, "1");
                        Toast.makeText( context, "Thành công", Toast.LENGTH_SHORT ).show();
                        notifyDataSetChanged();
                    } else if (holder.ChonChucVu.equals("Nhân viên")) {
                        databaseHelper.updateTypeUser(email, "2");
                        notifyDataSetChanged();
                        Toast.makeText( context, "Thành công", Toast.LENGTH_SHORT ).show();
                    } else {
                        databaseHelper.updateTypeUser(email, "0");
                        Toast.makeText( context, "Thành công", Toast.LENGTH_SHORT ).show();
                        notifyDataSetChanged();
                    }
                }
            } );

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        String ChonChucVu;
        TextView txttentaikhoan;
        TextView txtchucVu;
        TextView txtemail;
        Button btnXacNhan;
        Button btnXoa;
        private Button btnSua;
        LinearLayout suaTT;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinnerChucVu);
            txttentaikhoan = itemView.findViewById(R.id.txttentaikhoan);
            txtchucVu = itemView.findViewById(R.id.txtchucVu);
            txtemail = itemView.findViewById(R.id.txtemail);
            btnXacNhan = itemView.findViewById(R.id.btnXacNhan);
            btnXoa = itemView.findViewById( R.id.btnXoa );
            suaTT = itemView.findViewById(R.id.suaTT);
            btnSua = itemView.findViewById( R.id.btnSua );



             btnSua.setOnClickListener( new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     String email = data.get( getAdapterPosition() ).getEmail();
                     Intent intent = new Intent( context, SuaTTaccActivity.class );
                     intent.putExtra( "email", email );
                     context.startActivity( intent );

                 }
             } );

//            data = new ArrayList<>();
            btnXoa.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = data.get( getAdapterPosition() ).getEmail();
                    AlertDialog.Builder builder = new AlertDialog.Builder( context );
                    builder.setTitle( "Xóa tài khoản" );
                    builder.setMessage( "Bạn có muốn xóa tài khoản này không?" );
                    builder.setPositiveButton( "Có", (dialogInterface, i) -> {
                        databaseHelper.deleteUser( email );
                        Toast.makeText( context, "Xóa thành công", Toast.LENGTH_SHORT ).show();
                        data.remove( getAdapterPosition() );
                        notifyDataSetChanged();

                    } );
                    builder.setNegativeButton( "Không", (dialogInterface, i) -> {

                    } );
                    builder.show();
                    builder.create();

                }
            } );



        }
    }
}
