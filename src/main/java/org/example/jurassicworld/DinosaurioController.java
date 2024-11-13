package org.example.jurassicworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DinosaurioController {

    @Autowired
    private TemperatureSensorService temperatureSensorService;

    @Autowired
    private MovimientoSensorService movimientoSensorService;

    @Autowired
    private FrequenciaCardiacaSensorService frequenciaCardiacaSensorService;

    private final Sinks.Many<List<Dinosaurio>> dinosaurioSink = Sinks.many().multicast().onBackpressureBuffer();
    private List<Dinosaurio> dinosaurios = new ArrayList<>();

    @PostConstruct
    public void init() {
        dinosaurios.add(new Dinosaurio("T-Rex", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Triceratops", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Velociraptor", "Carnívoro", "Selva"));
        dinosaurios.add(new Dinosaurio("Diplodocus", "Herbívoro", "Pradera"));
        dinosaurios.add(new Dinosaurio("Pteranodon", "Piscívoro", "Océano"));
        dinosaurios.add(new Dinosaurio("Cruteranodon", "Piscívoro", "Océano"));

        temperatureSensorService.streamTemperatureData(dinosaurios).subscribe();
        movimientoSensorService.streamMovementData().subscribe(mov -> dinosaurios.forEach(dino -> dino.setMovimiento(mov)));
        frequenciaCardiacaSensorService.streamHeartRateData().subscribe(hr -> dinosaurios.forEach(dino -> dino.setFrecuenciaCardiaca(hr)));

        Flux.interval(Duration.ofSeconds(1)).subscribe(tick -> dinosaurioSink.tryEmitNext(dinosaurios));
    }

    @SendTo("/topic/dinosaurios")
    public Flux<List<Dinosaurio>> getDinosaurios() {
        return dinosaurioSink.asFlux();
    }
}
