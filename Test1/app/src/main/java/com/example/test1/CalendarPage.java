package com.example.test1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class CalendarPage extends AppCompatActivity {
    private Button Back;
    private BottomNavigationView bottomNavigationView;
    private static TextView textDate;
    private static TextView textIncome;
    private static TextView textSpanding;
    private TextView textSize;
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
    private void init() {
        textDate = findViewById(R.id.Date);
        textIncome = findViewById(R.id.Income);
        textSpanding = findViewById(R.id.Spending);
        textSize = findViewById(R.id.Size);
        textSize.setText(String.valueOf(MemoryMeneger.GetSizeStatistic()));

        Back = findViewById(R.id.Back);
        Back.setOnClickListener(v -> switchToMainPage());

        Button Сal = findViewById(R.id.pickTime);
        Сal.setOnClickListener(v -> {
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        bottomNavigationView = findViewById(R.id.ButtonNav);
        bottomNavigationView.setSelectedItemId(R.id.button3);
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
            } else if (item.getItemId() == R.id.buttonToHome) {
                startActivity(MainActivity.MainPage);
                return true;
            } else {
                return false;
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // тут придумаємо список, куди буде вписуватись дата. потім використовувати
            // Сохраняем выбранную дату в переменные
            String selectedDate = String.format("%02d.%02d.%04d", day, month + 1, year); // Форматируем дату в строку
            // Теперь вы можете использовать эту дату, например, установить ее в TextView
            DataItemForStatistic temp = MemoryMeneger.GetStatistics(selectedDate);
            textIncome.setText(temp.getSumIncome());
            textSpanding.setText(temp.getSumSpending());
            textDate.setText(selectedDate);
        }
    }

    private void switchToMainPage() {
        startActivity(MainActivity.MainPage);
    }
}