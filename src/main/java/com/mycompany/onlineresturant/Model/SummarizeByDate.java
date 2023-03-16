package com.mycompany.onlineresturant.Model;

public class SummarizeByDate {

    private String datetime; 
    private int category; 
    private int takeawayPrice;
    private int qty;
    private int orderPrice;
    private int grossProfit;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String date_time) {
        this.datetime = date_time;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getTakeawayPrice() {
        return takeawayPrice;
    }

    public void setTakeawayPrice(int takeawayPrice) {
        this.takeawayPrice = takeawayPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(int grossProfit) {
        this.grossProfit = grossProfit;
    }
    
    public SummarizeByDate(String datetime, int takeawayPrice,
        int qty, int orderPrice, int grossProfit){
        this.datetime = datetime;
        this.takeawayPrice = takeawayPrice;
        this.qty = qty;
        this.orderPrice = orderPrice;
        this.grossProfit = grossProfit;
    }
}
