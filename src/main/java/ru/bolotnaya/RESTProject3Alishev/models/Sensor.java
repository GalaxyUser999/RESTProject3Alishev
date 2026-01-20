package ru.bolotnaya.RESTProject3Alishev.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "Sensor")
@NoArgsConstructor
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    @Size(min = 3, max = 30, message = "The sensor's name should be between 3 and 30")
    @NotEmpty(message = "Enter the sensor's name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
}
