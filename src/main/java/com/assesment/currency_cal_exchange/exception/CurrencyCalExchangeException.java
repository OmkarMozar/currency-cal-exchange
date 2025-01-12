package com.assesment.currency_cal_exchange.exception;

public class CurrencyCalExchangeException extends RuntimeException {

    private String reasonCode;

    public String getReasonCode() {
        return reasonCode;
    }

    public CurrencyCalExchangeException(String message) {
        super(message);
    }

    public CurrencyCalExchangeException(String reasonCode, String message) {
        super(message);
        this.reasonCode = reasonCode;
    }

    public CurrencyCalExchangeException(String reasonCode, Throwable ex) {
        super(ex);
        this.reasonCode = reasonCode;
    }

    public CurrencyCalExchangeException(String reasonCode, String message,  Throwable ex) {
        super(message, ex);
        this.reasonCode = reasonCode;
    }





}
