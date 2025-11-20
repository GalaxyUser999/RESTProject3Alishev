package ru.bolotnaya.RESTProject3Alishev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bolotnaya.RESTProject3Alishev.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
//    List<List<Measurements>> findMeasurements();

}
