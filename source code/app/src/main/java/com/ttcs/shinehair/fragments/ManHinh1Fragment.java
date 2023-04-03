package com.ttcs.shinehair.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.DichVu;
import com.ttcs.shinehair.CustomsAdapter.DichVuAdapter;
import com.ttcs.shinehair.R;

import java.util.ArrayList;


public class ManHinh1Fragment extends Fragment {
//    ViewPager viewPager;
    ViewPager2 viewPager;
    RecyclerView rcView;
    private ArrayList<DichVu> data;
    DatabaseHelper mDatabaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_man_hinh1, container, false );
        viewPager = getActivity().findViewById( R.id.view_pager );
        rcView = view.findViewById( R.id.rcView );

        data = new ArrayList<>();
        mDatabaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = mDatabaseHelper.getData("SELECT * FROM dichvu");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenDichVu = cursor.getString(1);
            String giaDichVu = cursor.getString(2);
            String chiTiet = cursor.getString(3);
            byte[] hinhanh = cursor.getBlob(4);
            data.add(new DichVu(id, tenDichVu, giaDichVu, chiTiet, hinhanh));
        }

        DichVuAdapter adapter = new DichVuAdapter(getActivity(), data, "chon");
        GridLayoutManager gridLayoutManager = new GridLayoutManager( getActivity(), 2 );
        rcView.setLayoutManager( gridLayoutManager );
        rcView.setAdapter(adapter);


        return view;
    }
}