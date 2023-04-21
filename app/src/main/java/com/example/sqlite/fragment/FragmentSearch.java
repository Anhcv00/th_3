package com.example.sqlite.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.adapter.RecycleViewAdapter;
import com.example.sqlite.database.SQLiteHelper;
import com.example.sqlite.model.Work;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private EditText eFrom, eTo;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spinner;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        eFrom = view.findViewById(R.id.eFrom);
        eTo = view.findViewById(R.id.eTo);
        btSearch = view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        spinner = view.findViewById(R.id.spCategory);
        String[] arr = getResources().getStringArray(R.array.category);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0; i < arr.length; i++){
            arr1[i+1] = arr[i];
        }
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));

        recycleViewAdapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Work> list = db.getAll();
        recycleViewAdapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recycleViewAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Work> list = db.searchByName(s);
                recycleViewAdapter.setList(list);
                return true;
            }
        });

        eFrom.setOnClickListener(this);
        eTo.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cate = spinner.getItemAtPosition(i).toString();
                List<Work> list;
                if(!cate.equalsIgnoreCase("all")){
                    list = db.searchByDesc(cate);
                } else {
                    list = db.getAll();
                }
                recycleViewAdapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == eFrom){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println(sdf.format(new Date(year, month, day)));
                    eFrom.setText(sdf.format(new Date(day, month, year)));
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == eTo){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    eTo.setText(sdf.format(new Date(day, month, year)));
                }
            }, year, month, day);
            dialog.show();
        }

        if(view == btSearch){
//            String from = eFrom.getText().toString();
//            String to = eTo.getText().toString();
//            if(!from.isEmpty() && !to.isEmpty()){
//                List<Work> list = db.searchByDateFromTo(from, to);
//                recycleViewAdapter.setList(list);
//            }
        }
    }
}
