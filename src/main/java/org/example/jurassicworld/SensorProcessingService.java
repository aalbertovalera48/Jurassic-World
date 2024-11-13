package org.example.jurassicworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@Service
public class SensorProcessingService {

    private final TemperatureSensorService temperatureSensorService;

    @Autowired
    public SensorProcessingService(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;
    }

    public void processTemperatureData() {
        temperatureSensorService.streamTemperatureData()
                .subscribeOn(Schedulers.parallel())
                .subscribe(sensor -> System.out.println("Processing temperature: " + sensor.byteValue()));
    }
}
