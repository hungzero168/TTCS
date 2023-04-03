package com.ttcs.shinehair.admin.activity;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.Users;
import com.ttcs.shinehair.CustomsAdapter.TaiKhoanAdaptor;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class TKStaffActivity extends AppCompatActivity {
    RecyclerView rcView;
    DatabaseHelper databaseHelper;
    ArrayList<Users> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tkstaff );
        rcView = findViewById( R.id.rcView );


        databaseHelper = new DatabaseHelper(TKStaffActivity.this);
        Cursor cursor = databaseHelper.getData("select * from users");
        data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int userType = cursor.getInt(2);
            String email = cursor.getString(3);
            String name = databaseHelper.getName(email);
            data.add( new Users(id, email, userType, name) );
        }
        TaiKhoanAdaptor adaptorTK = new TaiKhoanAdaptor( this, data ,"quyen");
        rcView.setAdapter(adaptorTK);
        rcView.setLayoutManager(new LinearLayoutManager( this ) );
        adaptorTK.notifyDataSetChanged();

    }
}