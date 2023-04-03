package com.ttcs.shinehair.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.activity.QuanLyAccActivity;
import com.ttcs.shinehair.admin.activity.ThongKeActivity;

public class HomeAdminActivity extends AppCompatActivity {
    Button  btnQLDatLich;
    Button btnQuanLyDichVu;
    Button btnQLTaiKhoan;
    Button btnThongke;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        btnQLDatLich = findViewById( R.id.btnQLDatLich );
        btnQuanLyDichVu = findViewById( R.id.btnQuanLyDichVu );
        btnQLTaiKhoan = findViewById( R.id.btnQLTaiKhoan );
        btnThongke = findViewById( R.id.btnThongke );


        btnQLTaiKhoan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeAdminActivity.this, QuanLyAccActivity.class ) );
            }
        } );
        btnQuanLyDichVu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeAdminActivity.this, QuanLyDichVuActivity.class ) );
            }
        } );
        btnQLDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                 Intent intent = new Intent(HomeAdminActivity.this, QuanLyDatLichActivity.class);
                startActivity(intent);
            }
        });

        btnThongke.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeAdminActivity.this, ThongKeActivity.class ) );
            }
        } );
    }
}