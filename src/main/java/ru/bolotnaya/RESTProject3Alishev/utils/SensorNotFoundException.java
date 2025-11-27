package ru.bolotnaya.RESTProject3Alishev.utils;

public class SensorNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "The sensor has not been found";

    public SensorNotFoundException(String message) {
        super(message);
    }

    public SensorNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
