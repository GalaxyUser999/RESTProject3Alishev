package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorDTO;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorMapper;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;
import ru.bolotnaya.RESTProject3Alishev.utils.SensorNotCreatedException;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorMapper sensorMapper;
    @GetMapping
    public List<SensorDTO> getSensors(){
        return sensorMapper.toUserResponseList(sensorService.findAll());
    }

    @GetMapping("/{name}")
    public SensorDTO getSensorByName(@PathVariable String name){
        return sensorMapper.toSensorDTO(sensorService.findByNameWithException(name));
    }

    @PostMapping("/registration")
    public ResponseEntity<SensorDTO> saveSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if (sensorService.findByName(sensorMapper.toSensor(sensorDTO).getName()).isPresent()) {
            throw new SensorNotCreatedException("The sensor with this name already exists");
        }
        sensorService.save(sensorMapper.toSensor(sensorDTO));
        return ResponseEntity.ok(sensorDTO);
    }
}
