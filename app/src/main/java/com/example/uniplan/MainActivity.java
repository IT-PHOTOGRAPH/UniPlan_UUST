package com.example.uniplan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniplan.adapters.EventAdapter;
import com.example.uniplan.database.DatabaseHelper;
import com.example.uniplan.models.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAdd;

    Button btnDay, btnWeek;

    DatabaseHelper databaseHelper;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                return true;

            } else if(item.getItemId() == R.id.nav_day){

                startActivity(new Intent(MainActivity.this,
                        DayScheduleActivity.class));

                return true;

            } else if(item.getItemId() == R.id.nav_week){

                startActivity(new Intent(MainActivity.this,
                        WeekScheduleActivity.class));

                return true;
            }

            return false;
        });

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        btnDay = findViewById(R.id.btnDay);
        btnWeek = findViewById(R.id.btnWeek);

        databaseHelper = new DatabaseHelper(this);

        loadEvents();

        btnAdd.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this,
                    AddEventActivity.class));

        });

        btnDay.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this,
                    WeekScheduleActivity.class));

        });

        btnWeek.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this,
                    WeekScheduleActivity.class));

        });
    }

    private void loadEvents() {

        List<Event> eventList = databaseHelper.getAllEvents();

        EventAdapter adapter = new EventAdapter(this, eventList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEvents();
    }
}