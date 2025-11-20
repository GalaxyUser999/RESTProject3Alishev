package ru.bolotnaya.RESTProject3Alishev.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {
    @Size(min = 3, max = 30, message = "The sensor's name should be between 3 and 30")
    @NotEmpty(message = "Enter the sensor's name")
    private String name;

}
