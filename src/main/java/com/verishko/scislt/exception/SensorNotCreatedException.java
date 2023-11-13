package com.verishko.scislt.exception;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException() {
    }

    public SensorNotCreatedException(String message) {
        super(message);
    }

    public SensorNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SensorNotCreatedException(Throwable cause) {
        super(cause);
    }

    public SensorNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
