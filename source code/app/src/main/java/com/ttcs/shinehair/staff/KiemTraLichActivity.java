package com.ttcs.shinehair.staff;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.CustomsAdapter.LichSuAdaptor;
import com.ttcs.shinehair.R;

import java.util.ArrayList;
import java.util.Calendar;

public class KiemTraLichActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private RecyclerView rcView;
    private ArrayList<LichSu> data;
    private String date;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_kiem_tra_lich );
        calendarView = findViewById(R.id.calendarView);
        rcView = findViewById(R.id.rcView);

        data = new ArrayList<>();
        databaseHelper = new DatabaseHelper(KiemTraLichActivity.this);
        sharedPreferences = getSharedPreferences("dangnhap", MODE_PRIVATE);
        String email = sharedPreferences.getString( "username", null );
        String get = databaseHelper.getIdUser(email);
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int moth, int dayOfMoth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, moth, dayOfMoth);

                date = dayOfMoth + "/" + (moth + 1) + "/" + year;
                // truy câp vào database lấy dữ liệu
                Cursor cursor1 = databaseHelper.getData("SELECT * FROM datlich WHERE idnhanvien = '" + get + "' AND ngaydat = '" + date + "' and trangthai = 1");
                data.clear();
                while (cursor1.moveToNext()) {
                    int id = cursor1.getInt(0);
                    int idtendichvu = cursor1.getInt(4);
                    String tendichvu = databaseHelper.getTenDichVu(idtendichvu);
                    String ngaydat = cursor1.getString(1);
                    String giodat = cursor1.getString(2);
                    String idgiadichvu = cursor1.getString(4);
                    String gia = databaseHelper.getGiaDichVu(idgiadichvu)+"k";
                    data.add( new LichSu(id, tendichvu, ngaydat, giodat, gia));
                }
                LichSuAdaptor adapter = new LichSuAdaptor(KiemTraLichActivity.this, data, "xacnhan");
                rcView.setAdapter(adapter);
                rcView.setLayoutManager(new LinearLayoutManager(KiemTraLichActivity.this));
                adapter.notifyDataSetChanged();

            }
        } );


    }
}