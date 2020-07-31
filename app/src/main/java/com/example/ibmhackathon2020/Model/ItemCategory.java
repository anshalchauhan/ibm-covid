package com.example.ibmhackathon2020.Model;

public class ItemCategory {
    private String itemName, Desc, sellerId,unit;
    private Integer qty, cost,itemId;

    public ItemCategory() {
    }

    public ItemCategory(String itemName, String desc, String sellerId, String unit, Integer qty, Integer cost, Integer itemId) {
        this.itemName = itemName;
        Desc = desc;
        this.sellerId = sellerId;
        this.unit = unit;
        this.qty = qty;
        this.cost = cost;
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}