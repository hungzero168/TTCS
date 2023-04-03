package com.ttcs.shinehair.CustomsAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ttcs.shinehair.fragments.ManHinh1Fragment;
import com.ttcs.shinehair.fragments.ManHinh2Fragment;
import com.ttcs.shinehair.fragments.ManHinh3Fragment;
import com.ttcs.shinehair.fragments.ManHinh4Fragment;

public class ChonAdapter extends FragmentStateAdapter {
    public ChonAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ManHinh1Fragment();
            case 1:
                return new ManHinh2Fragment();
            case 2:
                return new ManHinh3Fragment();
            case 3:
                return new ManHinh4Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
