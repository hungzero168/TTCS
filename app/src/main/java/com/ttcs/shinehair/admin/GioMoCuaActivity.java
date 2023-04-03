package com.ttcs.shinehair.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.R;

public class GioMoCuaActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Spinner spinner;
    EditText edtgiomocua, edtgiodongcua;
    CheckBox cbdongcua;
    Button btncapnhap;
    String selectedDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_mo_cua );
        spinner = findViewById( R.id.spinnerOpenTime );
        edtgiomocua = findViewById( R.id.edtgiomocua );
        edtgiodongcua = findViewById( R.id.edtgiodongcua );
        cbdongcua = findViewById( R.id.cbngaynghi );
        btncapnhap = findViewById( R.id.btncapnhat );

        String[] daysOfWeek = {"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daysOfWeek);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = (String) parent.getItemAtPosition(position);
                // Do something with the selected day
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText( GioMoCuaActivity.this, "Chọn ngày", Toast.LENGTH_SHORT ).show();
            }
        });


        databaseHelper = new DatabaseHelper( GioMoCuaActivity.this );
        btncapnhap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String giomo = edtgiomocua.getText().toString();
                String giodong = edtgiodongcua.getText().toString();
                int ngaynghi;
                if (cbdongcua.isChecked()) {
                    ngaynghi = 1;
                } else {
                    ngaynghi = 0;
                }
                if(databaseHelper.updateGioMo(selectedDay,giomo,giodong,ngaynghi)) {
                    Toast.makeText( GioMoCuaActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {
                    Toast.makeText( GioMoCuaActivity.this, "Cập nhật bất bại", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
}