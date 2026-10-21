package com.example.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    @Test
    void guardaOCodigo() {
        assertEquals("AA-11-BB", new Matricula("AA-11-BB").getCodigo());
    }

    @Test
    void toStringDevolveOCodigo() {
        assertEquals("AA-11-BB", new Matricula("AA-11-BB").toString());
    }

    @Test
    void codigoNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Matricula(null));
    }

    @Test
    void codigoVazioLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Matricula("   "));
    }

    @Test
    void matriculasComOMesmoCodigoSaoIguais() {
        assertEquals(new Matricula("AA-11-BB"), new Matricula("AA-11-BB"));
        assertEquals(new Matricula("AA-11-BB").hashCode(), new Matricula("AA-11-BB").hashCode());
        assertNotEquals(new Matricula("AA-11-BB"), new Matricula("CC-22-DD"));
    }
}
