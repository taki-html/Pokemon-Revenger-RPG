package historia;
import java.util.Scanner;

import personagem.Jogador;

public interface Capitulo {
    /**
     * Executa a lógica do capítulo.
     * @param jogador O objeto Jogador, que será modificado pelo capítulo.
     * @param scanner O Scanner para ler a entrada do usuário.
     * @return Um objeto ResultadoCapitulo indicando se o jogo continua e qualquer mensagem final.
     */
    ResultadoCapitulo executar(Jogador jogador, Scanner scanner);
}