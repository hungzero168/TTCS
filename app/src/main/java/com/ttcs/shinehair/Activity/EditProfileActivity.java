package com.ttcs.shinehair.Activity;

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
import com.ttcs.shinehair.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditProfileActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtsdt,edtadd;
    private Button btnUpdate;
    ImageView img;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtsdt = findViewById(R.id.edtsdt);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtadd = findViewById( R.id.edtAdd );
        img = findViewById( R.id.img );


        SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // lấy tài khoản từ Preference

        String email = sharedPreferences.getString( "username", "");
        String pass = sharedPreferences.getString( "pass"," " );
        databaseHelper = new DatabaseHelper(EditProfileActivity.this);


        // hiển thị thông tin người dùng đang đăng nhập lên các EditText
        edtEmail.setText(email);
        edtEmail.setEnabled( false );
        edtName.setText(databaseHelper.getName(email));
        edtsdt.setText(databaseHelper.getPhone(email));
        edtadd.setText( databaseHelper.getAdd( email ) );
        byte[] hinhanh = databaseHelper.getHinhAnh(email);
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);
        }

        
        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtsdt.getText().toString();
                String add = edtadd.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress( Bitmap.CompressFormat.PNG, 100, byteArray );
                byte[] hinhanh = byteArray.toByteArray();


                if (databaseHelper.updateInfo(email, name, phone, add, hinhanh)) {
                    Toast.makeText(EditProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        } );

        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 999);
            }
        } );



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == 999 && resultCode == RESULT_OK && data != null){
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