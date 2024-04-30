package com.example.test1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        switchToMainPage();

    }
    private void switchToMainPage() {
        Intent intent = new Intent(this, MainPageAtciviti.class);
        startActivity(intent);
    }
}
