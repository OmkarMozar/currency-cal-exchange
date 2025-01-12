package com.assesment.currency_cal_exchange.service;

import com.assesment.currency_cal_exchange.model.Item;
import com.assesment.currency_cal_exchange.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
    }

    @Test
    void testCalculatePayableAmount_employeeDiscount_withGroceryAndNonGroceryItems() {
        //given
        
        User user = new User("employee",1);
        Item item1 = new Item("Item1", "non-grocery", 100.0);
        Item item2 = new Item("Item2", "grocery", 200.0);
        List<Item> items = Arrays.asList(item1, item2);

        //when
        double payableAmount = discountService.calculatePayableAmount(user, 300, items);

        //then
        assertEquals(270, payableAmount);
    }

    @Test
    void testCalculatePayableAmount_affiliateDiscount_withGroceryAndNonGroceryItems() {
        //given
        User user = new User("affiliate",1);
        Item item1 = new Item("Item1", "non-grocery", 100.0);
        Item item2 = new Item("Item2", "grocery", 200.0);
        List<Item> items = Arrays.asList(item1, item2);

        //when
        double payableAmount = discountService.calculatePayableAmount(user, 300, items);

        //then
        assertEquals(290, payableAmount);
    }

    @Test
    void testCalculatePayableAmount_customerDiscount_withGroceryAndNonGroceryItems() {
        //given
        User user = new User("customer", 3);
          
        Item item1 = new Item("Item1", "non-grocery", 100.0);
        Item item2 = new Item("Item2", "grocery", 200.0);
                
        List<Item> items = Arrays.asList(item1, item2);

        //when
        double payableAmount = discountService.calculatePayableAmount(user, 300, items);

        //then
        assertEquals(295, payableAmount);
    }

    @Test
    void testCalculatePayableAmount_noDiscount_withGroceryAndNonGroceryItems() {
        //given
        User user = new User("regular", 1);
        Item item1 = new Item("Item1", "non-grocery", 100.0);
        Item item2 = new Item("Item2", "grocery", 200.0);
        List<Item> items = Arrays.asList(item1, item2);

        //when
        double payableAmount = discountService.calculatePayableAmount(user, 300, items);

        //then
        assertEquals(300, payableAmount);
    }

    @Test
    void testCalculatePayableAmount_multipleItems() {
        //given
        User user = new User("employee",1);
        Item item1 = new Item("Item1", "non-grocery", 100.0);
        Item item2 = new Item("Item2", "grocery", 200.0);
        Item item3 = new Item("Item3", "non-grocery", 300.0);
        List<Item> items = Arrays.asList(item1, item2, item3);

        //when
        double payableAmount = discountService.calculatePayableAmount(user, 600, items);

        //then
        assertEquals(480, payableAmount);
    }

    @Test
    void testConvertCurrency() {
        //given
        double amount = 100;
        double exchangeRate = 1.2;

        //when
        double convertedAmount = discountService.convertCurrency(amount, exchangeRate);

        //then
        assertEquals(120, convertedAmount);
    }

    @Test
    void testGetCircularDiscountedAmount() {
        //given
        double amountInUSD = 250;

        //when
        double discountedAmount = discountService.getCircularDiscountedAmount(amountInUSD);

        //then
        assertEquals(240, discountedAmount);
    }
}
