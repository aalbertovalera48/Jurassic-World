package org.example.jurassicworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TemperatureController {

    private final TemperatureSensorService temperatureSensorService;

    public TemperatureController(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;
    }

    @GetMapping(value = "/temperature-stream", produces = "application/stream+json")
    public Flux<Double> streamTemperatures() {
        List<Dinosaurio> dinosaurios = new ArrayList<>();
        dinosaurios.add(new Dinosaurio("T-Rex", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Triceratops", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Velociraptor", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Diplodocus", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Pteranodon", "Piscívoro", "Océano"));
        dinosaurios.add(new Dinosaurio("Cruteranodon", "Piscívoro", "Océano"));

        return temperatureSensorService.streamTemperatureData(dinosaurios);
    }
}