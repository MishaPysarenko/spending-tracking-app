package com.example.test1;

public class DataItem {
    private String sum;
    private String name;
    private String date;
    private Boolean isStable;

    public DataItem(String sum, String name, String date, Boolean isStable) {
        this.sum = sum;
        this.name = name;
        this.date = date;
        this.isStable = isStable;
    }
    public String getSum() {return sum;}
    public String getName() {return name;}
    public String getDate(){return date;}
    public Boolean getIsStable(){return isStable;}
}
