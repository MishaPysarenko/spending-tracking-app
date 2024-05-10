package com.example.test1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuInflater;
import android.view.Menu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.logging.Logger;

 


public class MainPageAtciviti extends AppCompatActivity {
    private static final Logger LOGGER = Logger.getLogger(MainPageAtciviti.class.getName());
    private static TextView Income;
    private static TextView Speding;
    private BottomNavigationView bottomNavigationView;
    private Button Calendar;
    private Button Add;
    private Button Remove;

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

        Income = findViewById(R.id.incomeTextView);
        Speding = findViewById(R.id.expensesTextView);

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

        //Если тебе надо протестировать именно статический текст который ты забиндеш, коментируй эти две строки
        Income.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
        Speding.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
    }
    public static void updateData(){
        Income.setText(String.valueOf(MemoryMeneger.GetAmountIncome()));
        Speding.setText(String.valueOf(MemoryMeneger.GetAmountSpending()));
    }
}