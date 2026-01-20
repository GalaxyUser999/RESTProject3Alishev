package ru.bolotnaya.RESTProject3Alishev.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bolotnaya.RESTProject3Alishev.models.Measurement;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasurementMapper {
    MeasurementDTO toMeasurementDTO(Measurement measurement);
    Measurement toMeasurement(MeasurementDTO measurementDTO);
    MeasurementAndSensorDTO toMeasurementsAndSensorDTO(Measurement measurement);
}
