package com.ttcs.shinehair.admin;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ttcs.shinehair.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThemDichVuActivity extends AppCompatActivity {
    private EditText edtTenDV, edtChiTiet, edtGia;
    private ImageView img;
    private Button btnUp;
    private ArrayList<DichVu> data;
    private int count = 0;
    private DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dich_vu);

        edtTenDV = findViewById(R.id.edtTenDichVu);
        edtGia = findViewById(R.id.edtGiaDichVu);
        edtChiTiet = findViewById(R.id.edtThongTin);
        btnUp = findViewById(R.id.btnUpdate);
        img = findViewById( R.id.img );

        // lấy tài khoản từ Preference
        SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", 0);
        String email = sharedPreferences.getString( "username", "");
        String pass = sharedPreferences.getString( "pass"," " );

        mDatabaseHelper = new DatabaseHelper( ThemDichVuActivity.this );


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTenDV.getText().toString();
                String gia = edtGia.getText().toString();
                String ct = edtChiTiet.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress( Bitmap.CompressFormat.PNG, 100, byteArray );
                byte[] hinhanh = byteArray.toByteArray();


                if (ten.isEmpty() || gia.isEmpty()) {
                    Toast.makeText( ThemDichVuActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT ).show();
                    return;
                }


                boolean insertData = mDatabaseHelper.insertDichVu( ten, gia, ct, hinhanh);
                if (insertData) {
                    Toast.makeText( ThemDichVuActivity.this, "Thêm dịch vụ thành công", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {
                    Toast.makeText( ThemDichVuActivity.this, "Thêm dịch vụ thất bại", Toast.LENGTH_SHORT ).show();
                }

            }
        });

        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 666);
            }
        } );
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == 666 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap (bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
