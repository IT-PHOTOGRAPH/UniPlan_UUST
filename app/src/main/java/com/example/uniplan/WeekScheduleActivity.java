package com.example.uniplan;

import android.os.Bundle;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniplan.adapters.EventAdapter;
import com.example.uniplan.database.DatabaseHelper;
import com.example.uniplan.models.Event;

import java.util.List;

public class WeekScheduleActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_week_schedule);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_week);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.nav_home){

                startActivity(new Intent(this,
                        MainActivity.class));

                return true;

            } else if(item.getItemId() == R.id.nav_week){

                startActivity(new Intent(this,
                        WeekScheduleActivity.class));

                return true;
            }

            return true;
        });

        recyclerView = findViewById(R.id.weekRecyclerView);

        databaseHelper = new DatabaseHelper(this);

        List<Event> eventList = databaseHelper.getAllEvents();

        EventAdapter adapter = new EventAdapter(this, eventList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}