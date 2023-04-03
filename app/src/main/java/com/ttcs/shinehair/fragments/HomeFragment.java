package com.ttcs.shinehair.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Activity.DatLichActivity;
import com.ttcs.shinehair.Activity.LichSuDatActivity;
import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.CustomsAdapter.DichVuAdapter;
import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.HomeAdminActivity;
import com.ttcs.shinehair.staff.StaffHomeActivity;

import java.util.ArrayList;
import java.util.Collections;


public class HomeFragment extends Fragment {
    private RecyclerView rDichVu;
    private TextView txtname, txtChuVu;
    LinearLayout lndatlich, car_view_history;
    CardView car_staff;
    CardView car_admin;
    private ArrayList<DichVu> data;
    private int viTri;
    DatabaseHelper mDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_home, container, false );
        rDichVu = (RecyclerView) view.findViewById( R.id.rec_dich_vu );
        txtname = view.findViewById( R.id.txt_user_name );
        txtChuVu = view.findViewById( R.id.txtChucVu );
        lndatlich = view.findViewById( R.id.lnDatLich );
        car_view_history = view.findViewById( R.id.car_view_history );
        car_staff = view.findViewById( R.id.car_staff );
        car_admin = view.findViewById( R.id.car_admin );

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dangnhap", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        data = new ArrayList<>();
        mDatabaseHelper = new DatabaseHelper(getActivity());

        Cursor cursor = mDatabaseHelper.getData("SELECT * FROM dichvu");
        data.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDichVu = cursor.getString(1);
            String giaDichVu = cursor.getString(2);
            String chiTiet = cursor.getString(3);
            byte[] hinhanh = cursor.getBlob(4);
            data.add(new DichVu(id, tenDichVu, giaDichVu, chiTiet, hinhanh));
        }

        Collections.shuffle(data);
        DichVuAdapter adapter = new DichVuAdapter(getActivity(), data, "hienthi");
        GridLayoutManager gridLayoutManager = new GridLayoutManager( getActivity(), 2 );
        rDichVu.setLayoutManager( gridLayoutManager );
        rDichVu.setAdapter(adapter);



        String email = sharedPreferences.getString( "username", "");
        String pass = sharedPreferences.getString( "pass"," " );
        if (email != null) {
            txtname.setText(email);
        }

        // lấy chức vụ từ database
        int chucVu = mDatabaseHelper.getUserType( email );
        if (chucVu == 1) {
            txtChuVu.setText( "Quản Trị" );
            car_admin.setVisibility( View.VISIBLE );

        } else if (chucVu == 2) {
            txtChuVu.setText( "Nhân viên" );
            car_staff.setVisibility(View.VISIBLE);
        } else {
        }

        car_admin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), HomeAdminActivity.class ) );
            }
        } );

        car_staff.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), StaffHomeActivity.class ) );
            }
        } );


        lndatlich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getActivity(), DatLichActivity.class));
            }
        } );

        car_view_history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), LichSuDatActivity.class));
            }
        } );

        return view;

    }

}