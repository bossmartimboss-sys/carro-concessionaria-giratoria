package com.example.modelo;

/** Classe base de todos os veículos do puzzle (Carro, Mota, Barco). */
public abstract class Veiculo {
    private final int numero;
    private final Matricula matricula;

    /**
     * @param numero    número do veículo no puzzle (1 a 10)
     * @param matricula matrícula do veículo
     * @throws IllegalArgumentException se a matrícula for nula
     */
    protected Veiculo(int numero, Matricula matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("O veículo precisa de uma matrícula.");
        }
        this.numero = numero;
        this.matricula = matricula;
    }

    public int getNumero() {
        return numero;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    /** Nome do tipo de veículo (ex.: "Carro"). */
    public abstract String getTipo();

    @Override
    public String toString() {
        return numero + ":" + getTipo() + "(" + matricula + ")";
    }
}
