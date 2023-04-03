package com.ttcs.shinehair.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.activity.XemLichDatActivity;
import com.ttcs.shinehair.admin.activity.XoaLichDatActivity;

public class QuanLyDatLichActivity extends AppCompatActivity {
    private Button btnquanlygiomocua;
    private Button btnXemDatLich;
    private Button btnXoaDatLich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quan_ly_dat_lich );
        btnquanlygiomocua = findViewById( R.id.btnquanlygiomocua );
        btnXemDatLich = findViewById( R.id.btnXemDatLich );
        btnXoaDatLich =findViewById( R.id.btnXoaDatLich );

        btnquanlygiomocua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyDatLichActivity.this, GioMoCuaActivity.class) );
            }
        } );

        btnXemDatLich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyDatLichActivity.this, XemLichDatActivity.class ) );
            }
        } );


        btnXoaDatLich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(QuanLyDatLichActivity.this, XoaLichDatActivity.class ) );
            }
        } );
    }
}