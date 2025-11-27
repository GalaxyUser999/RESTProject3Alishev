package ru.bolotnaya.RESTProject3Alishev.utils;

public class SensorNotCreatedException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Failed to create the sensor";

    public SensorNotCreatedException(String message) {
        super(message);
    }

    public SensorNotCreatedException() {
        super(DEFAULT_MESSAGE);
    }
}
