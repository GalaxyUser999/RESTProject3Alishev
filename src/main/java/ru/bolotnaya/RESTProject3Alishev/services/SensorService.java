package ru.bolotnaya.RESTProject3Alishev.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;
import ru.bolotnaya.RESTProject3Alishev.repositories.SensorRepository;
import ru.bolotnaya.RESTProject3Alishev.util.SensorNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name){
        //toDo
        return sensorRepository.findByName(name);
    }

    public Sensor findByNameWithException(String name){
        return sensorRepository.findByName(name).orElseThrow(SensorNotFoundException::new);
    }



    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
