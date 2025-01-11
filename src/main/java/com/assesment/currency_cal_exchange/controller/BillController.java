package com.assesment.currency_cal_exchange.controller;

import com.assesment.currency_cal_exchange.model.BillRequest;
import com.assesment.currency_cal_exchange.model.PayableAmountResponse;
import com.assesment.currency_cal_exchange.service.CurrencyExchangeService;
import com.assesment.currency_cal_exchange.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    private CurrencyExchangeService exchangeService;

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate")
    public ResponseEntity<PayableAmountResponse> calculatePayableAmount(@RequestBody BillRequest billRequest, Authentication authentication) {
        //return ResponseEntity.ok(new PayableAmountResponse(0.25));
        String username = authentication.getName(); // Retrieve authenticated username
        System.out.println("Authenticated user: " + username);
        Map<String, Double> rates = exchangeService.getExchangeRates(billRequest.getOriginalCurrency());
        double targetCurrencyExchangeRate = rates.get(billRequest.getTargetCurrency());
        double payableAmount0 = discountService.calculatePayableAmount(
                billRequest.getUser(), billRequest.getTotalAmount(), billRequest.getItems());
        double usdExchangeRate = rates.get("USD");
        double payableAmountInUSD = discountService.convertCurrency(payableAmount0, usdExchangeRate);
        double circularDiscountedAmountUSD = discountService.getCircularDiscountedAmount(payableAmountInUSD);
        double payableAmount = discountService.convertCurrency(circularDiscountedAmountUSD, 1 / usdExchangeRate);
        double finalPayableAmount = discountService.convertCurrency(payableAmount, targetCurrencyExchangeRate);
        return ResponseEntity.ok(new PayableAmountResponse(finalPayableAmount));
    }

}
