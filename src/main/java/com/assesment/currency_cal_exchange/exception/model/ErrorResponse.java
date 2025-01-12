package com.assesment.currency_cal_exchange.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class ErrorResponse {

    @JsonProperty("Errors")
    private @NotNull Errors error;
}
