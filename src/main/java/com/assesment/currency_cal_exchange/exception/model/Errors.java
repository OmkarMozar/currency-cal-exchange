package com.assesment.currency_cal_exchange.exception.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private @NotNull @Valid List<Error> errorList = new ArrayList<>();
}
