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
    private MemoryMeneger memoryMeneger;
    private TextView SumSpending;
    private List<DataItem> dataListSpending;
    private RecyclerView recyclerViewForSpending;
    private MyAdapter adapterForSpending;

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
        memoryMeneger = new MemoryMeneger(this);
        calendar = Calendar.getInstance();

        recyclerViewForSpending = findViewById(R.id.ListSpending);
        recyclerViewForSpending.setLayoutManager(new LinearLayoutManager(this));

        dataListSpending = memoryMeneger.getSpending();

        if (dataListSpending == null)
            dataListSpending = new ArrayList<>();
        else
            adapterForSpending = new MyAdapter(dataListSpending, memoryMeneger, this);

        if (adapterForSpending != null)
            recyclerViewForSpending.setAdapter(adapterForSpending);



        Button goToBackMainMenuFromSpendingPage = findViewById(R.id.buttonToBackMainPageInSpending);
        Button goToAddSpending = findViewById(R.id.AddSpending);
        SumSpending = findViewById(R.id.SumSpending);

        SumSpending.setText(String.valueOf(getAmountSpending()));
        goToAddSpending.setOnClickListener(v -> switchToAddSpendingPage());
        goToBackMainMenuFromSpendingPage.setOnClickListener(v -> switchToMainPage());
    }
    private void switchToAddSpendingPage() {
        Intent intent = new Intent(this, AddSpendingPage.class);
        startActivity(intent);
    }
    private void switchToMainPage() {
        Intent intent = new Intent(this, MainPageAtciviti.class);
        startActivity(intent);
    }
    private int getAmountSpending() {
        int res = 0;
        if (dataListSpending != null) {
            for (DataItem item : dataListSpending) {
                if (item != null) {
                    String sum = item.getSum();
                    if (sum != null && !sum.isEmpty()) {
                        try {
                            int amount = Integer.parseInt(sum);
                            res += amount;
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // Логирование ошибки
                        }
                    }
                }
            }
        }
        return res;
    }
}