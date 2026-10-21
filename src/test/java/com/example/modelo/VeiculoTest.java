package com.example.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    private Veiculo criar(int numero, Matricula m) {
        return new Veiculo(numero, m) {
            @Override
            public String getTipo() {
                return "Teste";
            }
        };
    }

    @Test
    void guardaNumeroEMatricula() {
        Matricula m = new Matricula("AA-11-BB");
        Veiculo v = criar(5, m);
        assertEquals(5, v.getNumero());
        assertSame(m, v.getMatricula());
    }

    @Test
    void toStringTemNumeroTipoEMatricula() {
        assertEquals("5:Teste(AA-11-BB)", criar(5, new Matricula("AA-11-BB")).toString());
    }

    @Test
    void matriculaNulaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> criar(1, null));
    }
}
