package ru.bolotnaya.RESTProject3Alishev.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;

@Getter
@Setter
public class MeasurementDTO {
   @Min(value = -100,  message = "Temperature can be between -100 and 100")
   @Max(value = 100,  message = "Temperature can be between -100 and 100")
   @NotNull(message = "Enter temperature")
    private Double value;

    @NotNull(message = "Enter raining measurements")
    private Boolean raining;

    @NotNull(message = "Enter the sensor")
    private Sensor sensor;


}
