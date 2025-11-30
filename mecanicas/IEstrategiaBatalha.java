package mecanicas;

import personagem.Personagem;

public interface IEstrategiaBatalha {

    String[] getOpcoesDoJogador();

    // Retorna a narrativa do turno (ex: "Você atacou e causou 10 de dano!")
    String executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Batalha batalha);

    // Retorna a narrativa do turno do inimigo
    String executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha);

    boolean isBatalhaImpossivel();
}