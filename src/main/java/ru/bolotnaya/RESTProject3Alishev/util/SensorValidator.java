package ru.bolotnaya.RESTProject3Alishev.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;

@Component
@AllArgsConstructor
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
