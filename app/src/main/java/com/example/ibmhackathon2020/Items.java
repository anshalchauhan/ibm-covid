package com.example.ibmhackathon2020;

public class Items {
    private String itemName, Desc, sellerId;
    private String qty, cost;

    public Items() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String  getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}