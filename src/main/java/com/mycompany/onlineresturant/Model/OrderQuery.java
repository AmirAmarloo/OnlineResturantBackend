package com.mycompany.onlineresturant.Model;

public class OrderQuery {
    
    private Integer foodId;
    private String name;
    private int price;
    private int qty;
    private String description;
    private int category;

    public OrderQuery(Integer foodId, String name, int price, int qty, String description, int category){
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.category = category;
    }
    
    public Integer getfoodId(){
        return this.foodId;
    }
    
    public void setId(Integer foodId){
        this.foodId = foodId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
    
    public int getCategory(){
        return this.category;
    }
    
    public void setCategory(int category){
        this.category = category;
    }
}
