package com.arquitectura.publisher.sensor_publisher1.repository;

import com.arquitectura.publisher.sensor_publisher1.model.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

}
