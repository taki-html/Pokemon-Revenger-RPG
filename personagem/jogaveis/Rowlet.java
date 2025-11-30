package personagem.jogaveis;
import personagem.Jogador;

public class Rowlet extends Jogador {

    private static final String HABILIDADE = "Voo Silencioso";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Rowlet(String nome) {
        super(nome, 15, 3, 3, 3, 1);
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
        
    }

    //Implementa a lógica da habilidade específica do Rowlet.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("janela")) {
            return "Você usa VOO SILENCIOSO e voa até o parapeito da janela sem fazer barulho.";
        } else {
            return "Você tenta usar VOO SILENCIOSO, mas não há para onde voar.";
        }
    }
}