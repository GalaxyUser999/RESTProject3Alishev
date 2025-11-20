package ru.bolotnaya.RESTProject3Alishev.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bolotnaya.RESTProject3Alishev.models.Measurements;

@Component
public class MeasurementsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;
        if(measurements.getSensor()==null){
            errors.rejectValue("sensor", "", "Enter the sensor");
        }
    }
}
