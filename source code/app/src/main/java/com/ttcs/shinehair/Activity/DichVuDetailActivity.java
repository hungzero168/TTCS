package com.ttcs.shinehair.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.R;

public class DichVuDetailActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private DichVu dichVu;
    private TextView tvTenDichVu, tvGiaDichVu, tvChiTiet;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_dich_vu_detail);

        tvTenDichVu = findViewById(R.id.tv_ten_dich_vu);
        tvGiaDichVu = findViewById(R.id.tv_gia_dich_vu);
        tvChiTiet = findViewById(R.id.tv_chi_tiet);
        img = findViewById( R.id.img1 );


        // Lấy ID được truyền từ Intent
        int id = getIntent().getIntExtra("id", -1);

        // Khởi tạo database helper và lấy thông tin của đối tượng DichVu tương ứng
        databaseHelper = new DatabaseHelper(this);
        dichVu = databaseHelper.getDichVuById(id);

        // Hiển thị thông tin lên các view
        img.setImageResource( R.drawable.bg_main2 );
        tvTenDichVu.setText(dichVu.getTenDichVu());
        tvGiaDichVu.setText(dichVu.getGiaDichVu());
        tvChiTiet.setText(dichVu.getChiTiet());
        byte[] hinhanh = databaseHelper.getHinhAnh( dichVu.getID() );
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);
        }
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_dich_vu_detail );
//    }
}