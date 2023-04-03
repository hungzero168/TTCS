package com.ttcs.shinehair.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.R;

public class QuanLyAccActivity extends AppCompatActivity {
    private Button btnQLTaiKhoan;
    private Button btnXoaTaiKhoan;
    private Button btnSuaThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quan_ly_acc );
        btnQLTaiKhoan = findViewById( R.id.btnQLTaiKhoan );
        btnXoaTaiKhoan = findViewById( R.id.btnXoaTaiKhoan );
        btnSuaThongTin = findViewById( R.id.btnSuaThongTin );

        btnSuaThongTin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyAccActivity.this, SuaThongTinActivity.class) );
            }
        } );

        btnQLTaiKhoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyAccActivity.this, TKStaffActivity.class) );
            }
        } );

        btnXoaTaiKhoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyAccActivity.this, XoaAccActivity.class) );
            }
        } );
    }
}