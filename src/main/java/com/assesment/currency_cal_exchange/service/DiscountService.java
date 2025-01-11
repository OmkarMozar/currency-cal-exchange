package com.assesment.currency_cal_exchange.service;

import com.assesment.currency_cal_exchange.model.Item;
import com.assesment.currency_cal_exchange.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    public double calculatePayableAmount(User user,double totalAmount, List<Item> items) {
        double discount = 0;
        double payableAmount = totalAmount;
        if (user.isEmployee()) {
            discount = 0.30;
        } else if (user.isAffiliate()) {
            discount = 0.10;
        } else if (user.hasBeenCustomerForYears(2)) {
            discount = 0.05;
        }
        for(Item item:items)
        {
            if (!item.isGrocery()) {
                payableAmount -= item.getPrice()*discount;
            }
        }

        return payableAmount;
    }

    public double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public double getCircularDiscountedAmount(double payableAmountInUSD) {
        double circularDiscount = (int) (payableAmountInUSD / 100) * 5;
        return payableAmountInUSD-circularDiscount;
    }
}
