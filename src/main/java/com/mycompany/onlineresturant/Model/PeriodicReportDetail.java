package com.mycompany.onlineresturant.Model;

public class PeriodicReportDetail {
    private int Id;
    private String dateTime;
    private String foodName;
    private int orderPrice;
    private int orderQTY;
    private int totalOrderPrice;
    private int category;
    private int takeawayPrice;
    private int takeawayQty;
    private int twTotalPrice;
    private int totalPrice;
    private int grossProfit;

    public int getId(){
        return this.Id; 
    }
    
    public void setId(int Id){
        this.Id = Id;
    }
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
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

    public int getTakeawayQty() {
        return takeawayQty;
    }

    public void setTakeawayQty(int takeawayQty) {
        this.takeawayQty = takeawayQty;
    }

    public int getTwTotalPrice() {
        return twTotalPrice;
    }

    public void setTwTotalPrice(int twTotalPrice) {
        this.twTotalPrice = twTotalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(int grossProfit) {
        this.grossProfit = grossProfit;
    }
    
    public PeriodicReportDetail(int Id){
        this.Id = Id;
    }
    
    public PeriodicReportDetail(int Id, String dateTime, String foodName, int orderPrice,
            int orderQTY, int totalOrderPrice, int category, int takeawayPrice,
            int takeawayQty, int twTotalPrice, int totalPrice, int grossProfit){
        this.Id = Id;
        this.dateTime = dateTime;
        this.foodName = foodName;
        this.orderPrice = orderPrice;
        this.orderQTY = orderQTY;
        this.totalOrderPrice = totalOrderPrice;
        this.category = category;
        this.takeawayPrice = takeawayPrice;
        this.takeawayQty = takeawayQty;
        this.twTotalPrice = twTotalPrice;
        this.totalPrice = totalPrice;
        this.grossProfit = grossProfit;
    }
}
