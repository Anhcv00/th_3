package com.example.sqlite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.UpdateDeleteActivity;
import com.example.sqlite.adapter.RecycleViewAdapter;
import com.example.sqlite.database.SQLiteHelper;
import com.example.sqlite.model.Work;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        recycleViewAdapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Work> list = db.getAll();
        recycleViewAdapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recycleViewAdapter);
        recycleViewAdapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Work work = recycleViewAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("work", work);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Work> list = db.getAll();
        recycleViewAdapter.setList(list);
    }
}
