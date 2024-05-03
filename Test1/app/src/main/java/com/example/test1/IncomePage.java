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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomePage extends AppCompatActivity {
    private Calendar calendar;
    private static TextView SumIncome;
    private RecyclerView recyclerViewForIncome;
    private static MyAdapter adapterForIncome;

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

        adapterForIncome = new MyAdapter(MemoryMeneger.GetListIncome(), this);

        recyclerViewForIncome.setAdapter(adapterForIncome);

        goToAddIncome.setOnClickListener(v -> switchToAddIncomePage());
        goToBackMainMenuFromIncomePage.setOnClickListener(v -> switchToMainPage());
        SumIncome.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
    }
    private void switchToMainPage() {
        Intent intent = new Intent(this, MainPageAtciviti.class);
        startActivity(intent);
    }
    private void switchToAddIncomePage()
    {
        Intent intent = new Intent(this, AddIncomePage.class);
        startActivity(intent);
    }
    public static void updateData(){
        adapterForIncome.notifyDataSetChanged(); // Обновляем адаптер
        SumIncome.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
    }
}