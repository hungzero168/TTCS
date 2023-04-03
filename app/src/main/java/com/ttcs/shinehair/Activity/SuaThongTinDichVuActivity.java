package com.ttcs.shinehair.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.CustomsAdapter.DichVuAdapter;
import com.ttcs.shinehair.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SuaThongTinDichVuActivity extends AppCompatActivity {
    private EditText edtTenDichVu, edtGiaDichVu, edtChiTiet;
    private Button btnSave;
    private DatabaseHelper databaseHelper;
    private DichVu dichVu;
    private DichVuAdapter dichVuAdapter;
    private ImageView img;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sua_thong_tin_dich_vu );

        // Khởi tạo database helper
        databaseHelper = new DatabaseHelper(this);

        // Lấy dữ liệu của DichVu từ intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        dichVu = databaseHelper.getDichVuById(id);

        // Ánh xạ các trường
        edtTenDichVu = findViewById(R.id.edtTenDichVu);
        edtGiaDichVu = findViewById(R.id.edtGiaDichVu);
        edtChiTiet = findViewById(R.id.edtChiTiet);
        btnSave = findViewById(R.id.btnSave);
        img = findViewById( R.id.img );

        // Hiển thị thông tin của DichVu lên các trường tương ứng
        edtTenDichVu.setText(dichVu.getTenDichVu());
        edtGiaDichVu.setText(dichVu.getGiaDichVu());
        edtChiTiet.setText(dichVu.getChiTiet());
        byte[] hinhanh = databaseHelper.getHinhAnh(dichVu.getID());
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);
        }







        // Xử lý sự kiện khi người dùng nhấn nút "Lưu"
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lấy thông tin đã được sửa từ các trường
                String tenDichVu = edtTenDichVu.getText().toString().trim();
                String giaDichVu = edtGiaDichVu.getText().toString().trim();
                String chiTiet = edtChiTiet.getText().toString().trim();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress( Bitmap.CompressFormat.PNG, 100, byteArray );
                byte[] hinhanh = byteArray.toByteArray();

                // Cập nhật đối tượng DichVu và lưu vào database
                dichVu.setTenDichVu(tenDichVu);
                dichVu.setGiaDichVu(giaDichVu);
                dichVu.setChiTiet(chiTiet);
                dichVu.setHinh(hinhanh);

                if (databaseHelper.updateDichVu(dichVu)) {
                    Toast.makeText(SuaThongTinDichVuActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("result", "success");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(SuaThongTinDichVuActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
        } );
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream( uri );
                Bitmap bitmap = BitmapFactory.decodeStream( inputStream );
                img.setImageBitmap( bitmap );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult( requestCode, resultCode, data );
    }
}