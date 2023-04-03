package com.ttcs.shinehair.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.Users;
import com.ttcs.shinehair.CustomsAdapter.StaffAdapter;
import com.ttcs.shinehair.R;

import java.util.ArrayList;

public class ManHinh2Fragment extends Fragment {

    ViewPager2 viewPager;
    RecyclerView recyclerView;
    ArrayList<Users> dataUser;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_man_hinh2, container, false );

        viewPager = getActivity().findViewById( R.id.view_pager );
        recyclerView = view.findViewById( R.id.rcView );

        dataUser = new ArrayList<>();
        databaseHelper = new DatabaseHelper( getActivity() );
        ArrayList<Users> staffUsers = databaseHelper.getStaffUsers();
        StaffAdapter staffAdapter = new StaffAdapter(getActivity(), staffUsers);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(staffAdapter);



        return view;
    }
}