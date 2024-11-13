package org.example.jurassicworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorProcessingService {

    private final TemperatureSensorService temperatureSensorService;

    @Autowired
    public SensorProcessingService(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;
    }

    public void processTemperatureData() {
        List<Dinosaurio> dinosaurios = new ArrayList<>();
        dinosaurios.add(new Dinosaurio("T-Rex", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Triceratops", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Velociraptor", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Diplodocus", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Pteranodon", "Piscívoro", "Océano"));
        dinosaurios.add(new Dinosaurio("Cruteranodon", "Piscívoro", "Océano"));

        temperatureSensorService.streamTemperatureData(dinosaurios)
                .subscribeOn(Schedulers.parallel())
                .subscribe(sensor -> System.out.println("Processing temperature: " + sensor.byteValue()));
    }
}