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

public class XoaAccActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Users> data;
    TaiKhoanAdaptor adaptor;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_xoa_acc );
        recyclerView = findViewById( R.id.rcView );

        data =new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getData("select * from users");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int userType = cursor.getInt(2);
            String email = cursor.getString(3);
            String name = databaseHelper.getName(email);
            data.add( new Users(id, email, userType, name) );
        }
        adaptor = new TaiKhoanAdaptor(this, data, "xoa");
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        adaptor.notifyDataSetChanged();

    }
}