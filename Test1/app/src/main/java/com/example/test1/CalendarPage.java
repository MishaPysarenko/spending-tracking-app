package com.example.test1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarPage extends AppCompatActivity {

    private Button Back;
    private MyAdapter adapterForIncome;
    private RecyclerView recyclerViewForStatistic;
    private TextView Income;
    private TextView Spending;
    private TextView Date;
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
        Back = findViewById(R.id.Back);
        recyclerViewForStatistic = findViewById(R.id.ListForStatistic);
        Income = findViewById(R.id.Income);
        Spending = findViewById(R.id.Spending);
        Date = findViewById(R.id.Date);
    }
}