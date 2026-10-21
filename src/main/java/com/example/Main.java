package com.example;

import com.example.jogo.Puzzle;

import java.util.Random;
import java.util.Scanner;

/** Interface de consola do puzzle. Só trata de ler e mostrar; a lógica está em {@link Puzzle}. */
public class Main {
    private static final int MOVIMENTOS_EMBARALHAR = 20;

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        Random random = new Random();
        puzzle.embaralhar(MOVIMENTOS_EMBARALHAR, random);

        Scanner sc = new Scanner(System.in);
        System.out.println("Objetivo: veículos pela ordem 1..10 nos lugares 0 a 9.");

        while (true) {
            System.out.println(puzzle);
            System.out.println("Jogadas: " + puzzle.getJogadas());

            if (puzzle.estaResolvido()) {
                System.out.println("Parabéns! Resolvido em " + puzzle.getJogadas() + " jogadas.");
                break;
            }

            System.out.print("Comando (A+ A- B+ B- C+ C-, 'embaralhar', 'sair'): ");
            if (!sc.hasNextLine()) break;
            String in = sc.nextLine().trim();

            if (in.equalsIgnoreCase("sair")) {
                break;
            } else if (in.equalsIgnoreCase("embaralhar")) {
                puzzle.embaralhar(MOVIMENTOS_EMBARALHAR, random);
            } else if (!puzzle.executar(in)) {
                System.out.println("Comando inválido.");
            }
            System.out.println();
        }
    }
}
