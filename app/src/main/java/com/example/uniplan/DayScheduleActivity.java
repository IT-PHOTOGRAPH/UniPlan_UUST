package com.example.uniplan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniplan.adapters.EventAdapter;
import com.example.uniplan.database.DatabaseHelper;
import com.example.uniplan.models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class DayScheduleActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_day_schedule);

        recyclerView = findViewById(R.id.dayRecyclerView);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        databaseHelper = new DatabaseHelper(this);

        List<Event> eventList = databaseHelper.getAllEvents();

        EventAdapter adapter = new EventAdapter(this, eventList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        bottomNavigationView.setSelectedItemId(R.id.nav_day);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                startActivity(new Intent(
                        DayScheduleActivity.this,
                        MainActivity.class));

                return true;

            } else if(item.getItemId() == R.id.nav_week){

                startActivity(new Intent(
                        DayScheduleActivity.this,
                        WeekScheduleActivity.class));

                return true;
            }

            return true;
        });
    }
}