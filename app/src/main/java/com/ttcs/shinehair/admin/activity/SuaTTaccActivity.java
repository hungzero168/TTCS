package com.ttcs.shinehair.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.R;

public class SuaTTaccActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtsdt,edtadd;
    private Button btnUpdate;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sua_ttacc );
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtsdt = findViewById(R.id.edtsdt);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtadd = findViewById( R.id.edtAdd );


        databaseHelper = new DatabaseHelper( SuaTTaccActivity.this);

        Intent intent = getIntent();
        String email = intent.getStringExtra( "email" );
        // hiển thị thông tin người dùng đang đăng nhập lên các EditText
        edtEmail.setText(email);
//        edtEmail.setEnabled( false );
        edtName.setText(databaseHelper.getName(email));
        edtsdt.setText(databaseHelper.getPhone(email));
        edtadd.setText( databaseHelper.getAdd( email ) );

        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtsdt.getText().toString();
                String add = edtadd.getText().toString();
                byte[] hinhanh=null;
                if (databaseHelper.updateInfo(email, name, phone,add, hinhanh)) {
                    Toast.makeText(SuaTTaccActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SuaTTaccActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        } );
    }
}