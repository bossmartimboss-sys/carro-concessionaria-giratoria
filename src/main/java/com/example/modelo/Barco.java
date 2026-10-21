package com.example.modelo;

/** Barco: um tipo de veículo do puzzle. */
public class Barco extends Veiculo {

    public Barco(int numero, Matricula matricula) {
        super(numero, matricula);
    }

    @Override
    public String getTipo() {
        return "Barco";
    }
}
