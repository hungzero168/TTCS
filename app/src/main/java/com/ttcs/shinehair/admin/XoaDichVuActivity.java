package com.ttcs.shinehair.admin;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.CustomsAdapter.DichVuAdapter;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class XoaDichVuActivity extends AppCompatActivity {
    RecyclerView lvXoaDichVu;
    DatabaseHelper db;
    ArrayList<DichVu> data;
    DichVuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_dich_vu);

        lvXoaDichVu = findViewById(R.id.rcView);
        data = new ArrayList<>();
        db = new DatabaseHelper(this);

        // Lấy dữ liệu từ database và đổ vào listview
        Cursor cursor = db.getData("SELECT * FROM dichvu");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDichVu = cursor.getString(1);
            String giaDichVu = cursor.getString(2);
            String chiTiet = cursor.getString(3);
            data.add(new DichVu(id, tenDichVu, giaDichVu, chiTiet));
        }
        String type = "xoa";
        adapter = new DichVuAdapter(this, data, type);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( XoaDichVuActivity.this, 2 );
        lvXoaDichVu.setLayoutManager( gridLayoutManager );
        lvXoaDichVu.setAdapter(adapter);
    }
}
