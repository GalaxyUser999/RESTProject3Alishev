package ru.bolotnaya.RESTProject3Alishev.utils;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class SensorNotCreatedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Failed to create the sensor";

    public SensorNotCreatedException(String message) {
        super(message);
    }

    public SensorNotCreatedException() {
        super(DEFAULT_MESSAGE);
    }

    public SensorNotCreatedException(String message, Object object) {
        super(MessageFormat.format(message, object));
    }

    public static Supplier<SensorNotCreatedException> sensorNotCreatedException(String message, Object object) {
        return () -> new SensorNotCreatedException(message, object);
    }
}
