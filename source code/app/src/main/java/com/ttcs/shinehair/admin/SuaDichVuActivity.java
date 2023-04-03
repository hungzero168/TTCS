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

public class SuaDichVuActivity extends AppCompatActivity {
    private RecyclerView rDichVu;
    DatabaseHelper db;
    ArrayList<DichVu> data;
    DichVuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_dich_vu);
        rDichVu = findViewById( R.id.rcView );


        data = new ArrayList<>();
        db = new DatabaseHelper(this);

        // Lấy dữ liệu từ database và đổ vào listview
        Cursor cursor = db.getData("SELECT * FROM dichvu");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDichVu = cursor.getString(1);
            String giaDichVu = cursor.getString(2);
            String chiTiet = cursor.getString(3);
            byte[] hinhanh = cursor.getBlob( 4 );
            data.add(new DichVu(id, tenDichVu, giaDichVu, chiTiet, hinhanh));
        }
        String type = "sua";
        adapter = new DichVuAdapter(this, data, type);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( SuaDichVuActivity.this, 2 );
        rDichVu.setLayoutManager( gridLayoutManager );
        rDichVu.setAdapter(adapter);
//        Intent intent = new Intent();
//        String kq = intent.getStringExtra("result");
//        if (kq.equals( "success"))
//            adapter.notifyDataSetChanged();
    }
}