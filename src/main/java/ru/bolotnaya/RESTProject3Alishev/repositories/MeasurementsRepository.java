package ru.bolotnaya.RESTProject3Alishev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bolotnaya.RESTProject3Alishev.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    long countByRaining(boolean rainy);
}
