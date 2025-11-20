package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorDTO;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;
import ru.bolotnaya.RESTProject3Alishev.util.SensorErrorResponse;
import ru.bolotnaya.RESTProject3Alishev.util.SensorNotCreatedException;
import ru.bolotnaya.RESTProject3Alishev.util.SensorValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    @GetMapping
    public List<SensorDTO> getSensors(){
        return sensorService.findAll().stream().
                map(this::convertToSensorDTO).
                collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public SensorDTO getSensorByName(@PathVariable String name){
        return convertToSensorDTO(sensorService.findByName(name).get());
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> saveSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");

            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleResponse(SensorNotCreatedException sensorNotCreatedException){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(sensorNotCreatedException.getMessage());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
