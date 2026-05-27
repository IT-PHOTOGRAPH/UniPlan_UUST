package com.example.uniplan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uniplan.database.DatabaseHelper;
import com.example.uniplan.models.Event;

public class AddEventActivity extends AppCompatActivity {

    EditText etTitle, etDescription, etDate, etTime;
    Button btnSave;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_event);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etDate.setFocusable(false);
        etTime.setFocusable(false);
        etDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(this,
                            (view, selectedYear, selectedMonth, selectedDay) -> {

                                String date = selectedDay + "/" +
                                        (selectedMonth + 1) + "/" +
                                        selectedYear;

                                etDate.setText(date);

                            }, year, month, day);

            datePickerDialog.show();

        });

        etTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(this,
                            (view, selectedHour, selectedMinute) -> {

                                String time = String.format(
                                        "%02d:%02d",
                                        selectedHour,
                                        selectedMinute
                                );

                                etTime.setText(time);

                            }, hour, minute, true);

            timePickerDialog.show();

        });

        btnSave = findViewById(R.id.btnSave);

        databaseHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {

            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String time = etTime.getText().toString().trim();

            if (title.isEmpty()) {

                etTitle.setError("Enter title");

                return;
            }

            Event event = new Event(
                    0,
                    title,
                    description,
                    date,
                    time
            );

            databaseHelper.addEvent(event);

            Toast.makeText(this,
                    "Event saved",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}