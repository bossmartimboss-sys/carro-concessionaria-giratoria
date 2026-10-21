package com.example.modelo;

/** Mota: um tipo de veículo do puzzle. */
public class Mota extends Veiculo {

    public Mota(int numero, Matricula matricula) {
        super(numero, matricula);
    }

    @Override
    public String getTipo() {
        return "Mota";
    }
}
