package ru.bolotnaya.RESTProject3Alishev.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolotnaya.RESTProject3Alishev.DTO.*;
import ru.bolotnaya.RESTProject3Alishev.models.Measurement;
import ru.bolotnaya.RESTProject3Alishev.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorMapper sensorMapper;
    private final MeasurementMapper measurementMapper;

    public void saveMeasurements(Measurement measurements) {
        measurements.setAddedAt(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }

    public List<Measurement> allMeasurements() {
        return measurementsRepository.findAll();
    }

    public List<MeasurementAndSensorDTO> allMeasurementsDTO(SensorDTO sensorDTO,
                                                            MeasurementAndSensorDTO measurementsAndSensorDTO,
                                                            ArrayList<MeasurementAndSensorDTO> list) {
        for (int i = 0; i < allMeasurements().size(); i++) {
            sensorDTO = sensorMapper.toSensorDTO(allMeasurements().get(i).getSensor());
            measurementsAndSensorDTO = measurementMapper.toMeasurementsAndSensorDTO(allMeasurements().get(i));
            measurementsAndSensorDTO.setSensorDTO(sensorDTO);
            list.add(measurementsAndSensorDTO);
        }
        return list;
    }

    public String rainyDaysCount() {
        long counter = allMeasurements().stream()
                .filter(Measurement::getRaining)
                .count();

        return "The quantity of rainy days is " + counter;
    }

}
