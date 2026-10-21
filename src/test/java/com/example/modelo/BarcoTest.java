package com.example.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BarcoTest {

    @Test
    void tipoEBarco() {
        assertEquals("Barco", new Barco(1, new Matricula("AA-01-BB")).getTipo());
    }

    @Test
    void guardaNumeroEMatricula() {
        Matricula m = new Matricula("AA-02-BB");
        Barco v = new Barco(2, m);
        assertEquals(2, v.getNumero());
        assertSame(m, v.getMatricula());
    }

    @Test
    void eUmVeiculo() {
        assertTrue(new Barco(3, new Matricula("AA-03-BB")) instanceof Veiculo);
    }

    @Test
    void toStringTemOFormatoEsperado() {
        assertEquals("4:Barco(AA-04-BB)", new Barco(4, new Matricula("AA-04-BB")).toString());
    }

    @Test
    void matriculaNulaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Barco(1, null));
    }
}
