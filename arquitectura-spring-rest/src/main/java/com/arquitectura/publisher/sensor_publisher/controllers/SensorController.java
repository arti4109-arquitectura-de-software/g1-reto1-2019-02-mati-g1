package com.arquitectura.publisher.sensor_publisher.controllers;

import com.arquitectura.publisher.sensor_publisher.model.Sensor;
import com.arquitectura.publisher.sensor_publisher.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Envio señales";
    }

    @RequestMapping(value = "/sensors", method = RequestMethod.GET)
    public ResponseEntity<Sensor> list() {
        List<Sensor> sensors = sensorService.list();
        return new ResponseEntity(sensors, HttpStatus.OK);
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public ResponseEntity<Sensor> sensorById(@RequestParam(value = "id") long id) {
        Sensor sensor = sensorService.get(id);
        return new ResponseEntity(sensor, HttpStatus.OK);
    }

    @RequestMapping(value = "/envio", method = RequestMethod.POST)
    public ResponseEntity<Sensor> create(@Valid @RequestBody Sensor sensor) {
        Sensor sensorCreated = sensorService.create(sensor);
        sensorService.SerializeDataKafka(sensor);
        return new ResponseEntity(sensorCreated, HttpStatus.CREATED);
    }

}
