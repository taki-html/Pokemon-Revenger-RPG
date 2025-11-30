package personagem.jogaveis;
import personagem.Jogador;

public class Tepig extends Jogador {

    private static final String HABILIDADE = "Faro Aguçado";

    //Construtor: Define os status base e chama o construtor da classe pai (Jogador)
    public Tepig(String nome) {
        super(nome, 15, 3, 3, 3, 1);
    }

    //Implementação de método abstrato
    @Override
    public String getHabilidadeExploracao() {
        return HABILIDADE;
    }

    @Override
    protected void aplicarAtributosDeNivel() {
        this.hpMax += 7; // Ganha muita vida
        this.atk += 3;   // Bate forte
        this.def += 1;
        this.agi += 1;   // Lento
        System.out.println("   | (Tepig está ficando robusto! +HP +ATK)");
    }
    
    //Implementa a lógica da habilidade específica do Tepig.
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        if (alvo.equals("sala")) {
            return "Você usa FARO AGUÇADO e sente o cheiro de papel velho vindo debaixo de uma tábua solta.";
        } else {
            return "Você tenta usar FARO AGUÇADO, mas não sente nada fora do comum.";
        }
    }
}