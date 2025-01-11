package com.assesment.currency_cal_exchange.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class BillRequest {

    private @Size(min = 1) List<Item> items;

    private
    @Size(max = 30, message = "Length must be less than 30")
    @Pattern(regexp = "[a-z]*[A-Z]*", message = "User must contain alphabets only.")
    User user;

    private String originalCurrency;
    private String targetCurrency;
    private Double totalAmount;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double calculateTotalAmount() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    /*public boolean isGroceryBill() {
        return items.stream().allMatch(Item::isGrocery);
    }*/
}
