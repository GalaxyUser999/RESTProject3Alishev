package ru.bolotnaya.RESTProject3Alishev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bolotnaya.RESTProject3Alishev.models.Measurements;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    List<Measurements> findAll();
}
