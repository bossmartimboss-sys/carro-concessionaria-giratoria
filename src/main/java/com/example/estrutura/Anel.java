package com.example.estrutura;

import com.example.modelo.Veiculo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Anel = lista circular duplamente ligada de {@link No}.
 * Percorre-se com for-each: cada elemento é o veículo de um lugar (ou null se vazio).
 */
public class Anel implements Iterable<Veiculo> {
    private final String nome;
    private No cabeca;
    private int tamanho = 0;

    public Anel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    /** Acrescenta um lugar no fim do anel. */
    public void adicionar(Lugar lugar) {
        No novo = new No(lugar);
        if (cabeca == null) {
            cabeca = novo;
            novo.setProximo(novo);
            novo.setAnterior(novo);
        } else {
            No ultimo = cabeca.getAnterior();
            ultimo.setProximo(novo);
            novo.setAnterior(ultimo);
            novo.setProximo(cabeca);
            cabeca.setAnterior(novo);
        }
        tamanho++;
    }

    /**
     * Roda o anel. Positivo = cada veículo avança para o nó seguinte;
     * negativo = recua. Aceita qualquer número (o excesso dá a volta ao anel).
     */
    public void rodar(int passos) {
        if (tamanho < 2) return;
        int p = Math.floorMod(passos, tamanho);
        if (p == 0) return;

        Veiculo[] antigos = getVeiculos().toArray(new Veiculo[0]);
        No n = cabeca;
        for (int i = 0; i < tamanho; i++) {
            n.getLugar().setVeiculo(antigos[Math.floorMod(i - p, tamanho)]);
            n = n.getProximo();
        }
    }

    public void rodarFrente() {
        rodar(1);
    }

    public void rodarTras() {
        rodar(-1);
    }

    /** Veículos pela ordem dos nós, a partir da cabeça. */
    public List<Veiculo> getVeiculos() {
        List<Veiculo> lista = new ArrayList<>();
        for (Veiculo v : this) {
            lista.add(v);
        }
        return lista;
    }

    @Override
    public Iterator<Veiculo> iterator() {
        return new Iterator<>() {
            private No atual = cabeca;
            private int percorridos = 0;

            @Override
            public boolean hasNext() {
                return percorridos < tamanho;
            }

            @Override
            public Veiculo next() {
                if (!hasNext()) throw new NoSuchElementException();
                Veiculo v = atual.getLugar().getVeiculo();
                atual = atual.getProximo();
                percorridos++;
                return v;
            }
        };
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("  ", "Anel " + nome + ": ", "");
        for (Veiculo v : this) {
            sj.add(v == null ? "Vazio" : v.toString());
        }
        return sj.toString();
    }
}
