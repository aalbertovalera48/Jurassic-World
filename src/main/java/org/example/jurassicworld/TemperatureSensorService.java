package org.example.jurassicworld;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Service
public class TemperatureSensorService {

    public Flux<Double> streamTemperatureData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> 20 + Math.random() * 15) // Datos aleatorios de temperatura
                .doOnNext(temp -> System.out.println("Temperatura: " + temp));
    }
}
