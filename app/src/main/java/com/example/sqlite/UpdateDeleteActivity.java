package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spStatus;
    private EditText eName, eDesc, eDateline;
    private RadioButton eCollab;
    private Button btEdit, btDelete, btBack;

    private Work work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        getSupportActionBar().hide();
        spStatus = findViewById(R.id.spStatus);
        eName = findViewById(R.id.eName);
        eDesc = findViewById(R.id.eDesc);
        eDateline = findViewById(R.id.eDateline);
        eCollab = findViewById(R.id.eCollab);
        btEdit = findViewById(R.id.btEdit);
        btDelete = findViewById(R.id.btDelete);
        btBack = findViewById(R.id.btBack);
        spStatus.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));
        btEdit.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btBack.setOnClickListener(this);
        eDateline.setOnClickListener(this);

        Intent intent = getIntent();
        work = (Work) intent.getSerializableExtra("work");
        eName.setText(work.getName());
        eDesc.setText(work.getDesc());
        eDateline.setText(work.getDateline());
        eCollab.setChecked(work.getCollab().equalsIgnoreCase("Cộng tác"));
        int p = 0;
        for (int i = 0; i < spStatus.getCount(); i++){
            if(spStatus.getItemAtPosition(i).toString().equalsIgnoreCase(work.getDesc())){
                p = i;
                break;
            }
        }
        spStatus.setSelection(p);

    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(view == eDateline){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    eDateline.setText(sdf.format(new Date(year, month, day)));
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == btBack){
            finish();
        }

        if(view == btEdit){
            String name = eName.getText().toString();
            String desc = eDesc.getText().toString();
            String dateline = eDateline.getText().toString();
            String status = spStatus.getSelectedItem().toString();
            boolean collab = eCollab.isActivated();
            if(!name.isEmpty() && !desc.isEmpty()){
                Work i = new Work(work.getId(), name, desc, dateline, status, collab ? "Một mình" : "Cộng tác");
                db.update(i);
                finish();
            }
        }
        if(view == btDelete){
            db.delete(work.getId());
            finish();
        }
    }
}