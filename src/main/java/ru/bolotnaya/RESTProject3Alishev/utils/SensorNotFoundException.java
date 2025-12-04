package ru.bolotnaya.RESTProject3Alishev.utils;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class SensorNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "The sensor has not been found";

    public SensorNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public SensorNotFoundException(String message) {
        super(message);
    }

    public SensorNotFoundException(String message, String name) {
        super(MessageFormat.format(message, name));
    }

    public static Supplier<SensorNotFoundException> sensorNotFoundException(String message, String name) {
        return () -> new SensorNotFoundException(message, name);
    }

}
