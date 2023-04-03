package com.ttcs.shinehair.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.R;

public class StaffHomeActivity extends AppCompatActivity {
    private Button btnQuanLylich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        btnQuanLylich = findViewById(R.id.btnQuanLylich);

        btnQuanLylich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffHomeActivity.this, KiemTraLichActivity.class) );
            }
        } );
    }
}