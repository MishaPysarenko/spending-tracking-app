package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpendingPage extends AppCompatActivity {
    private static TextView SumSpending;
    private RecyclerView recyclerViewForSpending;
    private static MyAdapter adapterForSpending;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spending_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewForSpending = findViewById(R.id.ListSpending);
        recyclerViewForSpending.setLayoutManager(new LinearLayoutManager(this));
        adapterForSpending = new MyAdapter(MemoryMeneger.GetListSpending(), this);
        recyclerViewForSpending.setAdapter(adapterForSpending);

        SumSpending = findViewById(R.id.SumSpending);
        SumSpending.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));

        bottomNavigationView = findViewById(R.id.ButtonNav);
        bottomNavigationView.setSelectedItemId(R.id.buttonToSpendingPage);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.button3) {
                startActivity(MainActivity.CalendarPage);
                return true;
            } else if (item.getItemId() == R.id.buttonToIncomePage) {
                startActivity(MainActivity.IncomePage);
                return true;
            } else if (item.getItemId() == R.id.buttonToSpendingPage) {
                startActivity(MainActivity.AddSpendingPage);
                return true;
            } else if (item.getItemId() == R.id.buttonToHome) {
                startActivity(MainActivity.MainPage);
                return true;
            } else {
                return false;
            }
        });
    }
    public static void updateData(){
        adapterForSpending.notifyDataSetChanged(); // Обновляем адаптер
        SumSpending.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
    }
}