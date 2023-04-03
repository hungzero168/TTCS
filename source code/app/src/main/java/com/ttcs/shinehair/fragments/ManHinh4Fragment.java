package com.ttcs.shinehair.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.Class.Users;
import com.ttcs.shinehair.R;

import java.util.ArrayList;


public class ManHinh4Fragment extends Fragment {

    TextView txtTenDV,txtTenNV, txtTG,txtTGG;
    TextView txtGia;
    Button btndatlich, btnhuylich;
    EditText edtghichu;
    ViewPager2 viewPager;
    DatabaseHelper db;

    ArrayList<DichVu> data;
    DichVu dichVu;
    Users users;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_man_hinh4, container, false );

        viewPager = getActivity().findViewById( R.id.view_pager );
        txtTenDV = view.findViewById( R.id.txttenDV );
        txtTenNV = view.findViewById( R.id.txttenNV );
        txtGia = view.findViewById( R.id.txtGia );
        txtTG = view.findViewById( R.id.txtTG );
        txtTGG = view.findViewById( R.id.txtTGG );
        btndatlich = view.findViewById( R.id.btndatlich );
        btnhuylich = view.findViewById( R.id.btnhuylich );
        edtghichu = view.findViewById( R.id.edtghichu );

        updateUI();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dichvu", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int id = sharedPreferences.getInt( "id",0);


        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("dangnhap", 0);
        SharedPreferences.Editor editor1 = sharedPreferences2.edit();
        String emailkhachhang = sharedPreferences2.getString( "username",null );


        SharedPreferences nvSharedPreferences = getActivity().getSharedPreferences("nhanvien", 0);
        SharedPreferences.Editor editor2 = nvSharedPreferences.edit();
        String emailvn = nvSharedPreferences.getString("emailnv", null);

        SharedPreferences ngaySharedPreferences = getActivity().getSharedPreferences("ngay", 0);
        SharedPreferences.Editor editor3 = ngaySharedPreferences.edit();
        String ngaychon = ngaySharedPreferences.getString("ngaychon", null);

        SharedPreferences gioSharedPreferences = getActivity().getSharedPreferences("date", 0);
        SharedPreferences.Editor editor4 = gioSharedPreferences.edit();
        String gio = gioSharedPreferences.getString("gio", null);




        btnhuylich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear().apply();editor.commit();
//                editor1.clear().apply();editor1.commit();
                editor2.clear().apply();editor2.commit();
                editor3.clear().apply();editor3.commit();
                editor4.clear().apply();editor4.commit();
                Toast.makeText( getActivity(), "Hủy bỏ thành công", Toast.LENGTH_SHORT ).show();
                getActivity().finish();
            }
        } );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    public void updateUI() {
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dichvu", 0);
//        int id = sharedPreferences.getInt( "id",0);
//
//        SharedPreferences nvSharedPreferences = getActivity().getSharedPreferences("nhanvien", 0);
//        String emailvn = nvSharedPreferences.getString("emailnv", null);
//
//        SharedPreferences ngaySharedPreferences = getActivity().getSharedPreferences("ngay", 0);
//        String ngaychon = ngaySharedPreferences.getString("ngaychon", null);
//
//        SharedPreferences gioSharedPreferences = getActivity().getSharedPreferences("date", 0);
//        String gio = gioSharedPreferences.getString("gio", null);

        db = new DatabaseHelper( getActivity());

        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("dangnhap", 0);
        SharedPreferences.Editor editor1 = sharedPreferences2.edit();
        String emailkhachhang = sharedPreferences2.getString( "username",null );


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dichvu", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int id = sharedPreferences.getInt( "id",0);

        SharedPreferences nvSharedPreferences = getActivity().getSharedPreferences("nhanvien", 0);
        SharedPreferences.Editor editor2 = nvSharedPreferences.edit();
        String emailvn = nvSharedPreferences.getString("emailnv", null);

        SharedPreferences ngaySharedPreferences = getActivity().getSharedPreferences("ngay", 0);
        SharedPreferences.Editor editor3 = ngaySharedPreferences.edit();
        String ngaychon = ngaySharedPreferences.getString("ngaychon", null);

        SharedPreferences gioSharedPreferences = getActivity().getSharedPreferences("date", 0);
        SharedPreferences.Editor editor4 = gioSharedPreferences.edit();
        String gio = gioSharedPreferences.getString("gio", null);
        btndatlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String trangthai = "1";
//                int idnhanvien = sharedPreferences.getInt("idnhanvien", 0);
                int idnhanvien = 0;
                if (emailvn !=null){
                    idnhanvien = db.getUserByEmail(emailvn).getId();
                }

                // Check if all required values are available
                if (ngaychon != null && gio != null && emailvn != null) {
                    String ghichu = edtghichu.getText().toString();
                    // Call the insertDatLich() method with the required parameters
                    db.insertDatLich(ngaychon, gio, emailkhachhang, id, idnhanvien, trangthai, ghichu);
                    Toast.makeText(getActivity(), "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                    editor.clear().apply();editor.commit();
//                    editor1.clear().apply();editor1.commit();
                    editor2.clear().apply();editor2.commit();
                    editor3.clear().apply();editor3.commit();
                    editor4.clear().apply();editor4.commit();



                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Vui lòng chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (id != 0) {
//            btndatlich.setEnabled(true);
            int giaDichVu = db.getGiaDichVuById(id);
            txtGia.setText( String.valueOf( giaDichVu )+"k" );
            dichVu = db.getDichVuById( id );
            txtTenDV.setText( dichVu.getTenDichVu());
        } else {
//            btndatlich.setEnabled(false);
//            Toast.makeText( getActivity(), "Chọn dịch vụ", Toast.LENGTH_SHORT ).show();
            txtTenDV.setText( "Chọn dịch vụ");
        }

        if (emailvn != null) {
//            btndatlich.setEnabled(true);
            users = db.getUserByEmail( emailvn );
            txtTenNV.setText( users.getName());
        } else {
//            btndatlich.setEnabled(false);
//            Toast.makeText( getActivity(), "Chọn nhân viên", Toast.LENGTH_SHORT ).show();
            txtTenNV.setText( "Chọn nhân viên");
        }
        if (ngaychon != null) {
//            btndatlich.setEnabled(true);
            txtTG.setText(ngaychon);
        } else {
//            btndatlich.setEnabled(false);
//            Toast.makeText( getActivity(), "chọn ngày", Toast.LENGTH_SHORT ).show();
            txtTG.setText("Chọn ngày");
        }

        if (gio != null) {
//            btndatlich.setEnabled(true);
            txtTGG.setText(gio);
        } else {
//            btndatlich.setEnabled(false);
//            Toast.makeText( getActivity(), "chọn giờ", Toast.LENGTH_SHORT ).show();
            txtTGG.setText("Chọn Giờ");
        }
    }
}