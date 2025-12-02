package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.*;
import ru.bolotnaya.RESTProject3Alishev.services.MeasurementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/measurements")
@FieldDefaults(makeFinal = true)
public class MeasurementController {
    MeasurementService measurementService;

    @GetMapping
    public List<MeasurementAndSensorDTO> allMeasurements(@ModelAttribute SensorDTO sensorDTO,
                                                         @ModelAttribute MeasurementAndSensorDTO measurementsAndSensorDTO) {
        return measurementService.allMeasurementsAndSensorsDTO(sensorDTO, measurementsAndSensorDTO);
    }


    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount() {
        return measurementService.rainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementAndSensorDTO> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(measurementService.addMeasurement(measurementDTO));
    }

}
