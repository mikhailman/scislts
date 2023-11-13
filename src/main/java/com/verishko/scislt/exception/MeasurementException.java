package com.verishko.scislt.exception;

public class MeasurementException extends RuntimeException {
    public MeasurementException() {
    }

    public MeasurementException(String message) {
        super(message);
    }

    public MeasurementException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeasurementException(Throwable cause) {
        super(cause);
    }

    public MeasurementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}