package com.assesment.currency_cal_exchange.controller;

import com.assesment.currency_cal_exchange.model.BillRequest;
import com.assesment.currency_cal_exchange.model.Item;
import com.assesment.currency_cal_exchange.model.PayableAmountResponse;
import com.assesment.currency_cal_exchange.model.User;
import com.assesment.currency_cal_exchange.service.CurrencyExchangeService;
import com.assesment.currency_cal_exchange.service.DiscountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BillControllerTest {

    @Mock
    private CurrencyExchangeService exchangeService;

    @Mock
    private DiscountService discountService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BillController billController;

    public BillControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculatePayableAmount() {
        //given
        BillRequest billRequest = new BillRequest();
        billRequest.setOriginalCurrency("USD");
        billRequest.setTargetCurrency("EUR");
        billRequest.setTotalAmount(200.0);
        Item item = new Item("Apple", "grocery", 50.0);
        billRequest.setItems(Collections.singletonList(item));
        User user = new User("employee", 3);

        billRequest.setUser(user);

        when(authentication.getName()).thenReturn("testUser");
        when(exchangeService.getExchangeRates("USD"))
                .thenReturn(Map.of("USD", 1.0, "EUR", 0.85));
        when(discountService.calculatePayableAmount(user, 200.0, billRequest.getItems()))
                .thenReturn(140.0);
        when(discountService.convertCurrency(140.0, 1.0)).thenReturn(140.0);
        when(discountService.getCircularDiscountedAmount(140.0)).thenReturn(135.0);
        when(discountService.convertCurrency(135.0, 1.0)).thenReturn(135.0);
        when(discountService.convertCurrency(135.0, 0.85)).thenReturn(114.75);

        //when
        ResponseEntity<PayableAmountResponse> response =
                billController.calculatePayableAmount(billRequest, authentication);

        //then
        assertEquals(114.75, response.getBody().getPayableAmount(), 0.001);
        verify(exchangeService, times(1)).getExchangeRates("USD");
        verify(discountService, times(1))
                .calculatePayableAmount(user, 200.0, billRequest.getItems());
    }
}

