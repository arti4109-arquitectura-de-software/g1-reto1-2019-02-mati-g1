package com.arquitectura.publisher.sensor_publisher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("sensor_name")
    @NotBlank
    private String sensorName;

    @JsonProperty("sensor_descr")
    @NotBlank
    private String sensorDescr;

    @JsonProperty("cantid")
    @NotNull
    private Integer cantidad;

    @JsonProperty("segments")
    @NotNull
    private String segments;

    @JsonProperty("fecha")
    private Date fecha;

    public Long getId() {
        return id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getSensorDescr() {
        return sensorDescr;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public void setSensorDescr(String sensorDescr) {
        this.sensorDescr = sensorDescr;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSegments() {
        return segments;
    }

    public void setSegments(String segments) {
        this.segments = segments;
    }

    public Date getFecha() {
        Date date= new Date();
        long time = date.getTime();
        System.out.println("Time in Milliseconds: " + time);
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: " + ts);
        return ts;
    }

    public void setFecha(Date fecha) {
        this.fecha = new Date();
    }

}
