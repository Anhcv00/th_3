package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sqlite.database.SQLiteHelper;
import com.example.sqlite.model.Work;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spStatus;
    private EditText eName, eDesc, eDateline;
    private RadioButton eCollab;
    private Button btAdd, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();
        spStatus = findViewById(R.id.spStatus);
        eName = findViewById(R.id.eName);
        eDesc = findViewById(R.id.eDesc);
        eDateline = findViewById(R.id.eDateline);
        eCollab = findViewById(R.id.eCollab);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        spStatus.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        eDateline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == eDateline){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    eDateline.setText(sdf.format(new Date(year, month, day)));
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == btAdd){
            String name = eName.getText().toString();
            String desc = eDesc.getText().toString();
            String dateline = eDateline.getText().toString();
            String status = spStatus.getSelectedItem().toString();
            boolean collab = eCollab.isChecked();
            if(!name.isEmpty()){
                Work work = new Work(name, desc, dateline, status, collab ? "Một mình" : "Cộng tác");
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(work);
                finish();
            }
        }
        if(view == btCancel){
            finish();
        }
    }
}