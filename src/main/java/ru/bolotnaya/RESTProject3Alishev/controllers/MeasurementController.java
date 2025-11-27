package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.*;
import ru.bolotnaya.RESTProject3Alishev.models.Measurement;
import ru.bolotnaya.RESTProject3Alishev.services.MeasurementService;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;
import ru.bolotnaya.RESTProject3Alishev.utils.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasurementMapper measurementMapper;
    private final SensorMapper sensorMapper;

    @GetMapping
    public List<MeasurementAndSensorDTO> allMeasurements(@ModelAttribute SensorDTO sensorDTO,
                                                         @ModelAttribute MeasurementAndSensorDTO measurementsAndSensorDTO,
                                                         @ModelAttribute ArrayList<MeasurementAndSensorDTO> list) {
        return measurementService.allMeasurementsDTO(sensorDTO, measurementsAndSensorDTO, list);
    }


    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount() {
        return measurementService.rainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementAndSensorDTO> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        int id = sensorService.findByNameWithException(measurementDTO.getSensor().getName()).getId();
        measurementDTO.getSensor().setId(id);
        Measurement measurement = measurementMapper.toMeasurement(measurementDTO);
        measurementService.saveMeasurements(measurement);

        MeasurementAndSensorDTO response = measurementMapper.toMeasurementsAndSensorDTO(measurement);
        response.setSensorDTO(sensorMapper.toSensorDTO(measurement.getSensor()));

        return ResponseEntity.ok(response);
    }

}
