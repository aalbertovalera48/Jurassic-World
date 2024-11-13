package org.example.jurassicworld;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dinosaurio {
    private String nombre;
    private String tipo;
    private String habitat;
    private double temperatura;
    private String movimiento;
    private int frecuenciaCardiaca;

    public Dinosaurio(String nombre, String tipo, String habitat) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.habitat = habitat;
    }
}
