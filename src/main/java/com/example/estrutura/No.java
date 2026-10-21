package com.example.estrutura;

/** Nó de uma lista circular duplamente ligada. Cada nó aponta para um {@link Lugar}. */
public class No {
    private final Lugar lugar;
    private No proximo;
    private No anterior;

    /** @throws IllegalArgumentException se o lugar for nulo */
    public No(Lugar lugar) {
        if (lugar == null) {
            throw new IllegalArgumentException("O nó precisa de um lugar.");
        }
        this.lugar = lugar;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
}
