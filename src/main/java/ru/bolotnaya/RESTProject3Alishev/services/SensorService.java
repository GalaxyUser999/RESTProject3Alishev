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

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class SensorService {
    SensorRepository sensorRepository;
    SensorMapper sensorMapper;

    public void checkByName(String name) {
        if (sensorRepository.findByName(name).isPresent())
            throw new SensorNotCreatedException("The sensor with this name already exists");
    }

    public Sensor findByName(String name) {
        return sensorRepository.findByName(name)
                .orElseThrow(SensorNotFoundException::new);
    }

    public void save(SensorDTO sensorDTO) {
        Sensor sensor = sensorMapper.toSensor(sensorDTO);
        sensorRepository.save(sensor);
    }


    public SensorDTO getSensorByName(String name) {
        Sensor sensor = sensorRepository.findByName(name).orElseThrow(SensorNotFoundException::new);
        return sensorMapper.toSensorDTO(sensor);
    }

    public List<SensorDTO> getSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensorMapper.toSensorResponseList(sensors);
    }
}
