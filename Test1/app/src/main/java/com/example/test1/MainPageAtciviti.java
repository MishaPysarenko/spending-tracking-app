package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainPageAtciviti extends AppCompatActivity {

    private LinearLayout panelLayout;
    private static TextView Income;
    private static TextView Speding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page_atciviti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        panelLayout = findViewById(R.id.panel1);
        Button panelButton = findViewById(R.id.openPanelButton);
        Button goToIncomePage = findViewById(R.id.buttonToIncomePage);
        Button goToSpendingPage = findViewById(R.id.buttonToSpendingPage);
        Button goToCalendar = findViewById(R.id.button3);

        Income = findViewById(R.id.incomeTextView);
        Speding = findViewById(R.id.expensesTextView);

        //Если тебе надо протестировать именно статический текст который ты забиндеш, коментируй эти две строки
        Income.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
        Speding.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));

        goToIncomePage.setOnClickListener(v -> {
                Intent intent = new Intent(this, IncomePage.class);
                startActivity(intent);
            }
        );

        goToSpendingPage.setOnClickListener(v -> {
                Intent intent = new Intent(this, SpendingPage.class);
                startActivity(intent);
            }
        );

        goToCalendar.setOnClickListener(v -> {
                    Intent intent = new Intent(this, CalendarPage.class);
                    startActivity(intent);
                }
        );

        panelButton.setOnClickListener(v -> {
            panelLayout.setVisibility(panelLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });
    }
    public static void updateData(){
        Income.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
        Speding.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
    }
}