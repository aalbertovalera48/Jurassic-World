package org.example.jurassicworld;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureSensor {
    private String id;
    private double temperature;

    public TemperatureSensor(String id, double temperature) {
        this.id = id;
        this.temperature = temperature;
    }
}