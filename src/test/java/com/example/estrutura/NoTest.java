package com.example.estrutura;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoTest {

    @Test
    void guardaOLugar() {
        Lugar l = new Lugar();
        assertSame(l, new No(l).getLugar());
    }

    @Test
    void ligacoesComecamNulas() {
        No n = new No(new Lugar());
        assertNull(n.getProximo());
        assertNull(n.getAnterior());
    }

    @Test
    void ligaProximoEAnterior() {
        No a = new No(new Lugar());
        No b = new No(new Lugar());
        a.setProximo(b);
        b.setAnterior(a);
        assertSame(b, a.getProximo());
        assertSame(a, b.getAnterior());
    }

    @Test
    void lugarNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new No(null));
    }
}
