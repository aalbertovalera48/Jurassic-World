package org.example.jurassicworld;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Service
public class FrequenciaCardiacaSensorService {

    public Flux<Integer> streamHeartRateData() {
        return Flux.interval(Duration.ofSeconds(5)) // Increase interval to 5 seconds
                .map(tick -> 60 + (int)(Math.random() * 40)) // Frecuencia cardíaca aleatoria
                .doOnNext(rate -> System.out.println("Frecuencia cardíaca: " + rate));
    }
}