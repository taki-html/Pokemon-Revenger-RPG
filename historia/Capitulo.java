package historia;

import personagem.Jogador;

public abstract class Capitulo {
    // Controla em qual "parte" do capítulo o jogador está
    protected int etapa = 0;

    /**
     * @param input O texto ou número que o usuário digitou no navegador.
     * @param jogador O objeto do jogador.
     * @return O texto da história para exibir na tela.
     */
    public abstract String processar(String input, Jogador jogador);
    
    // Verifica se o capítulo acabou para passar pro próximo
    public abstract boolean estaFinalizado();
}