package com.ttcs.shinehair.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.CustomsAdapter.ShiftAdapter;
import com.ttcs.shinehair.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ManHinh3Fragment extends Fragment {

    ViewPager2 viewPager;
    CalendarView calendarView;
    RecyclerView recyclerView;
    String date;
    private ShiftAdapter shiftAdapter;
    static DatabaseHelper databaseHelper;
    String ngaychon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_man_hinh3, container, false );

        viewPager = getActivity().findViewById( R.id.view_pager );
        calendarView = view.findViewById( R.id.calendarView );
        recyclerView = view.findViewById( R.id.rcView );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ngay", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        // Đặt ngày tối thiểu có thể chọn là ngày hiện tại
        calendarView.setMinDate( calendar.getTimeInMillis() );
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        calendarView.setMaxDate(calendar.getTimeInMillis());

        databaseHelper =new DatabaseHelper( getActivity() );



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);
                String dayOfWeekStr = "";
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        dayOfWeekStr = "Chủ nhật";
                        break;
                    case Calendar.MONDAY:
                        dayOfWeekStr = "Thứ hai";
                        break;
                    case Calendar.TUESDAY:
                        dayOfWeekStr = "Thứ ba";
                        break;
                    case Calendar.WEDNESDAY:
                        dayOfWeekStr = "Thứ tư";
                        break;
                    case Calendar.THURSDAY:
                        dayOfWeekStr = "Thứ năm";
                        break;
                    case Calendar.FRIDAY:
                        dayOfWeekStr = "Thứ sáu";
                        break;
                    case Calendar.SATURDAY:
                        dayOfWeekStr = "Thứ bảy";
                        break;
                }
                ngaychon = dayOfMonth + "/" + (month + 1) + "/" + year;
                editor.putString("ngaychon", ngaychon);
                editor.apply();
                editor.commit();


                String start2 = databaseHelper.getDay(dayOfWeekStr).getGioBatDau();
                String end = databaseHelper.getDay(dayOfWeekStr).getGioKetThuc();
                int dayoff = databaseHelper.getDay(dayOfWeekStr).getNgayNghi();


                SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
                try {
                    Date startDate = format.parse(start2);
                    Date endDate = format.parse(end);

                    List<String> shifts = divideShifts(startDate, endDate, dayoff);
                    shiftAdapter = new ShiftAdapter(shifts);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(shiftAdapter);
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        return view;
    }

    public List<String> divideShifts(Date start, Date end, int dayOff) {
        if (dayOff == 1) {
            return Collections.emptyList();
        }
        List<String> shifts = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startHour = calendar.get(Calendar.HOUR_OF_DAY);
        int startMinute = calendar.get(Calendar.MINUTE);
        calendar.setTime(end);
        int endHour = calendar.get(Calendar.HOUR_OF_DAY);
        int endMinute = calendar.get(Calendar.MINUTE);
        int totalMinutes = (endHour * 60 + endMinute) - (startHour * 60 + startMinute);
        int numShifts = totalMinutes / 30;
        int remainingMinutes = totalMinutes % 30;
        if (remainingMinutes > 0) {
            numShifts++;
        }
        for (int i = 0; i < numShifts; i++) {
            int shiftStartHour = startHour + (i * 30 / 60);
            int shiftStartMinute = (startMinute + (i * 30)) % 60;
            shifts.add(String.format( Locale.getDefault(), "%02d:%02d", shiftStartHour, shiftStartMinute));
        }
        return shifts;
    }

}