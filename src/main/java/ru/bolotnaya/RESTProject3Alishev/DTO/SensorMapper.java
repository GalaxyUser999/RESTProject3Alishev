package ru.bolotnaya.RESTProject3Alishev.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SensorMapper {
    SensorDTO toSensorDTO(Sensor sensor);

    List<SensorDTO> toUserResponseList(List<Sensor> sensors);

    Sensor toSensor(SensorDTO sensorDTO);
}
