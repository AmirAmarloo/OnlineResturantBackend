package com.mycompany.onlineresturant.Model;

public class OrderByStatus {
    
    private int id;
    private String name;
    private int qty;
    private String description;
    private int userId;
    private String dateTime;
    private int orderGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderGroup() {
        return orderGroup;
    }

    public void setOrderGroup(int orderGroup) {
        this.orderGroup = orderGroup;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
           
    public OrderByStatus(int id, String name, int qty, String description, int userId, String dateTime, int orderGroup){
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
        this.orderGroup = orderGroup;
    }
    
    public OrderByStatus(int userId){
        this.userId = userId;
    }
    
}
