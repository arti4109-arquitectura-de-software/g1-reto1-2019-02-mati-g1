package com.arquitectura.publisher.sensor_publisher.repository;

import com.arquitectura.publisher.sensor_publisher.model.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

}
