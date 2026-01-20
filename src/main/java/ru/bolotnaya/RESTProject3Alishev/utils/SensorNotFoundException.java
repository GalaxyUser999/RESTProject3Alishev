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

    public SensorNotFoundException(String message, Object object) {
        super(MessageFormat.format(message, object));
    }

    public static Supplier<SensorNotFoundException> sensorNotFoundException(String message, Object object) {
        return () -> new SensorNotFoundException(message, object);
    }

}
