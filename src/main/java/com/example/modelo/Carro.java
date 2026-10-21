package com.example.modelo;

/** Carro: um tipo de veículo do puzzle. */
public class Carro extends Veiculo {

    public Carro(int numero, Matricula matricula) {
        super(numero, matricula);
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}
