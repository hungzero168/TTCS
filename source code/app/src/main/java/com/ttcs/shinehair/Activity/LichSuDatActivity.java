package com.ttcs.shinehair.Activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.CustomsAdapter.LichSuAdaptor;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class LichSuDatActivity extends AppCompatActivity {
    RecyclerView recyclerView, rclichdadat;
    ArrayList<LichSu> data;
    DatabaseHelper db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_dat);
        recyclerView = findViewById(R.id.rclichdat);
        rclichdadat = findViewById( R.id.rclichdadat );

        sharedPreferences = getSharedPreferences("dangnhap", MODE_PRIVATE);
        String email = sharedPreferences.getString("username", null);

        db = new DatabaseHelper(this);
        Cursor cursor = db.getData("SELECT * FROM datlich WHERE emailkhachhang = '" + email + "' and trangthai = 1");
        data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int idtendichvu = cursor.getInt(4);
            String tendichvu = db.getTenDichVu(idtendichvu);
            String ngaydat = cursor.getString(1);
            String giodat = cursor.getString(2);
            String idgiadichvu = cursor.getString(4);
            String gia = db.getGiaDichVu(idgiadichvu)+"k";
            data.add(new LichSu(id, tendichvu, ngaydat, giodat, gia));
        }
//        db.updateLichDat(email, "2");
        LichSuAdaptor lichSuAdaptor = new LichSuAdaptor(this, data, "huy");
        recyclerView.setAdapter(lichSuAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lichSuAdaptor.notifyDataSetChanged();





        Cursor cursor1 = db.getData( "select * from datlich where emailkhachhang = '"+ email +"' and trangthai = 2" );
        data = new ArrayList<>();
        while (cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            int idtendichvu = cursor1.getInt(4);
            String tendichvu = db.getTenDichVu(idtendichvu);
            String ngaydat = cursor1.getString(1);
            String giodat = cursor1.getString(2);
            String idgiadichvu = cursor1.getString(4);
            String gia = db.getGiaDichVu(idgiadichvu)+"k";
            data.add(new LichSu(id, tendichvu, ngaydat, giodat, gia));
        }
        LichSuAdaptor lichSuAdaptor1 = new LichSuAdaptor(this, data, "xem");
        rclichdadat.setAdapter(lichSuAdaptor1);
        rclichdadat.setLayoutManager(new LinearLayoutManager(this));
        lichSuAdaptor1.notifyDataSetChanged();

    }
}
