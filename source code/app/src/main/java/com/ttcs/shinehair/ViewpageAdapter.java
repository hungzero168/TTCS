package com.ttcs.shinehair;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewpageAdapter extends FragmentStateAdapter {

    public ViewpageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position ==1)
            return new SignUpFragment();
        return new LoginFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
