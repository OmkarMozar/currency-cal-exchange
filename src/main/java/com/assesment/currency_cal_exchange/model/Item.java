package com.assesment.currency_cal_exchange.model;

import lombok.Builder;

@Builder
public class Item {
    private String name;
    private String category; // "grocery" or "non-grocery"
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isGrocery() {
        return "grocery".equalsIgnoreCase(category);
    }
}
