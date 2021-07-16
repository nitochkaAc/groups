package com.example.groups;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CreateTeachActivity extends AppCompatActivity {
  Button date;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teach);

        date = (Button) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                //                SimpleDateFormat mMonth = new SimpleDateFormat("MMM");
                //                String Month_name = mMonth.format(c);

                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day


                // date picker dialog
                datePickerDialog = new DatePickerDialog(CreateTeachActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date.setText(dayOfMonth + "/"
                                        + (month + 1) + "/" + year);
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void btnOnSave(View view) {
    }

    public void btnOnDelete(View view) {
    }

    public void setDate(View view) {
//        new DatePickerDialog(CreateTeachActivity.this, (view1, year, month, dayOfMonth) -> d,
//                dateAndTime.get(Calendar.YEAR),
//                dateAndTime.get(Calendar.MONTH),
//                dateAndTime.get(Calendar.DAY_OF_MONTH))
//                .show();
    }
}