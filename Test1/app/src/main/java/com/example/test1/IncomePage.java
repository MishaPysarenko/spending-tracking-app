package com.example.test1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomePage extends AppCompatActivity {
    private Calendar calendar;
    private static TextView SumIncome;
    private RecyclerView recyclerViewForIncome;
    private static MyAdapter adapterForIncome;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_income_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SumIncome = findViewById(R.id.SumIncome);
        Button goToBackMainMenuFromIncomePage = findViewById(R.id.buttonTobackMainPageInIncome);
        Button goToAddIncome = findViewById(R.id.AddIncome);

        calendar = Calendar.getInstance();

        recyclerViewForIncome = findViewById(R.id.ListIncome);
        recyclerViewForIncome.setLayoutManager(new LinearLayoutManager(this));

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

        adapterForIncome = new MyAdapter(MemoryMeneger.GetListIncome(), this);

        recyclerViewForIncome.setAdapter(adapterForIncome);

        goToAddIncome.setOnClickListener(v -> switchToAddIncomePage());
        goToBackMainMenuFromIncomePage.setOnClickListener(v -> switchToMainPage());
        SumIncome.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
    }
    private void switchToMainPage() {
        startActivity(MainActivity.MainPage);
    }
    private void switchToAddIncomePage()
    {
        startActivity(MainActivity.AddIncomePage);
    }
    public static void updateData(){
        //adapterForIncome.notifyDataSetChanged(); // Обновляем адаптер
        SumIncome.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
    }
}