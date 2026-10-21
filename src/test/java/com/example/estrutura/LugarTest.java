package com.example.estrutura;

import com.example.modelo.Carro;
import com.example.modelo.Matricula;
import com.example.modelo.Veiculo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LugarTest {

    private Veiculo carro(int n) {
        return new Carro(n, new Matricula("AA-0" + n + "-BB"));
    }

    @Test
    void lugarSemArgumentosComecaVazio() {
        Lugar l = new Lugar();
        assertTrue(l.estaVazio());
        assertNull(l.getVeiculo());
    }

    @Test
    void lugarComVeiculoNaoEstaVazio() {
        Veiculo v = carro(1);
        Lugar l = new Lugar(v);
        assertFalse(l.estaVazio());
        assertSame(v, l.getVeiculo());
    }

    @Test
    void setVeiculoSubstituiOVeiculo() {
        Lugar l = new Lugar(carro(1));
        Veiculo outro = carro(2);
        l.setVeiculo(outro);
        assertSame(outro, l.getVeiculo());
    }

    @Test
    void setVeiculoNuloEsvaziaOLugar() {
        Lugar l = new Lugar(carro(1));
        l.setVeiculo(null);
        assertTrue(l.estaVazio());
    }
}
