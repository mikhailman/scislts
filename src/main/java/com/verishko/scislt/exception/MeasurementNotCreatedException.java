package com.verishko.scislt.exception;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException() {
    }

    public MeasurementNotCreatedException(String message) {
        super(message);
    }

    public MeasurementNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeasurementNotCreatedException(Throwable cause) {
        super(cause);
    }

    public MeasurementNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
