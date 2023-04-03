package com.ttcs.shinehair.CustomsAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.LichSu;
import com.ttcs.shinehair.R;

import java.util.ArrayList;
import java.util.List;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ViewHolder> {
    private static List<String> shifts;

    public ShiftAdapter(List<String> shifts) {
        this.shifts = shifts;
    }



    private static int selectedPosition = -1;
    SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.shift_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String shift = shifts.get(position);
        holder.shiftTextView.setText(shift);

        sharedPreferences = holder.itemView.getContext().getSharedPreferences("date", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (selectedPosition == position) {
            holder.mLayout.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.lanender)));
        } else {
            holder.mLayout.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.purple_200)));
        }
    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView shiftTextView;
        public LinearLayout mLayout;
        private DatabaseHelper databaseHelper;
        private ArrayList<LichSu> data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shiftTextView = itemView.findViewById(R.id.shiftTextView);
            mLayout = itemView.findViewById( R.id.lnGio );





            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position == selectedPosition) {
                        selectedPosition = -1;
                        notifyDataSetChanged();
                        editor.clear();
                        editor.commit();
                    } else {
                        selectedPosition = position;
                        notifyDataSetChanged();
                        if (selectedPosition != -1) {
                            String gio = shifts.get(selectedPosition).toString();
                            // use gio here
                            editor.putString("gio", gio);
                            editor.putBoolean("dachongio", true);
                            editor.apply();
                            editor.commit();
                        }
                    }
                }
            });

        }
    }
}
