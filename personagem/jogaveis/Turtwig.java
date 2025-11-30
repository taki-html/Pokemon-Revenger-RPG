package personagem.jogaveis;
import personagem.Jogador;

public class Turtwig extends Jogador {

    private static final String HABILIDADE = "Vinhas";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Turtwig(String nome) {
        // super(nome, hpMax, atk, def, agi, nivel)
        super(nome, 15, 3, 3, 3, 1);
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
    }
    
    //Implementa a lógica da habilidade específica do Turtwig.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("prateleira") || alvo.equals("alavanca")) {
            return "Você usa suas [Vinhas] para puxar o objeto distante.";
        } else {
            return "Você tenta usar [Vinhas], mas não funciona aqui.";
        }
    }
}