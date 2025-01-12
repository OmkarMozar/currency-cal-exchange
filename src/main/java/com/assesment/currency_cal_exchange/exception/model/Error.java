package com.assesment.currency_cal_exchange.exception.model;

import java.util.Objects;

public class Error {

    private String source;
    private String reasonCode;
    private String description;
    private Boolean recoverable;
    private String details;

    public String getSource() {
        return source;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getRecoverable() {
        return recoverable;
    }

    public String getDetails() {
        return details;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecoverable(Boolean recoverable) {
        this.recoverable = recoverable;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(source, error.source) && Objects.equals(reasonCode, error.reasonCode) && Objects.equals(description, error.description) && Objects.equals(recoverable, error.recoverable) && Objects.equals(details, error.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, reasonCode, description, recoverable, details);
    }
}
