package personagem.jogaveis;
import personagem.Jogador;


public class Charmander extends Jogador {

    private static final String HABILIDADE = "Garras Ágeis";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Charmander(String nome) {
        super(nome, 15, 3, 3, 3, 1);
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
    }

    //Implementa a lógica da habilidade específica do Charmander.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("gaveta")) {
            return "Você usa GARRAS ÁGEIS para forçar a gaveta emperrada. Ela abre!";
        } else if (alvo.equals("corda")) {
            return "Você usa GARRAS ÁGEIS para cortar a corda. Elas se rompem!";
        } else {
            return "Você tenta usar GARRAS ÁGEIS, mas sem sucesso";
        }
    }
}