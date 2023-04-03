package com.ttcs.shinehair.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.R;

public class QuanLyDichVuActivity extends AppCompatActivity {
    Button btnThemDichVu,btndelDV, btnCapnhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quan_ly_dich_vu );
        btndelDV = findViewById( R.id.btndelDV );
        btnThemDichVu = findViewById(R.id.btnThemDichVu);
        btnCapnhat = findViewById( R.id.btn_Cap_nhat );

        btnThemDichVu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( QuanLyDichVuActivity.this, ThemDichVuActivity.class);
                startActivity(intent);
            }
        });

        btnCapnhat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyDichVuActivity.this, SuaDichVuActivity.class) );
            }
        } );

        btndelDV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyDichVuActivity.this, XoaDichVuActivity.class ) );
            }
        } );
    }
}