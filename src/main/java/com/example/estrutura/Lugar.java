package com.example.estrutura;

import com.example.modelo.Veiculo;

/**
 * Posição física do puzzle. Guarda um veículo (ou fica vazia).
 * Um mesmo Lugar pode pertencer a dois anéis ao mesmo tempo.
 */
public class Lugar {
    private Veiculo veiculo;

    public Lugar() {
        this(null);
    }

    public Lugar(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean estaVazio() {
        return veiculo == null;
    }
}
