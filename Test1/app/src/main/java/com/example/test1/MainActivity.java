package com.example.test1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static Intent MainPage;
    public static Intent SpendingPage;
    public static Intent IncomePage;
    public static Intent AddSpendingPage;
    public static Intent AddIncomePage;
    public static Intent CalendarPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        MemoryMeneger.init(this);
        MemoryMeneger.AddDebugDATA(); //Коменти эту стору чтобы убрать данные для отладки

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
