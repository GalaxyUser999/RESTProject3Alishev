package ru.bolotnaya.RESTProject3Alishev.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurements")
@NoArgsConstructor
@Data
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "value")
    @NotNull(message = "Enter temperature")
    @Min(value = -100, message = "Temperature can be between -100 and 100")
    @Max(value = 100, message = "Temperature can be between -100 and 100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Enter raining measurements")
    private Boolean raining;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "Enter the sensor")
    private Sensor sensor;
}
