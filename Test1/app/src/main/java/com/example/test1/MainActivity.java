package com.example.test1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;

    private RecyclerView recyclerViewForIncome;
    private RecyclerView recyclerViewForSpending;
    private MyAdapter adapterForIncome;
    private MyAdapter adapterForSpending;
    private List<DataItem> dataListIncome;
    private List<DataItem> dataListSpending;

    private ConstraintLayout incomePage;
    private ConstraintLayout spendingPage;
    private ConstraintLayout mainPage;
    private ConstraintLayout addIncomePage;
    private ConstraintLayout addSpendingPage;

    private LinearLayout panelLayout;
    private TextView SumIncome;
    private TextView SumSpending;
    private MemoryMeneger memoryMeneger;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        frameLayout = findViewById(R.id.frameLayout);
        setContentView(R.layout.activity_main);
        initMemory();
        switchToMainPage();
    }

    private void switchToMainPage() {
        // Очищаем содержимое FrameLayout перед загрузкой новой страницы
        setContentView(R.layout.main_page);
        frameLayout.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.main_page, frameLayout, true);

        initView();
        initMainPageButtons();
    }

    private void initMainPageButtons() {
        Button panelButton = findViewById(R.id.openPanelButton);
        Button goToIncomePage = findViewById(R.id.buttonToIncomePage);
        Button goToSpendingPage = findViewById(R.id.buttonToSpendingPage);

        panelButton.setOnClickListener(v -> {
            panelLayout.setVisibility(panelLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        goToIncomePage.setOnClickListener(v -> switchToIncomePage());
        goToSpendingPage.setOnClickListener(v -> switchToSpendingPage());
    }

    private void switchToIncomePage() {
        setContentView(R.layout.page_income);
        initIncomePageButtons();
    }

    private void initIncomePageButtons() {
        recyclerViewForIncome = findViewById(R.id.ListIncome);
        recyclerViewForIncome.setLayoutManager(new LinearLayoutManager(this));
        Button goToBackMainMenuFromIncomePage = findViewById(R.id.buttonTobackMainPageInIncome);
        Button goToAddIncome = findViewById(R.id.AddIncome);

        SumIncome.setText(String.valueOf(getAmountIncome()));
        goToAddIncome.setOnClickListener(v -> switchToAddIncomePage());
        goToBackMainMenuFromIncomePage.setOnClickListener(v -> switchToMainPage());
    }

    private void switchToAddIncomePage() {

        setContentView(R.layout.page_add_income);
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

            dataListIncome.add(temp);
            memoryMeneger.saveIncome(temp);

            adapterForIncome.notifyDataSetChanged(); // Обновляем адаптер
        });

        goToBackIncome.setOnClickListener(v -> switchToIncomePage());
    }

    private void switchToSpendingPage() {
        setContentView(R.layout.page_spending);
        initSpendingPageButtons();
    }

    private void initSpendingPageButtons() {
        recyclerViewForSpending = findViewById(R.id.ListSpending);
        recyclerViewForSpending.setLayoutManager(new LinearLayoutManager(this));
        Button goToBackMainMenuFromSpendingPage = findViewById(R.id.buttonToBackMainPageInSpending);
        Button goToAddSpending = findViewById(R.id.AddSpending);

        SumSpending.setText(String.valueOf(getAmountSpending()));
        goToAddSpending.setOnClickListener(v -> switchToAddSpendingPage());
        goToBackMainMenuFromSpendingPage.setOnClickListener(v -> switchToMainPage());
    }

    private void switchToAddSpendingPage() {
        setContentView(R.layout.page_add_spending);
        initAddSpendingPageButtons();
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

            dataListSpending.add(temp);
            memoryMeneger.saveSpending(temp);

            adapterForSpending.notifyDataSetChanged(); // Обновляем адаптер
        });

        goToBackSpending.setOnClickListener(v -> switchToSpendingPage());
    }

    private void initView() {
        SumIncome = findViewById(R.id.SumIncome);
        SumSpending = findViewById(R.id.SumSpending);

        panelLayout = findViewById(R.id.panel1);

        incomePage = findViewById(R.id.IncomePage);
        spendingPage = findViewById(R.id.SpendingPage);
        mainPage = findViewById(R.id.MainPage);
        addIncomePage = findViewById(R.id.PageAddIncome);
        addSpendingPage = findViewById(R.id.PageAddSpending);
    }

    private void initMemory() {
        memoryMeneger = new MemoryMeneger(this);
        calendar = Calendar.getInstance();

        dataListIncome = memoryMeneger.getIncome();
        dataListSpending = memoryMeneger.getSpending();

        if (dataListIncome == null)
            dataListIncome = new ArrayList<>();
        else
            adapterForIncome = new MyAdapter(dataListIncome, memoryMeneger, this);

        if (dataListSpending == null)
            dataListSpending = new ArrayList<>();
        else
            adapterForSpending = new MyAdapter(dataListSpending, memoryMeneger, this);

        if (adapterForIncome != null)
            recyclerViewForIncome.setAdapter(adapterForIncome);

        if (adapterForSpending != null)
            recyclerViewForSpending.setAdapter(adapterForSpending);
    }

    private int getAmountIncome() {
        int res = 0;
        if (dataListIncome != null) {
            for (DataItem item : dataListIncome) {
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

    @Override
    public void onBackPressed() {
        // Обработка нажатия кнопки "Назад" на устройстве
        if (mainPage.getVisibility() != View.VISIBLE) {
            switchToMainPage();
        } else {
            super.onBackPressed();
        }
    }
}
