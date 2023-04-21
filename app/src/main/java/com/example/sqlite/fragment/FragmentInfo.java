package com.example.sqlite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.List;

public class FragmentInfo extends Fragment {
    private ImageView imageView;
    private TextView tvName, tvMsv, tvCn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=view.findViewById(R.id.imageView);
        tvName = view.findViewById(R.id.tvName);
        tvMsv = view.findViewById(R.id.tvMsv);
        tvCn = view.findViewById(R.id.tvCn);
    }

}
