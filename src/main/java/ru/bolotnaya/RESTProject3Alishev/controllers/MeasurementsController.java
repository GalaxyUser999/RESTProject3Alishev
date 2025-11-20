package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.MeasurementsAndSensorDTO;
import ru.bolotnaya.RESTProject3Alishev.DTO.MeasurementsDTO;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorDTO;
import ru.bolotnaya.RESTProject3Alishev.models.Measurements;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;
import ru.bolotnaya.RESTProject3Alishev.services.MeasurementsService;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;
import ru.bolotnaya.RESTProject3Alishev.util.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final MeasurementsValidator measurementsValidator;

    @GetMapping
    public List<MeasurementsAndSensorDTO> allMeasurements(@ModelAttribute SensorDTO sensorDTO,
                                                          @ModelAttribute MeasurementsAndSensorDTO measurementsAndSensorDTO) {
        List<MeasurementsAndSensorDTO> list = new ArrayList<>();
        for (int i = 0; i < measurementsService.allMeasurements().size(); i++) {
            sensorDTO = convertToSensorDTO(measurementsService.allMeasurements().get(i).getSensor());
            measurementsAndSensorDTO = convertToMeasurementsAndSensorDTO(measurementsService.allMeasurements().get(i));
            measurementsAndSensorDTO.setSensorDTO(sensorDTO);
            list.add(measurementsAndSensorDTO);
        }
        return list;
    }

    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount() {
        int counter = 0;
        for (int i = 0; i < measurementsService.allMeasurements().size(); i++){
            boolean rainy = measurementsService.allMeasurements().get(i).getRaining();
            if(rainy){
                counter++;
            }
        }
        return "The quantity of rainy days is "+counter;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) {
        measurementsValidator.validate(convertToMeasurements(measurementsDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");

            throw new MeasurementsNotCreatedException(errorMessage.toString());
        }
        int id = sensorService.findByNameWithException(measurementsDTO.getSensor().getName()).getId();
        measurementsDTO.getSensor().setId(id);
        measurementsService.saveMeasurements(convertToMeasurements(measurementsDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

    private MeasurementsAndSensorDTO convertToMeasurementsAndSensorDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsAndSensorDTO.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsResponseError> handleResponse(MeasurementsNotCreatedException measurementsNotCreatedException) {
        MeasurementsResponseError measurementsResponseError = new MeasurementsResponseError(measurementsNotCreatedException.getMessage());
        return new ResponseEntity<>(measurementsResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleResponse(SensorNotFoundException p) {
        SensorErrorResponse response = new SensorErrorResponse("Sensor with this name has not been found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
