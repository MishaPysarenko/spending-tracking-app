package com.example.test1;

public class DataItemForStatistic {
    private String Date;
    private String SumIncome;
    private String SumSpending;

    DataItemForStatistic(String SumIncome, String SumSpending, String Date) {
        this.Date = Date;
        this.SumIncome = SumIncome;
        this.SumSpending = SumSpending;
    }

    public String getName()
    {
        return Date;
    }
    public String getSumIncome()
    {
        return SumIncome;
    }
    public String getSumSpending()
    {
        return SumSpending;
    }
}
