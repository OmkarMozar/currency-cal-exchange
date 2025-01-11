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

    // Test calculatePayableAmount for an employee with grocery and non-grocery items
    @Test
    void testCalculatePayableAmount_employeeDiscount_withGroceryAndNonGroceryItems() {
        // Given
        
        User user = User.builder()
                .userType("employee") // Employee
                .build();
        Item item1 = Item.builder()
                .name("Item1")
                .price(100)
                .category("non-grocery")
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(200)
                .category("grocery")
                .build();
        List<Item> items = Arrays.asList(item1, item2);

        // When
        double payableAmount = discountService.calculatePayableAmount(user, 500, items);

        // Then
        assertEquals(350, payableAmount, 0.001); // 500 - (100*0.30) = 350, no discount on grocery item
    }

    // Test calculatePayableAmount for an affiliate with grocery and non-grocery items
    @Test
    void testCalculatePayableAmount_affiliateDiscount_withGroceryAndNonGroceryItems() {
        // Given
        User user = User.builder()
                .userType("affiliate") // Affiliate
                .build();
        Item item1 = Item.builder()
                .name("Item1")
                .price(100)
                .category("non-grocery")
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(200)
                .category("grocery")
                .build();
        List<Item> items = Arrays.asList(item1, item2);

        // When
        double payableAmount = discountService.calculatePayableAmount(user, 500, items);

        // Then
        assertEquals(400, payableAmount, 0.001); // 500 - (100*0.10) = 400, no discount on grocery item
    }

    // Test calculatePayableAmount for a customer with a 2-year tenure, grocery and non-grocery items
    @Test
    void testCalculatePayableAmount_customerDiscount_withGroceryAndNonGroceryItems() {
        // Given
        User user = User.builder()
                .userType("customer") // Regular customer
                .tenureInYears(3)  // More than 2 years as customer
                .build();
        Item item1 = Item.builder()
                .name("Item1")
                .price(100)
                .category("non-grocery")
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(200)
                .category("grocery")
                .build();
        List<Item> items = Arrays.asList(item1, item2);

        // When
        double payableAmount = discountService.calculatePayableAmount(user, 300, items);

        // Then
        assertEquals(295, payableAmount, 0.001); // 500 - (100*0.05) = 475, no discount on grocery item
    }

    // Test calculatePayableAmount for a customer with no applicable discounts
    @Test
    void testCalculatePayableAmount_noDiscount_withGroceryAndNonGroceryItems() {
        // Given
        User user = User.builder()
                .userType("regular") // Regular customer (no discount)
                .build();
        Item item1 = Item.builder()
                .name("Item1")
                .price(100)
                .category("non-grocery")
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(200)
                .category("grocery")
                .build();
        List<Item> items = Arrays.asList(item1, item2);

        // When
        double payableAmount = discountService.calculatePayableAmount(user, 500, items);

        // Then
        assertEquals(500, payableAmount, 0.001); // No discount applied, total remains 500
    }

    // Test calculatePayableAmount with multiple items of different categories
    @Test
    void testCalculatePayableAmount_multipleItems() {
        // Given
        User user = User.builder()
                .userType("employee") // Employee
                .build();
        Item item1 = Item.builder()
                .name("Item1")
                .price(100)
                .category("non-grocery")  // Non-grocery item
                .build();
        Item item2 = Item.builder()
                .name("Item2")
                .price(200)
                .category("grocery")     // Grocery item (no discount)
                .build();
        Item item3 = Item.builder()
                .name("Item3")
                .price(300)
                .category("non-grocery")  // Non-grocery item
                .build();
        List<Item> items = Arrays.asList(item1, item2, item3);

        // When
        double payableAmount = discountService.calculatePayableAmount(user, 800, items);

        // Then
        assertEquals(560, payableAmount, 0.001); // 800 - (100*0.30) - (300*0.30) = 560, no discount on grocery item
    }

    // Test convertCurrency method
    @Test
    void testConvertCurrency() {
        // Given
        double amount = 100;
        double exchangeRate = 1.2;

        // When
        double convertedAmount = discountService.convertCurrency(amount, exchangeRate);

        // Then
        assertEquals(120, convertedAmount, 0.001); // 100 * 1.2 = 120
    }

    // Test getCircularDiscountedAmount method
    @Test
    void testGetCircularDiscountedAmount() {
        // Given
        double amountInUSD = 250;

        // When
        double discountedAmount = discountService.getCircularDiscountedAmount(amountInUSD);

        // Then
        assertEquals(245, discountedAmount, 0.001); // 250 - (250/100)*5 = 245
    }
}
