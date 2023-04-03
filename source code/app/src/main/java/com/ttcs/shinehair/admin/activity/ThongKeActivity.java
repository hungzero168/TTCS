package com.ttcs.shinehair.admin.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.CustomsAdapter.DatLichAdapter;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView rcthongke;
    ArrayList<LichSu> data;
    DatabaseHelper databaseHelper;
    DatLichAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thong_ke );
        textView = findViewById( R.id.txttong );
        rcthongke = findViewById( R.id.rcthongke );

        data = new ArrayList<>();
        databaseHelper = new DatabaseHelper( ThongKeActivity.this );
        Cursor cursor = databaseHelper.getData( "SELECT * FROM datlich where trangthai = 2" );
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String ngaydat = cursor.getString( 1 );
            String giodat = cursor.getString( 2 );
            String idtendichvu = cursor.getString( 4 );
            String emailkhachhang = cursor.getString( 4 );
            String emailnhanvien = cursor.getString( 5 );
            String trangthai = cursor.getString( 6 );
            String ghichu = cursor.getString( 7 );
            String gia = databaseHelper.getGiaDichVu( idtendichvu );
            data.add( new LichSu( id, idtendichvu, ngaydat, giodat, gia, emailkhachhang, emailnhanvien, trangthai, ghichu) );
        }
        rcthongke.setHasFixedSize( true );
        rcthongke.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new DatLichAdapter( this, data, "tong");
        rcthongke.setAdapter( adapter );
        // tính tổng tiền lịch sử
        int tong = 0;
        for (int i = 0; i < data.size(); i++) {
            tong += Integer.parseInt( data.get( i ).getGia() );
        }
        String gia = String.valueOf( tong );

        textView.setText(gia+"k" );
    }
}