package com.example.modelo;

/** Matrícula de um veículo. Imutável e nunca vazia. */
public class Matricula {
    private final String codigo;

    /**
     * @param codigo texto da matrícula (ex.: "AA-01-BB")
     * @throws IllegalArgumentException se for nulo ou vazio
     */
    public Matricula(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("A matrícula não pode ser vazia.");
        }
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;
        return codigo.equals(((Matricula) o).codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return codigo;
    }
}
