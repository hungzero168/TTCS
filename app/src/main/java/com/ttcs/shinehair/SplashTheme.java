package com.ttcs.shinehair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ttcs.shinehair.Class.DatabaseHelper;

public class SplashTheme extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    @Override
    protected void onStart() {
        super.onStart();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent intent = new Intent(SplashTheme.this, MainActivity.class);
                // Intent intent = new Intent(SplashTheme.this, MainActivity2.class);
                // startActivity(intent);
                // finish();



                SharedPreferences sharedPreferences = getSharedPreferences( LoginFragment.PERFS_NAME, MODE_PRIVATE );
                boolean hasLog = sharedPreferences.getBoolean( "dangnhap", false );
                if (hasLog) {
                    Intent intent = new Intent(SplashTheme.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashTheme.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

}
