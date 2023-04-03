package com.ttcs.shinehair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ttcs.shinehair.Activity.DatLichActivity;
import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.fragments.AccountFragment;
import com.ttcs.shinehair.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    FloatingActionButton btnDat;
    DatabaseHelper mDatabaseHelper;


    HomeFragment homeFragment = new HomeFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNav);
        btnDat = findViewById(R.id.btn_dat_lich);


//        mDatabaseHelper = new DatabaseHelper(MainActivity.this);
//        mDbHelper.deleteAll();

//        mDatabaseHelper.insertUsers(new Users("admin", "admin", 1));
//        mDatabaseHelper.insertUsers(new Users("staff", "staff", 2));
//        mDatabaseHelper.insertUsers(new Users("staff2", "staff", 2));
//        mDatabaseHelper.insertUsers(new Users("staff3", "staff", 2));
//        mDatabaseHelper.insertUsers(new Users("staff4", "staff", 2));




        bottomNavigationView.setBackground(null);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
//        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.account);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                case R.id.account:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                    return true;
            }
            return false;
        });

        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyển main fragment sang activity dat lich
                startActivity(new Intent(MainActivity.this, DatLichActivity.class));

            }
        });
    }
}
