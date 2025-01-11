package com.assesment.currency_cal_exchange.service;

import com.assesment.currency_cal_exchange.model.CurrencyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchangeService {

    @Value("${currency.api.base-url}")
    public String baseUrl;

    @Value("${currency.api.key}")
    public String apiKey;

    public HashMap<String,Map<String, Double>>  cache = HashMap.newHashMap(0);

    public RestTemplate restTemplate = new RestTemplate();

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        if(cache.containsKey(baseCurrency)) {
            System.out.println("Returning from cache");
            return cache.get(baseCurrency);
        }

        String url = String.format("%s/%s/latest/%s",baseUrl,apiKey, baseCurrency);

        ResponseEntity<CurrencyApiResponse> response = restTemplate.getForEntity(url, CurrencyApiResponse.class);
        cache.put(baseCurrency,response.getBody().getConversionRates());
        return response.getBody().getConversionRates();
    }

    // TODO implement reset cache

}
