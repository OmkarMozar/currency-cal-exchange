package com.assesment.currency_cal_exchange.model;

public class PayableAmountResponse {
    private double payableAmount;

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public PayableAmountResponse(double payableAmount) {
        this.payableAmount = payableAmount;
    }
}

