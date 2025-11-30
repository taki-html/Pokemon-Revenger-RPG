package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utilitária com métodos estáticos para o jogo.
 */
public class Util {

    // Pausa a execução por um número de segundos.
    public static void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //Aguardar o usuário pressionar ENTER
    public static void aguardarEnter(Scanner scanner) {
    scanner.nextLine();
    }

    // Limpa a tela
    public static void limparTela() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static int obterEscolha(Scanner scanner, String prompt, String... opcoes) {
        System.out.println("\n" + prompt);
        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf("%d. [%s]\n", (i + 1), opcoes[i]);
        }

        int escolha = -1;
        while (escolha < 1 || escolha > opcoes.length) {
            System.out.print("Sua escolha: ");
            try {
                escolha = scanner.nextInt();
                if (escolha < 1 || escolha > opcoes.length) {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
        scanner.nextLine();
        return escolha;
    }
}