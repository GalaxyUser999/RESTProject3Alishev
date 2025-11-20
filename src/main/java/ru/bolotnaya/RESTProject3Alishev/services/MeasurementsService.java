package ru.bolotnaya.RESTProject3Alishev.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolotnaya.RESTProject3Alishev.models.Measurements;
import ru.bolotnaya.RESTProject3Alishev.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Transactional
    public void saveMeasurements(Measurements measurements){
        measurements.setAddedAt(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }

    public List<Measurements> allMeasurements(){
        return measurementsRepository.findAll();
    }
}
