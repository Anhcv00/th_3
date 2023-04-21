package com.example.sqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.model.Work;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{

    private List<Work> list;

    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(List<Work> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Work getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Work work = list.get(position);
        holder.name.setText(work.getName());
        holder.desc.setText(work.getDesc());
        holder.status.setText(work.getStatus());
        holder.dateline.setText(work.getDateline());
        holder.collab.setText(work.getCollab());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, desc, status, dateline, collab;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            desc = itemView.findViewById(R.id.tvDesc);
            status = itemView.findViewById(R.id.tvStatus);
            dateline = itemView.findViewById(R.id.tvDateline);
            collab = itemView.findViewById(R.id.tvCollab);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}
