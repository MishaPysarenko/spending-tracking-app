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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpendingPage extends AppCompatActivity {
    private Calendar calendar;
    private static TextView SumSpending;
    private RecyclerView recyclerViewForSpending;
    private static MyAdapter adapterForSpending;

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

        Button goToBackMainMenuFromSpendingPage = findViewById(R.id.buttonToBackMainPageInSpending);
        Button goToAddSpending = findViewById(R.id.AddSpending);
        SumSpending = findViewById(R.id.SumSpending);

        SumSpending.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
        goToAddSpending.setOnClickListener(v -> switchToAddSpendingPage());
        goToBackMainMenuFromSpendingPage.setOnClickListener(v -> switchToMainPage());
    }
    private void switchToAddSpendingPage() {
        startActivity(MainActivity.AddSpendingPage);
    }
    private void switchToMainPage() {
        startActivity(MainActivity.MainPage);
    }
    public static void updateData(){
        adapterForSpending.notifyDataSetChanged(); // Обновляем адаптер
        SumSpending.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
    }
}