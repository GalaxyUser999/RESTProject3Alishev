package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorDTO;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorMapper;
import ru.bolotnaya.RESTProject3Alishev.services.SensorService;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
@FieldDefaults(makeFinal=true)
public class SensorController {
     SensorService sensorService;

    @GetMapping
    public List<SensorDTO> getSensors(){
        return sensorService.getSensors();
    }

    @GetMapping("/{name}")
    public SensorDTO getSensorByName(@PathVariable String name){
        return sensorService.getSensorByName(name);
    }

    @PostMapping("/registration")
    public ResponseEntity<SensorDTO> saveSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorService.checkByName(sensorDTO.getName());
        sensorService.save(sensorDTO);
        return ResponseEntity.ok(sensorDTO);
    }
}
