package com.arquitectura.publisher.sensor_publisher1.service;

import com.arquitectura.publisher.sensor_publisher1.model.Sensor;
import com.arquitectura.publisher.sensor_publisher1.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class SensorService {

    @Autowired
    private SensorRepository repository;

    public Sensor get(long sensorId) {
        return repository.findOne(sensorId);
    }

    public List<Sensor> list() {
        Iterable<Sensor> sensors = repository.findAll();
        List<Sensor> list = new ArrayList<Sensor>();
        sensors.forEach(list::add);
        return list;
    }

    public Sensor create(Sensor sensor) {
        return repository.save(sensor);
    }


    public void SerializeDataKafka (Sensor sensor){

        Properties props = new Properties();
        props.put("bootstrap.servers", "35.226.244.197:9093");
        //props.put("bootstrap.servers", "10.128.0.6:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        long inicio = System.currentTimeMillis();
        System.out.println();
        for(int i = 1; i <= 5000; i++) {
            log.info("Generating event #" + i);

            String key = extractKey(sensor);
            String value = extractValue(sensor);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("sensors-tracking", key, value);

            log.info("Producing to Kafka the record: " + key + ":" + value);
            producer.send(producerRecord);

            //sleep(1000);
        }
        long fin = System.currentTimeMillis();
        long tiempo =  inicio -fin;
        System.out.println(tiempo);
        producer.close();
    }

    private static String extractKey(Sensor sensor) {
        return sensor.getId().toString();
    }

    private static String extractValue(Sensor sensor) {
        return String.format("%s,%s,%s,%s", sensor.getSensorName(), sensor.getSensorDescr(), sensor.getSegments(), sensor.getFecha());
    }
}
