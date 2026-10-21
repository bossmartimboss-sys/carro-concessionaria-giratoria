package com.example.estrutura;

import com.example.modelo.Barco;
import com.example.modelo.Carro;
import com.example.modelo.Matricula;
import com.example.modelo.Mota;
import com.example.modelo.Veiculo;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class AnelTest {

    /** Anel com n lugares, com os veículos numerados 1..n. */
    private Anel criar(int n) {
        Anel a = new Anel("X");
        for (int i = 1; i <= n; i++) {
            a.adicionar(new Lugar(new Carro(i, new Matricula("AA-0" + i + "-BB"))));
        }
        return a;
    }

    private int[] numeros(Anel a) {
        int[] r = new int[a.getTamanho()];
        int i = 0;
        for (Veiculo v : a) r[i++] = v.getNumero();
        return r;
    }

    @Test
    void anelNovoEstaVazio() {
        Anel a = new Anel("A");
        assertEquals("A", a.getNome());
        assertEquals(0, a.getTamanho());
        assertTrue(a.getVeiculos().isEmpty());
        assertFalse(a.iterator().hasNext());
    }

    @Test
    void adicionarAumentaOTamanhoEMantemAOrdem() {
        Anel a = criar(4);
        assertEquals(4, a.getTamanho());
        assertArrayEquals(new int[]{1, 2, 3, 4}, numeros(a));
    }

    @Test
    void adicionarLugarNuloLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Anel("A").adicionar(null));
    }

    @Test
    void rodarFrenteAvancaCadaVeiculo() {
        Anel a = criar(4);
        a.rodarFrente();
        assertArrayEquals(new int[]{4, 1, 2, 3}, numeros(a));
    }

    @Test
    void rodarTrasRecuaCadaVeiculo() {
        Anel a = criar(4);
        a.rodarTras();
        assertArrayEquals(new int[]{2, 3, 4, 1}, numeros(a));
    }

    @Test
    void rodarVariosPassosDeUmaVez() {
        Anel a = criar(4);
        a.rodar(2);
        assertArrayEquals(new int[]{3, 4, 1, 2}, numeros(a));
    }

    @Test
    void passosNegativosRecuam() {
        Anel a = criar(4);
        a.rodar(-1);
        assertArrayEquals(new int[]{2, 3, 4, 1}, numeros(a));
    }

    @Test
    void passosMaioresQueOTamanhoDaoAVolta() {
        Anel a = criar(4);
        a.rodar(5);   // igual a rodar 1
        assertArrayEquals(new int[]{4, 1, 2, 3}, numeros(a));
    }

    @Test
    void zeroPassosNaoAlteraNada() {
        Anel a = criar(4);
        a.rodar(0);
        assertArrayEquals(new int[]{1, 2, 3, 4}, numeros(a));
    }

    @Test
    void frenteSeguidaDeTrasDeixaTudoIgual() {
        Anel a = criar(5);
        a.rodarFrente();
        a.rodarTras();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, numeros(a));
    }

    @Test
    void rodarTantasVezesQuantoOTamanhoVoltaAoInicio() {
        Anel a = criar(4);
        for (int i = 0; i < 4; i++) a.rodarFrente();
        assertArrayEquals(new int[]{1, 2, 3, 4}, numeros(a));
    }

    @Test
    void rodarAnelVazioOuComUmLugarNaoFazNada() {
        Anel vazio = new Anel("V");
        assertDoesNotThrow(vazio::rodarFrente);
        assertDoesNotThrow(vazio::rodarTras);

        Anel um = criar(1);
        um.rodarFrente();
        um.rodarTras();
        assertArrayEquals(new int[]{1}, numeros(um));
    }

    @Test
    void lugarPartilhadoPassaOVeiculoParaOOutroAnel() {
        Lugar partilhado = new Lugar(new Carro(1, new Matricula("AA-01-BB")));
        Lugar l2 = new Lugar(new Mota(2, new Matricula("AA-02-BB")));
        Lugar l3 = new Lugar(new Barco(3, new Matricula("AA-03-BB")));

        Anel a = new Anel("A");
        a.adicionar(l2);
        a.adicionar(partilhado);

        Anel b = new Anel("B");
        b.adicionar(partilhado);
        b.adicionar(l3);

        a.rodarFrente();   // o veículo 2 passa para o lugar partilhado
        assertEquals(2, b.getVeiculos().get(0).getNumero());
    }

    @Test
    void iteradorEsgotadoLancaExcecao() {
        Iterator<Veiculo> it = criar(2).iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void toStringMostraONomeEOsVeiculos() {
        assertEquals("Anel X: 1:Carro(AA-01-BB)  2:Carro(AA-02-BB)", criar(2).toString());
    }

    @Test
    void toStringMostraVazioQuandoNaoHaVeiculo() {
        Anel a = new Anel("V");
        a.adicionar(new Lugar());
        assertEquals("Anel V: Vazio", a.toString());
    }
}
