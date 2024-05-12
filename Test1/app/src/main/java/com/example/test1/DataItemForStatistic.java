package com.example.test1;

public class DataItemForStatistic {
    private String sumIncome;
    private String sumSpending;
    private String date;
    public DataItemForStatistic(String sumIncome, String sumSpending, String date) {
        this.sumIncome = sumIncome;
        this.sumSpending = sumSpending;
        this.date = date;
    }
    public String getSum() {return sumIncome;}
    public String getName() {return sumSpending;}
    public String getDate(){return date;}
}
