package com.example.a20190424vukhoinycschools.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a20190424vukhoinycschools.R;
import com.example.a20190424vukhoinycschools.model.SchoolEntry;


import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<SchoolEntry> schoolEntryList;

    public CustomAdapter(List<SchoolEntry> schoolEntryList){
        this.schoolEntryList = schoolEntryList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView itemLayout = inflater
                .inflate(R.layout.item_layout, viewGroup, false)
                .findViewById(R.id.cardview);

        if (itemLayout.getParent() != null){
            ((ViewGroup)itemLayout.getParent()).removeView(itemLayout);
        }
        return new CustomViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        SchoolEntry schoolEntry = schoolEntryList.get(i);
        customViewHolder.tvName.setText(schoolEntry.getName());
        customViewHolder.tvEmail.setText(schoolEntry.getEmail());
        customViewHolder.tvMath.setText(schoolEntry.getMath());
        customViewHolder.tvReading.setText(schoolEntry.getReading());
        customViewHolder.tvWriting.setText(schoolEntry.getWriting());
        customViewHolder.tvNumTestTaker.setText(schoolEntry.getNumTestTaker());
    }

    @Override
    public int getItemCount() {
        return schoolEntryList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvEmail, tvNumTestTaker, tvMath, tvReading, tvWriting;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_school_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvNumTestTaker = itemView.findViewById(R.id.tv_num_test_taker);
            tvMath = itemView.findViewById(R.id.tv_math);
            tvReading = itemView.findViewById(R.id.tv_reading);
            tvWriting = itemView.findViewById(R.id.tv_writing);

        }
    }
}
