package com.example.jogo;

import com.example.estrutura.Anel;
import com.example.modelo.Veiculo;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    private int[] numeros(Anel a) {
        int[] r = new int[a.getTamanho()];
        int i = 0;
        for (Veiculo v : a) r[i++] = v.getNumero();
        return r;
    }

    @Test
    void estadoInicial() {
        Puzzle p = new Puzzle();
        assertArrayEquals(new int[]{1, 2, 3, 4}, numeros(p.getAnel(0)));
        assertArrayEquals(new int[]{4, 5, 6, 7}, numeros(p.getAnel(1)));
        assertArrayEquals(new int[]{7, 8, 9, 10, 1}, numeros(p.getAnel(2)));
        assertEquals(0, p.getJogadas());
    }

    @Test
    void temTresAneisComTamanhosCertos() {
        Puzzle p = new Puzzle();
        assertEquals(4, p.getAnel(0).getTamanho());
        assertEquals(4, p.getAnel(1).getTamanho());
        assertEquals(5, p.getAnel(2).getTamanho());
    }

    @Test
    void osTiposDosVeiculosSaoMisturados() {
        Puzzle p = new Puzzle();
        Veiculo[] a = p.getAnel(0).getVeiculos().toArray(new Veiculo[0]);
        assertEquals("Carro", a[0].getTipo());
        assertEquals("Mota", a[1].getTipo());
        assertEquals("Barco", a[2].getTipo());
    }

    @Test
    void rodarAnelAAfetaOsAneisBeC() {
        Puzzle p = new Puzzle();
        assertTrue(p.rodar('A', true));
        assertArrayEquals(new int[]{4, 1, 2, 3}, numeros(p.getAnel(0)));
        assertArrayEquals(new int[]{3, 5, 6, 7}, numeros(p.getAnel(1)));      // lugar 3 partilhado
        assertArrayEquals(new int[]{7, 8, 9, 10, 4}, numeros(p.getAnel(2)));  // lugar 0 partilhado
    }

    @Test
    void aceitaLetrasMinusculas() {
        Puzzle p = new Puzzle();
        assertTrue(p.rodar('b', false));
        assertArrayEquals(new int[]{5, 6, 7, 4}, numeros(p.getAnel(1)));
    }

    @Test
    void anelInvalidoDevolveFalseENaoAlteraNada() {
        Puzzle p = new Puzzle();
        assertFalse(p.rodar('Z', true));
        assertArrayEquals(new int[]{1, 2, 3, 4}, numeros(p.getAnel(0)));
        assertEquals(0, p.getJogadas());
    }

    @Test
    void cadaRotacaoValidaContaUmaJogada() {
        Puzzle p = new Puzzle();
        p.rodar('A', true);
        p.rodar('B', false);
        p.rodar('X', true);   // inválida, não conta
        assertEquals(2, p.getJogadas());
    }

    @Test
    void osDezVeiculosContinuamTodosPresentesDepoisDeRodar() {
        Puzzle p = new Puzzle();
        p.rodar('A', true);
        p.rodar('B', false);
        p.rodar('C', true);
        p.rodar('A', false);
        assertTodosPresentes(p);
    }

    @Test
    void estaResolvidoNoInicioEDeixaDeEstarDepoisDeRodar() {
        Puzzle p = new Puzzle();
        assertTrue(p.estaResolvido());
        p.rodar('A', true);
        assertFalse(p.estaResolvido());
        p.rodar('A', false);
        assertTrue(p.estaResolvido());
    }

    @Test
    void embaralharMantemOsVeiculosEZeraAsJogadas() {
        Puzzle p = new Puzzle();
        p.rodar('A', true);
        p.embaralhar(30, new Random(7));
        assertEquals(0, p.getJogadas());
        assertTodosPresentes(p);
    }

    @Test
    void embaralharComNumeroNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Puzzle().embaralhar(-1, new Random()));
    }

    @Test
    void executarAceitaComandosValidos() {
        Puzzle p = new Puzzle();
        assertTrue(p.executar("A+"));
        assertTrue(p.executar("  b- "));
        assertEquals(2, p.getJogadas());
    }

    @Test
    void executarRejeitaComandosInvalidos() {
        Puzzle p = new Puzzle();
        assertFalse(p.executar(null));
        assertFalse(p.executar(""));
        assertFalse(p.executar("A"));
        assertFalse(p.executar("AA"));
        assertFalse(p.executar("A*"));
        assertFalse(p.executar("Z+"));
        assertFalse(p.executar("A++"));
        assertEquals(0, p.getJogadas());
    }

    @Test
    void toStringTemUmaLinhaPorAnel() {
        String[] linhas = new Puzzle().toString().split(System.lineSeparator());
        assertEquals(3, linhas.length);
        assertTrue(linhas[0].startsWith("Anel A: "));
        assertTrue(linhas[1].startsWith("Anel B: "));
        assertTrue(linhas[2].startsWith("Anel C: "));
    }

    private void assertTodosPresentes(Puzzle p) {
        boolean[] vistos = new boolean[11];
        for (int i = 0; i < 3; i++)
            for (Veiculo v : p.getAnel(i)) vistos[v.getNumero()] = true;
        for (int n = 1; n <= 10; n++) assertTrue(vistos[n], "falta o veículo " + n);
    }
}
