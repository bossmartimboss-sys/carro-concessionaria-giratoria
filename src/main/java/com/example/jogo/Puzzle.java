package com.example.jogo;

import com.example.estrutura.Anel;
import com.example.estrutura.Lugar;
import com.example.modelo.Barco;
import com.example.modelo.Carro;
import com.example.modelo.Matricula;
import com.example.modelo.Mota;
import com.example.modelo.Veiculo;

import java.util.Random;

/**
 * Puzzle com 3 anéis e 10 lugares (veículos numerados de 1 a 10).
 * A: lugares 0,1,2,3   B: lugares 3,4,5,6   C: lugares 6,7,8,9,0
 * Os lugares 3, 6 e 0 são partilhados entre dois anéis.
 * Objetivo: o veículo n no lugar n-1 (ordem 1..10).
 */
public class Puzzle {
    private final Lugar[] lugares = new Lugar[10];
    private final Anel[] aneis = new Anel[3];
    private int jogadas = 0;

    public Puzzle() {
        for (int i = 0; i < lugares.length; i++) {
            int n = i + 1;
            Matricula m = new Matricula(String.format("AA-%02d-BB", n));
            Veiculo v;
            if (i % 3 == 0)      v = new Carro(n, m);
            else if (i % 3 == 1) v = new Mota(n, m);
            else                 v = new Barco(n, m);
            lugares[i] = new Lugar(v);
        }

        aneis[0] = criarAnel("A", 0, 1, 2, 3);
        aneis[1] = criarAnel("B", 3, 4, 5, 6);
        aneis[2] = criarAnel("C", 6, 7, 8, 9, 0);
    }

    private Anel criarAnel(String nome, int... indices) {
        Anel a = new Anel(nome);
        for (int i : indices) {
            a.adicionar(lugares[i]);
        }
        return a;
    }

    public Anel getAnel(int indice) {
        return aneis[indice];
    }

    public int getJogadas() {
        return jogadas;
    }

    /**
     * Roda um anel e conta a jogada.
     *
     * @param nomeAnel 'A', 'B' ou 'C' (maiúscula ou minúscula)
     * @param frente   true = avança, false = recua
     * @return false se o nome do anel for inválido (nada acontece)
     */
    public boolean rodar(char nomeAnel, boolean frente) {
        int idx = "ABC".indexOf(Character.toUpperCase(nomeAnel));
        if (idx < 0) return false;
        aplicar(idx, frente);
        jogadas++;
        return true;
    }

    /**
     * Interpreta um comando de texto como "A+" ou "b-".
     *
     * @return true se o comando era válido e foi executado
     */
    public boolean executar(String comando) {
        if (comando == null) return false;
        String c = comando.trim();
        if (c.length() != 2) return false;
        char sinal = c.charAt(1);
        if (sinal != '+' && sinal != '-') return false;
        return rodar(c.charAt(0), sinal == '+');
    }

    /** Faz movimentos aleatórios (sem contar jogadas). Como cada rotação é reversível, tem sempre solução. */
    public void embaralhar(int movimentos, Random random) {
        if (movimentos < 0) {
            throw new IllegalArgumentException("O número de movimentos não pode ser negativo.");
        }
        for (int i = 0; i < movimentos; i++) {
            aplicar(random.nextInt(aneis.length), random.nextBoolean());
        }
        jogadas = 0;
    }

    /** true se o veículo n estiver no lugar n-1, para todos. */
    public boolean estaResolvido() {
        for (int i = 0; i < lugares.length; i++) {
            Veiculo v = lugares[i].getVeiculo();
            if (v == null || v.getNumero() != i + 1) return false;
        }
        return true;
    }

    private void aplicar(int indiceAnel, boolean frente) {
        aneis[indiceAnel].rodar(frente ? 1 : -1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aneis.length; i++) {
            if (i > 0) sb.append(System.lineSeparator());
            sb.append(aneis[i]);
        }
        return sb.toString();
    }
}
