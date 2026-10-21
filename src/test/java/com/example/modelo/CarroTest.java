package com.example.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    @Test
    void tipoECarro() {
        assertEquals("Carro", new Carro(1, new Matricula("AA-01-BB")).getTipo());
    }

    @Test
    void guardaNumeroEMatricula() {
        Matricula m = new Matricula("AA-02-BB");
        Carro v = new Carro(2, m);
        assertEquals(2, v.getNumero());
        assertSame(m, v.getMatricula());
    }

    @Test
    void eUmVeiculo() {
        assertTrue(new Carro(3, new Matricula("AA-03-BB")) instanceof Veiculo);
    }

    @Test
    void toStringTemOFormatoEsperado() {
        assertEquals("4:Carro(AA-04-BB)", new Carro(4, new Matricula("AA-04-BB")).toString());
    }

    @Test
    void matriculaNulaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Carro(1, null));
    }
}
