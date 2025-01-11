package com.assesment.currency_cal_exchange.service;

import com.assesment.currency_cal_exchange.model.CurrencyApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CurrencyExchangeServiceTest {

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set mock values for @Value fields
        currencyExchangeService.baseUrl = "https://api.example.com";
        currencyExchangeService.apiKey = "test-api-key";
    }

    @Test
    void testGetExchangeRates_cacheMiss() {
        // Given
        String baseCurrency = "USD";
        String expectedUrl = "https://api.example.com/test-api-key/latest/USD";

        // Mock the CurrencyApiResponse
        CurrencyApiResponse mockResponse = new CurrencyApiResponse();
        mockResponse.setConversionRates(Map.of("USD", 1.0, "EUR", 0.85));

        // Mock RestTemplate behavior
        when(restTemplate.getForEntity(expectedUrl, CurrencyApiResponse.class))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        // When
        Map<String, Double> rates = currencyExchangeService.getExchangeRates(baseCurrency);

        // Then
        assertEquals(2, rates.size());
        assertEquals(1.0, rates.get("USD"));
        assertEquals(0.85, rates.get("EUR"));
        verify(restTemplate, times(1)).getForEntity(expectedUrl, CurrencyApiResponse.class);
    }

    @Test
    void testGetExchangeRates_cacheHit() {
        // Given
        String baseCurrency = "USD";
        currencyExchangeService.cache.put(baseCurrency, Map.of("USD", 1.0, "EUR", 0.85));

        // When
        Map<String, Double> rates = currencyExchangeService.getExchangeRates(baseCurrency);

        // Then
        assertEquals(2, rates.size());
        assertEquals(1.0, rates.get("USD"));
        assertEquals(0.85, rates.get("EUR"));
        verify(restTemplate, times(0)).getForEntity(anyString(), eq(CurrencyApiResponse.class));
    }
}
