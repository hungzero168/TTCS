package com.ttcs.shinehair.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.R;

public class ChangePassActivity extends AppCompatActivity {

    private TextInputEditText edtOldPass, edtpass1, edtpass2;
    private Button btnUp;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edtOldPass = findViewById(R.id.edtpassOld);
        edtpass1 = findViewById(R.id.edtpass);
        edtpass2 = findViewById(R.id.edtpass2);
        btnUp = findViewById(R.id.btnUpdate);


        SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // lấy tài khoản từ Preference

        String email = sharedPreferences.getString( "username", "");
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đổi mật khẩu
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtpass1.getText().toString();
                String newPass2 = edtpass2.getText().toString();

                databaseHelper = new DatabaseHelper(ChangePassActivity.this);


                if (oldPass.isEmpty() || newPass.isEmpty() || newPass2.isEmpty()) {
                    Toast.makeText(ChangePassActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPass.equals(newPass2) && databaseHelper.checkEmailPassword(email, oldPass)) {
                        databaseHelper.changePassword(email, newPass);
                        Toast.makeText(ChangePassActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(ChangePassActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });


        

    }
}