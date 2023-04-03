package com.ttcs.shinehair.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.ttcs.shinehair.CustomsAdapter.ChonAdapter;
import com.ttcs.shinehair.R;

public class DatLichActivity extends AppCompatActivity {
    private Button btnNext, btnPrev;
    private ChonAdapter adapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);

        viewPager = findViewById(R.id.view_pager);
        adapter = new ChonAdapter( this );
        viewPager.setAdapter( adapter);

        viewPager.setUserInputEnabled(false);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to next page
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1, true);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to previous page
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem - 1, true);
            }
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    btnPrev.setVisibility(View.GONE);

                } else {
                    btnPrev.setVisibility(View.VISIBLE);
                }

                if (position == adapter.getItemCount() - 1) {
                    btnNext.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                }
                btnPrev.invalidate();
                btnNext.invalidate();
            }
        });
    }
}