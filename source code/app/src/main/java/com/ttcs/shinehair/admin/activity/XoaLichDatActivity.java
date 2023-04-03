package com.ttcs.shinehair.admin.activity;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.CustomsAdapter.DatLichAdapter;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class XoaLichDatActivity extends AppCompatActivity {
    private RecyclerView rcView;
    ArrayList<LichSu> data;
    DatabaseHelper databaseHelper;
    DatLichAdapter datLichAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_xoa_lich_dat );
        rcView = findViewById( R.id.rcView );
        rcView.setHasFixedSize( true );
        rcView.setLayoutManager( new LinearLayoutManager( this ) );
        databaseHelper = new DatabaseHelper( this );
        
        Cursor cursor = databaseHelper.getData( "SELECT * FROM datlich" );
        data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String ngaydat = cursor.getString( 1 );
            String giodat = cursor.getString( 2 );
            String idtendichvu = cursor.getString( 3 );
            String emailkhachhang = cursor.getString( 4 );
            String emailnhanvien = cursor.getString( 5 );
            String trangthai = cursor.getString( 6 );
            String ghichu = cursor.getString( 7 );
            data.add( new LichSu( id, ngaydat, giodat, idtendichvu, emailkhachhang, emailnhanvien, trangthai, ghichu ) );
        }
        datLichAdapter = new DatLichAdapter( this, data, "xoa");
        rcView.setAdapter( datLichAdapter );
        

    }
}