package com.example.test1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarPage extends AppCompatActivity {

    private Button Back;
    private static MyAdapterForStatistic adapterForStatictic;
    private RecyclerView recyclerViewForStatistic;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();


    }

    private void init()
    {
        adapterForStatictic = new MyAdapterForStatistic(MemoryMeneger.GetListStatistics(),this);

        Back = findViewById(R.id.Back);
        Back.setOnClickListener(v -> switchToMainPage());

        recyclerViewForStatistic = findViewById(R.id.ListForStatistic);
        recyclerViewForStatistic.setAdapter(adapterForStatictic);

        bottomNavigationView = findViewById(R.id.ButtonNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.button3) {
                startActivity(MainActivity.CalendarPage);
                return true;
            } else if (item.getItemId() == R.id.buttonToIncomePage) {
                startActivity(MainActivity.IncomePage);
                return true;
            } else if (item.getItemId() == R.id.buttonToSpendingPage) {
                startActivity(MainActivity.SpendingPage);
                return true;
            } else {
                return false;
            }
        });
    }
    public static void updateData()
    {
        adapterForStatictic.notifyDataSetChanged();;
    }
    private void switchToMainPage() {
        startActivity(MainActivity.MainPage);
    }
}