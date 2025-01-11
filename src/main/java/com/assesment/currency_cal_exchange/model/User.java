package com.assesment.currency_cal_exchange.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class User {

    private String userType; // "employee", "affiliate", or "customer"
    private int tenureInYears; // Years the user has been a customer

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getTenureInYears() {
        return tenureInYears;
    }

    public void setTenureInYears(int tenureInYears) {
        this.tenureInYears = tenureInYears;
    }

    public boolean isEmployee() {
        return "employee".equalsIgnoreCase(userType);
    }

    public boolean isAffiliate() {
        return "affiliate".equalsIgnoreCase(userType);
    }

    public boolean hasBeenCustomerForYears(int years) {
        return tenureInYears >= years;
    }
}
