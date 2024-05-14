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

        switchToMainPage();
    }
    private void switchToMainPage() {
        startActivity(MainPage);
    }
}
