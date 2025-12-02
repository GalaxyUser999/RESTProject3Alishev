package ru.bolotnaya.RESTProject3Alishev.services;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.bolotnaya.RESTProject3Alishev.DTO.*;
import ru.bolotnaya.RESTProject3Alishev.models.Measurement;
import ru.bolotnaya.RESTProject3Alishev.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class MeasurementService {
    MeasurementsRepository measurementsRepository;
    SensorMapper sensorMapper;
    MeasurementMapper measurementMapper;
    SensorService sensorService;


    public List<Measurement> allMeasurements() {
        return measurementsRepository.findAll();
    }

    public List<MeasurementAndSensorDTO> allMeasurementsAndSensorsDTO(SensorDTO sensorDTO,
                                                                      MeasurementAndSensorDTO measurementsAndSensorDTO) {
        List<MeasurementAndSensorDTO> list = new ArrayList<>();
        for (int i = 0; i < allMeasurements().size(); i++) {
            sensorDTO = sensorMapper.toSensorDTO(allMeasurements().get(i).getSensor());
            measurementsAndSensorDTO = measurementMapper.toMeasurementsAndSensorDTO(allMeasurements().get(i));
            measurementsAndSensorDTO.setSensorDTO(sensorDTO);
            list.add(measurementsAndSensorDTO);
        }
        return list;
    }

    public String rainyDaysCount() {
        long counter = 0;

        for (Measurement measurement: allMeasurements()){
            if (measurement.getRaining())
                counter++;
        }

        return "The quantity of rainy days is " + counter;
    }

    public MeasurementAndSensorDTO addMeasurement(MeasurementDTO measurementDTO) {
        long id = sensorService.findByName(measurementDTO.getSensor().getName()).getId();
        measurementDTO.getSensor().setId(id);
        Measurement measurement = measurementMapper.toMeasurement(measurementDTO);

        measurement.setAddedAt(LocalDateTime.now());
        measurementsRepository.save(measurement);


        MeasurementAndSensorDTO response = measurementMapper.toMeasurementsAndSensorDTO(measurement);
        response.setSensorDTO(sensorMapper.toSensorDTO(measurement.getSensor()));

        return response;
    }
}
