package com.ttcs.shinehair.Activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.R;

public class InfoUserActivity extends AppCompatActivity {
    TextView txtname, txtSDT, txtEmail,txtDiaChi;
    ImageView img;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_info_user );
        txtname = findViewById( R.id.txtname );
        txtSDT = findViewById( R.id.txtSDT );
        txtEmail = findViewById( R.id.txtEmail );
        txtDiaChi = findViewById( R.id.txtDiaChi );
        img = findViewById(R.id.img  );


        SharedPreferences sharedPreferences = getSharedPreferences( "dangnhap", 0 );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // lấy tài khoản từ Preference
        String email = sharedPreferences.getString( "username", "" );
        String pass = sharedPreferences.getString( "pass"," " );

        databaseHelper = new DatabaseHelper( InfoUserActivity.this );

        txtEmail.setText(email);
        txtname.setText(databaseHelper.getName(email));
        txtSDT.setText(databaseHelper.getPhone(email));
        txtDiaChi.setText(databaseHelper.getAdd(email));
        byte[] hinhanh = databaseHelper.getHinhAnh(email);
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);
        }


    }
}