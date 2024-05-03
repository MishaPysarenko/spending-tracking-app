package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddSpendingPage extends AppCompatActivity {
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_spending_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initAddSpendingPageButtons() {
        Button goToBackSpending = findViewById(R.id.CancelAddSpending);
        Button addSpending = findViewById(R.id.buttonAddSpending);
        CheckBox checkBoxSpending = findViewById(R.id.checkBoxStableSpending);
        EditText editTextSpendingAmount = findViewById(R.id.editTextAmountSpending);
        EditText editTextSpendingDescription = findViewById(R.id.editTextDescriptionSpending);

        addSpending.setOnClickListener(v -> {
            String spendingAmount = editTextSpendingAmount.getText().toString();
            String spendingDescription = editTextSpendingDescription.getText().toString();
            calendar = Calendar.getInstance();
            String date = String.valueOf(calendar.get(Calendar.DATE) + "."+ (calendar.get(Calendar.MONTH) + 1) + "."+ calendar.get(Calendar.YEAR));
            DataItem temp = new DataItem(spendingAmount, spendingDescription, date , checkBoxSpending.isChecked());

            MemoryMeneger.AddToIncomeList(temp);

            SpendingPage.updateData();//Обновляем адаптер
            MainPageAtciviti.updateData();//Обнавляем данные на главной странице
        });

        goToBackSpending.setOnClickListener(v -> {
            Intent intent = new Intent(this, SpendingPage.class);
            startActivity(intent);
        }
        );
    }
}