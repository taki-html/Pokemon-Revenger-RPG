package personagem.jogaveis;
import personagem.Jogador;

public class Piplup extends Jogador {

    private static final String HABILIDADE = "Bicar";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Piplup(String nome) {
        super(nome, 15, 3, 3, 3, 1);
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
    }

    @Override
    protected void aplicarAtributosDeNivel() {
        this.hpMax += 5;
        this.atk += 2;
        this.def += 2; // Balanceado
        this.agi += 1;
        System.out.println("   | (Piplup parece mais orgulhoso e resistente! +DEF)");
    }

    //Implementa a lógica da habilidade específica do Piplup.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("trava fraca") || alvo.equals("gaveta")) {
            return "Você usa BICAR para quebrar a trava fraca. Ela abre!";
        } else {
            return "Você usa BICAR, mas não funciona.";
        }
    }
}