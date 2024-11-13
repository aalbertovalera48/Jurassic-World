package org.example.jurassicworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TemperatureController {

    private final TemperatureSensorService temperatureSensorService;

    public TemperatureController(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;
    }

    @GetMapping(value = "/temperature-stream", produces = "application/stream+json")
    public Flux<Double> streamTemperatures() {
        return temperatureSensorService.streamTemperatureData();
    }
}
