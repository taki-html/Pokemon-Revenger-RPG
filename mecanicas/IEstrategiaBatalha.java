package mecanicas;

import java.util.Scanner;
import personagem.Personagem;

public interface IEstrategiaBatalha {

    String[] getOpcoesDoJogador();

    /**
     * Define a lógica para o turno do jogador.
     */
    void executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Scanner scanner, Batalha batalha);

    /**
     * Define a lógica para o turno do inimigo.
     */
    void executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha);

    /**
     * Usado no fim da batalha para a mensagem correta.
     */
    boolean isBatalhaImpossivel();
}