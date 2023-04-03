package com.ttcs.shinehair.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.R;

public class ChiTietDatLichActivity extends AppCompatActivity {
    private TextView txtngay;
    private TextView txtgio;
    private TextView txttendichvu;
    private TextView txtgia;
    private TextView txttenkhach;
    private TextView txtemailkhach;
    private TextView txttennhanvien;
    private TextView txtemailnhanvien;
    private TextView txttrangthai;
    private TextView txtghichu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_dat_lich );
        txtngay = findViewById( R.id.txtngay );
        txtgio = findViewById( R.id.txtgio );
        txttendichvu = findViewById( R.id.txttendichvu );
        txtgia = findViewById( R.id.txtgia );
        txttenkhach = findViewById( R.id.txttenkhach );
        txtemailkhach = findViewById( R.id.txtemailkhach );
        txttennhanvien = findViewById( R.id.txttennhanvien );
        txtemailnhanvien = findViewById( R.id.txtemailnhanvien );
        txttrangthai = findViewById( R.id.txttrangthai );
        txtghichu = findViewById( R.id.txtghichu );

        Intent intent = getIntent();
        int id = intent.getIntExtra( "id", 0 );

        DatabaseHelper databaseHelper = new DatabaseHelper( this );
        LichSu lichSu = databaseHelper.getdatlichById( id );
        if (lichSu != null) {
            txtngay.setText( lichSu.getNgaydat() );
            txtgio.setText( lichSu.getGiodat() );
//            txttendichvu.setText( databaseHelper.getTenDichVu( lichSu.getId() ) );
            String tendichvu = databaseHelper.getTenDichVu( Integer.parseInt( lichSu.getIdtendichvu() ) );
            if (tendichvu != null) {
                txttendichvu.setText(tendichvu);
            } else {
                txttendichvu.setText(" ");
            }
//            txtgia.setText( databaseHelper.getGiaDichVu( lichSu.getId() ) );
            String gia = databaseHelper.getGiaDichVu(lichSu.getIdtendichvu());
            if (gia != null) {
                txtgia.setText(gia+"k");
            } else {
                txtgia.setText(" ");
            }


            String e = lichSu.getEmailkhachhang();
             String tenkhach = databaseHelper.getName(e);
            if (tenkhach != null) {
                txttenkhach.setText(tenkhach);
            } else {
                txttenkhach.setText(" ");
            }

            txtemailkhach.setText( lichSu.getEmailkhachhang() );



            String tennhanvien = databaseHelper.getUserById( Integer.parseInt( lichSu.getIdnhanvien() ) ).getName();
            if (tennhanvien != null) {
                txttennhanvien.setText(tennhanvien);
            } else {
                txttennhanvien.setText(" ");
            }
//            txtemailnhanvien.setText( lichSu.getEmailnhanvien() );
            String emailnhanvien = databaseHelper.getUserById( Integer.parseInt( lichSu.getIdnhanvien() ) ).getEmail();
            if (emailnhanvien != null) {
                txtemailnhanvien.setText(emailnhanvien);
            } else {
                txtemailnhanvien.setText(" ");
            }
            if (lichSu.getTrangthai().equals( "0" )) {
                txttrangthai.setText( "Đã hủy" );
            } else if (lichSu.getTrangthai().equals( "1" )) {
                txttrangthai.setText( "Đã đặt lịch" );
            } else if (lichSu.getTrangthai().equals( "2" )) {
                txttrangthai.setText( "Thành công" );
            }
            txtghichu.setText( lichSu.getGhichu() );
        }
    }
}