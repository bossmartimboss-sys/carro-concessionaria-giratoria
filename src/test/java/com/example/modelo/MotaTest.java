package com.example.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MotaTest {

    @Test
    void tipoEMota() {
        assertEquals("Mota", new Mota(1, new Matricula("AA-01-BB")).getTipo());
    }

    @Test
    void guardaNumeroEMatricula() {
        Matricula m = new Matricula("AA-02-BB");
        Mota v = new Mota(2, m);
        assertEquals(2, v.getNumero());
        assertSame(m, v.getMatricula());
    }

    @Test
    void eUmVeiculo() {
        assertTrue(new Mota(3, new Matricula("AA-03-BB")) instanceof Veiculo);
    }

    @Test
    void toStringTemOFormatoEsperado() {
        assertEquals("4:Mota(AA-04-BB)", new Mota(4, new Matricula("AA-04-BB")).toString());
    }

    @Test
    void matriculaNulaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Mota(1, null));
    }
}
