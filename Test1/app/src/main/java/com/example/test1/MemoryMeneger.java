package com.example.test1;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryMeneger {
    //хуйня для сохранения в память телефона, разбита сразу на доходы и траты
    private SharedPreferences IncomeData;
    private SharedPreferences.Editor editorIncomeData;
    private SharedPreferences SpendingData;
    private SharedPreferences.Editor editorSpendingData;

    private SharedPreferences SpendingStatistics;
    private SharedPreferences.Editor editorSpendingStatistics;
    private SharedPreferences StatisticsIncome;
    private SharedPreferences.Editor editorStatisticsIncome;
    private Context mContext; // Добавляем поле для хранения контекста

    public MemoryMeneger(Context context) {
        this.mContext = context; // Сохраняем переданный контекст
        init(); // Вызываем метод инициализации
    }
    public void init() {
        // Получаем объекты SharedPreferences с помощью контекста
        IncomeData = mContext.getSharedPreferences("Income", Context.MODE_PRIVATE);
        editorIncomeData = IncomeData.edit();

        SpendingData = mContext.getSharedPreferences("Spending", Context.MODE_PRIVATE);
        editorSpendingData = SpendingData.edit();

        StatisticsIncome = mContext.getSharedPreferences("IncomeStatistics", Context.MODE_PRIVATE);
        editorStatisticsIncome = StatisticsIncome.edit();

        SpendingStatistics = mContext.getSharedPreferences("SpendingStatistics", Context.MODE_PRIVATE);
        editorSpendingStatistics = SpendingStatistics.edit();
    }
    public void saveIncome(DataItem income) {
        String name = income.getName();
        editorIncomeData.putInt(name + "_sum", Integer.parseInt(income.getSum()));
        editorIncomeData.putBoolean(name + "_isStable", income.getIsStable());
        editorIncomeData.putString(name + "_date", income.getDate());
        editorIncomeData.apply();
    }
    public void saveSpending(DataItem spending) {
        String name = spending.getName();
        editorSpendingData.putInt(name + "_sum", Integer.parseInt(spending.getSum()));
        editorSpendingData.putBoolean(name + "_isStable", spending.getIsStable());
        editorSpendingData.putString(name + "_date", spending.getDate());
        editorSpendingData.apply();
    }
    public List<DataItem> getSpending() {
        List<DataItem> spendingList = new ArrayList<>();
        Map<String, ?> spendingEntries = SpendingData.getAll();
        for (Map.Entry<String, ?> entry : spendingEntries.entrySet()) {
            String name = entry.getKey();
            if (name.endsWith("_sum")) {
                int sum = SpendingData.getInt(name, 0); // Значение по умолчанию 0
                String itemName = name.replace("_sum", "");
                boolean isStable = SpendingData.getBoolean(itemName + "_isStable", false);
                String date = SpendingData.getString(itemName + "_date", "");
                spendingList.add(new DataItem(String.valueOf(sum), itemName, date, isStable));
            }
        }
        return spendingList;
    }
    public List<DataItem> getIncome() {
        List<DataItem> incomeList = new ArrayList<>();
        Map<String, ?> incomeEntries = IncomeData.getAll();
        for (Map.Entry<String, ?> entry : incomeEntries.entrySet()) {
            String name = entry.getKey();
            if (name.endsWith("_sum")) {
                String itemName = name.replace("_sum", "");
                int sum = IncomeData.getInt(name, 0); // Значение по умолчанию 0
                boolean isStable = IncomeData.getBoolean(itemName + "_isStable", false);
                String date = IncomeData.getString(itemName + "_date", "");
                incomeList.add(new DataItem(String.valueOf(sum), itemName, date, isStable));
            }
        }
        return incomeList;
    }
    public void delete(String name) {
        // Удаляем данные для дохода
        editorIncomeData.remove(name + "_sum").apply();
        editorIncomeData.remove(name + "_isStable").apply();
        editorIncomeData.remove(name + "_date").apply();

        // Удаляем данные для трат
        editorSpendingData.remove(name + "_sum").apply();
        editorSpendingData.remove(name + "_isStable").apply();
        editorSpendingData.remove(name + "_date").apply();
    }
}