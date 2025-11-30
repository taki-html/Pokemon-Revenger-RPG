package personagem.jogaveis;
import personagem.Jogador;

public class Froakie extends Jogador {

    private static final String HABILIDADE = "Aderência";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Froakie(String nome) {
        super(nome, 15, 3, 3, 3, 1);
    }

    @Override
    protected void aplicarAtributosDeNivel() {
        this.hpMax += 4;
        this.atk += 2;
        this.def += 1;
        this.agi += 3; // Foco em Agilidade
        System.out.println("   | (Froakie se move com mais rapidez! +AGI)");
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
    }
    
    //Implementa a lógica da habilidade específica do Frokie.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("janela") || alvo.equals("parede")) {
            return "Você usa ADERÊNCIA para escalar a parede e alcançar o parapeito da janela.";
        } else {
            return "Você usa ADERÊNCIA, mas sem sucesso";
        }
    }
}