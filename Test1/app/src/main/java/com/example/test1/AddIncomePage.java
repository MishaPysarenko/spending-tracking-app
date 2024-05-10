package com.example.test1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AddIncomePage extends AppCompatActivity {

    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_income_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initAddIncomePageButtons();

    }

    private void initAddIncomePageButtons() {
        Button goToBackIncome = findViewById(R.id.CancelAddIncome);
        Button addIncome = findViewById(R.id.buttonAddIncome);
        CheckBox checkBoxIncome = findViewById(R.id.checkBoxStableIncome);
        EditText editTextIncomeAmount = findViewById(R.id.editTextAmount);
        EditText editTextIncomeDescription = findViewById(R.id.editTextDescription);

        addIncome.setOnClickListener(v -> {
            String incomeAmount = editTextIncomeAmount.getText().toString();
            String incomeDescription = editTextIncomeDescription.getText().toString();
            calendar = Calendar.getInstance();
            String date = String.valueOf(calendar.get(Calendar.DATE) + "."+ (calendar.get(Calendar.MONTH) + 1) + "."+ calendar.get(Calendar.YEAR));
            DataItem temp = new DataItem(incomeAmount, incomeDescription, date, checkBoxIncome.isChecked());

            MemoryMeneger.AddToIncomeList(temp);

            IncomePage.updateData();//Обновляем адаптер
            MainPageAtciviti.updateData(); //Обнавляем данные на главной странице
        });

        goToBackIncome.setOnClickListener(v -> {
            startActivity(MainActivity.IncomePage);
        });
    }
}