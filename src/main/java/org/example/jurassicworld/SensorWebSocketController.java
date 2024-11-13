package org.example.jurassicworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class SensorWebSocketController {

    @Autowired
    private TemperatureSensorService temperatureSensorService;

    @Autowired
    private MovimientoSensorService movimientoSensorService;

    @Autowired
    private FrequenciaCardiacaSensorService frequenciaCardiacaSensorService;

    private final Sinks.Many<Double> temperatureSink = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<String> movementSink = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<Integer> heartRateSink = Sinks.many().multicast().onBackpressureBuffer();

    public SensorWebSocketController() {
        temperatureSensorService.streamTemperatureData().subscribe(temperatureSink::tryEmitNext);
        movimientoSensorService.streamMovementData().subscribe(movementSink::tryEmitNext);
        frequenciaCardiacaSensorService.streamHeartRateData().subscribe(heartRateSink::tryEmitNext);
    }

    @SendTo("/topic/temperature")
    public Flux<Double> getTemperatureData() {
        return temperatureSink.asFlux();
    }

    @SendTo("/topic/movement")
    public Flux<String> getMovimientoData() {
        return movementSink.asFlux();
    }

    @SendTo("/topic/heartRate")
    public Flux<Integer> getFrecuenciaCardiacaData() {
        return heartRateSink.asFlux();
    }
}