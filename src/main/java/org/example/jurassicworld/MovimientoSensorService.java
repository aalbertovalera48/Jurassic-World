package org.example.jurassicworld;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Service
public class MovimientoSensorService {

    public Flux<String> streamMovementData() {
        return Flux.interval(Duration.ofSeconds(5)) // Increase interval to 5 seconds
                .map(tick -> "Movimiento detectado en sector " + (int)(Math.random() * 10))
                .doOnNext(movement -> System.out.println("Movimiento: " + movement));
    }
}