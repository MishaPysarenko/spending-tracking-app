package com.example.test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static Intent MainPage;
    public static Intent SpendingPage;
    public static Intent IncomePage;
    public static Intent AddSpendingPage;
    public static Intent AddIncomePage;
    public static Intent CalendarPage;
    private SharedPreferences lastDateUpdate;
    private SharedPreferences.Editor lastDateUpdateEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        MemoryMeneger.init(this);
        MemoryMeneger.AddDebugDATA(); //Коменти эту стору чтобы убрать данные для отладки

        lastDateUpdate = getSharedPreferences("Income", Context.MODE_PRIVATE);
        lastDateUpdateEditor = lastDateUpdate.edit();

        MainPage = new Intent(this, MainPageAtciviti.class);
        SpendingPage = new Intent(this, SpendingPage.class);
        IncomePage = new Intent(this, IncomePage.class);
        AddSpendingPage = new Intent(this, AddSpendingPage.class);
        AddIncomePage = new Intent(this, AddIncomePage.class);
        CalendarPage = new Intent(this, CalendarPage.class);

        update();
        switchToMainPage();
    }
    private void switchToMainPage() {
        startActivity(MainPage);
    }

    private void update() {
        // Получаем предыдущую дату обновления из SharedPreferences
        long previousUpdateMillis = lastDateUpdate.getLong("lastUpdateMillis", 0);
        Calendar previousUpdateCalendar = Calendar.getInstance();
        previousUpdateCalendar.setTimeInMillis(previousUpdateMillis);

        // Получаем текущую дату
        Calendar currentCalendar = Calendar.getInstance();

        // Проверяем, изменилась ли дата с предыдущего обновления
        if (currentCalendar.get(Calendar.DAY_OF_YEAR) != previousUpdateCalendar.get(Calendar.DAY_OF_YEAR) ||
                currentCalendar.get(Calendar.YEAR) != previousUpdateCalendar.get(Calendar.YEAR)) {
            // Дата изменилась, выполняем другой алгоритм обновления
            // Здесь можно добавить код для обновления данных
            // Например, загрузка новых данных из сети или базы данных
            // После обновления данных нужно сохранить текущую дату обновления в SharedPreferences

            int tempIncome = MemoryMeneger.GetAmountIncome();
            int tempSpending = MemoryMeneger.GetAmountSpending();

            MemoryMeneger.AddToStatisticsList(new DataItem(String.valueOf(tempIncome),String.valueOf(tempSpending),
                    String.valueOf(currentCalendar.get(Calendar.DATE) + "."+ (currentCalendar.get(Calendar.MONTH) + 1) + "."+ currentCalendar.get(Calendar.YEAR)),
                    false));

            lastDateUpdateEditor.putLong("lastUpdateMillis", currentCalendar.getTimeInMillis()).apply();
            MemoryMeneger.deleteFalseItems();
        }
    }

}
