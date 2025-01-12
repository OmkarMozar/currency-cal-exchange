package com.assesment.currency_cal_exchange.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class CurrencyApiResponse {

    @JsonProperty("base_code")
    private String baseCode; // Maps to "base_code" in the API response

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates; // Maps to "conversion_rates"

    // Getters and setters
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

}
