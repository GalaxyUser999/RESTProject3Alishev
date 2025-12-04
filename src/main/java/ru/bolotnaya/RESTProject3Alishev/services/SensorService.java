package ru.bolotnaya.RESTProject3Alishev.services;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorDTO;
import ru.bolotnaya.RESTProject3Alishev.DTO.SensorMapper;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;
import ru.bolotnaya.RESTProject3Alishev.repositories.SensorRepository;
import ru.bolotnaya.RESTProject3Alishev.utils.SensorNotCreatedException;
import ru.bolotnaya.RESTProject3Alishev.utils.SensorNotFoundException;

import java.util.List;

import static ru.bolotnaya.RESTProject3Alishev.utils.SensorNotCreatedException.sensorNotCreatedException;
import static ru.bolotnaya.RESTProject3Alishev.utils.SensorNotFoundException.sensorNotFoundException;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class SensorService {
    SensorRepository sensorRepository;
    SensorMapper sensorMapper;

    public void checkByName(String name) {
        sensorRepository.findByName(name).orElseThrow(sensorNotCreatedException("The sensor \"{0}\" already exists", name));
    }

    public Sensor findByName(String name) {
        return sensorRepository.findByName(name)
                .orElseThrow(sensorNotFoundException("The sensor \"{0}\" has not been found", name));
    }

    public void save(SensorDTO sensorDTO) {
        Sensor sensor = sensorMapper.toSensor(sensorDTO);
        sensorRepository.save(sensor);
    }


    public SensorDTO getSensorByName(String name) {
        Sensor sensor = sensorRepository.findByName(name).orElseThrow(sensorNotFoundException("The sensor \"{0}\" has not been found", name));
        return sensorMapper.toSensorDTO(sensor);
    }

    public List<SensorDTO> getSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensorMapper.toSensorResponseList(sensors);
    }
}
