package org.example.jurassicworld;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;

@Service
public class TemperatureSensorService {

    public Flux<Double> streamTemperatureData(List<Dinosaurio> dinosaurios) {
        return Flux.interval(Duration.ofSeconds(5)) // Increase interval to 5 seconds
                .map(tick -> {
                    double temp = 20 + Math.random() * 15;
                    dinosaurios.forEach(dino -> dino.setTemperatura(temp));
                    return temp;
                });
    }
}