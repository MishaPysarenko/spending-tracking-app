package com.example.test1;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryMeneger {
    //хуйня для сохранения в память телефона, разбита сразу на доходы и траты
    private static SharedPreferences IncomeData;
    private static SharedPreferences.Editor editorIncomeData;
    private static SharedPreferences SpendingData;
    private static SharedPreferences.Editor editorSpendingData;
    //Для календаря
    private static SharedPreferences Statistics;
    private static SharedPreferences.Editor editorStatistics;
    //---------------------------------------------------------------------
    private static Context mContext; // Добавляем поле для хранения контекста
    private static List<DataItem> dataListIncome;
    private static List<DataItem> dataListSpending;
    private static List<DataItem> dataListStatistics;

    public static void AddDebugDATA() {
        DataItem temp1 = new DataItem("500","Параш лента","03.05.2024",true);
        dataListSpending.add(temp1);
        DataItem temp2 = new DataItem("360","Жижка","03.05.2024",false);
        dataListSpending.add(temp2);
        DataItem temp3 = new DataItem("750","Винцо","03.05.2024",true);
        dataListSpending.add(temp3);
        DataItem temp4 = new DataItem("1500","Я хуй знает","03.05.2024",false);
        dataListSpending.add(temp4);
        DataItem temp5 = new DataItem("400","Шавуха","03.05.2024",false);
        dataListSpending.add(temp5);

        DataItem temp6 = new DataItem("600","Нашел на улице","03.05.2024",true);
        dataListStatistics.add(temp6);
        DataItem temp7 = new DataItem("360","у друга спиздил","03.05.2024",false);
        dataListStatistics.add(temp7);
        DataItem temp8 = new DataItem("750","Отдал вино","03.05.2024",false);
        dataListStatistics.add(temp8);
        DataItem temp9 = new DataItem("1500","ЗП","03.05.2024",true);
        dataListStatistics.add(temp9);
        DataItem temp10 = new DataItem("400","Шавуха оказалась просроченой","03.05.2024",false);
        dataListStatistics.add(temp10);

        DataItem temp11 = new DataItem("600","Нашел на улице","03.05.2024",true);
        dataListStatistics.add(temp6);
        DataItem temp12 = new DataItem("360","у друга спиздил","03.05.2024",false);
        dataListStatistics.add(temp7);
        DataItem temp13 = new DataItem("750","Отдал вино","03.05.2024",false);
        dataListStatistics.add(temp8);
        DataItem temp14 = new DataItem("1500","ЗП","03.05.2024",true);
        dataListStatistics.add(temp9);
        DataItem temp15 = new DataItem("400","Шавуха оказалась просроченой","03.05.2024",false);
        dataListStatistics.add(temp10);
    }
    private MemoryMeneger() {

    }
    public static void init(Context context) {
        mContext = context;
        // Получаем объекты SharedPreferences с помощью контекста
        IncomeData = mContext.getSharedPreferences("Income", Context.MODE_PRIVATE);
        editorIncomeData = IncomeData.edit();

        SpendingData = mContext.getSharedPreferences("Spending", Context.MODE_PRIVATE);
        editorSpendingData = SpendingData.edit();

        //---------------------------------------------------------------------------------------------------
        Statistics = mContext.getSharedPreferences("Statistics", Context.MODE_PRIVATE);
        editorStatistics = Statistics.edit();
        //---------------------------------------------------------------------------------------------------

        dataListIncome = getIncome();
        dataListSpending = getSpending();

        dataListStatistics = getStatistic();

        if (dataListIncome == null)
            dataListIncome = new ArrayList<>();

        if (dataListSpending == null)
            dataListSpending = new ArrayList<>();

        if (dataListStatistics == null)
            dataListStatistics = new ArrayList<>();

    }
    public static void AddToIncomeList(DataItem temp) {
        dataListIncome.add(temp);
        saveIncome(temp);
    }
    public static void AddToSpendingList(DataItem temp) {
        dataListSpending.add(temp);
        saveSpending(temp);
    }
    public static void AddToStatisticsList(DataItem temp) {
        dataListStatistics.add(temp);
        saveIncome(temp);
    }
    private static void saveIncome(DataItem income) {
        String name = income.getName();
        editorIncomeData.putInt(name + "_sum", Integer.parseInt(income.getSum()));
        editorIncomeData.putBoolean(name + "_isStable", income.getIsStable());
        editorIncomeData.putString(name + "_date", income.getDate());
        editorIncomeData.apply();
    }
    public static void saveSpending(DataItem spending) {
        String name = spending.getName();
        editorSpendingData.putInt(name + "_sum", Integer.parseInt(spending.getSum()));
        editorSpendingData.putBoolean(name + "_isStable", spending.getIsStable());
        editorSpendingData.putString(name + "_date", spending.getDate());
        editorSpendingData.apply();
    }
    private static void saveStatistics(DataItem income) {
        String name = income.getName();
        editorStatistics.putInt(name + "_sum", Integer.parseInt(income.getSum()));
        editorStatistics.putBoolean(name + "_isStable", income.getIsStable());
        editorStatistics.putString(name + "_date", income.getDate());
        editorStatistics.apply();
    }
    public static List<DataItem> GetListStatistics(){
        List<DataItem> incomeList = dataListStatistics;
        return incomeList;
    }
    public static List<DataItem> GetListIncome(){
        List<DataItem> incomeList = dataListIncome;
        return incomeList;
    }
    public static List<DataItem> GetListSpending(){
        List<DataItem> incomeList = dataListSpending;
        return incomeList;
    }
    private static List<DataItem> getStatistic() {
        List<DataItem> incomeList = new ArrayList<>();
        Map<String, ?> incomeEntries = Statistics.getAll();
        for (Map.Entry<String, ?> entry : incomeEntries.entrySet()) {
            String name = entry.getKey();
            if (name.endsWith("_sum")) {
                String itemName = name.replace("_sum", "");
                int sum = Statistics.getInt(name, 0); // Значение по умолчанию 0
                boolean isStable = Statistics.getBoolean(itemName + "_isStable", false);
                String date = Statistics.getString(itemName + "_date", "");
                incomeList.add(new DataItem(String.valueOf(sum), itemName, date, isStable));
            }
        }
        return incomeList;
    }
    private static List<DataItem> getSpending() {
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
    private static List<DataItem> getIncome() {
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
    public static void delete(String name) {
        if(IncomeData.getString(name,null)!=null)
        {
            // Удаляем данные для дохода
            editorIncomeData.remove(name + "_sum").apply();
            editorIncomeData.remove(name + "_isStable").apply();
            editorIncomeData.remove(name + "_date").apply();
            IncomePage.updateData();
        }
        if(SpendingData.getString(name,null)!=null)
        {
            // Удаляем данные для трат
            editorSpendingData.remove(name + "_sum").apply();
            editorSpendingData.remove(name + "_isStable").apply();
            editorSpendingData.remove(name + "_date").apply();
            SpendingPage.updateData();
        }
        if(Statistics.getString(name,null)!=null)
        {
            // Удаляем данные для трат
            editorStatistics.remove(name + "_sum").apply();
            editorStatistics.remove(name + "_isStable").apply();
            editorStatistics.remove(name + "_date").apply();
            SpendingPage.updateData();
        }
    }
    public static void deleteFalseItems() {
        // Удаляем элементы с булевым значением false из данных для дохода
        for (Map.Entry<String, ?> entry : IncomeData.getAll().entrySet()) {
            String key = entry.getKey();
            if (key.endsWith("_isStable") && !IncomeData.getBoolean(key, true)) {
                String name = key.substring(0, key.length() - "_isStable".length());
                editorIncomeData.remove(name + "_sum").apply();
                editorIncomeData.remove(name + "_isStable").apply();
                editorIncomeData.remove(name + "_date").apply();
                IncomePage.updateData();
            }
        }

        // Удаляем элементы с булевым значением false из данных для трат
        for (Map.Entry<String, ?> entry : SpendingData.getAll().entrySet()) {
            String key = entry.getKey();
            if (key.endsWith("_isStable") && !SpendingData.getBoolean(key, true)) {
                String name = key.substring(0, key.length() - "_isStable".length());
                editorSpendingData.remove(name + "_sum").apply();
                editorSpendingData.remove(name + "_isStable").apply();
                editorSpendingData.remove(name + "_date").apply();
                SpendingPage.updateData();
            }
        }
    }
    public static int GetAmountIncome() {
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
    public static int GetAmountSpending() {
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
}