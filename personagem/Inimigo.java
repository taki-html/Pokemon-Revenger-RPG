package personagem;

public class Inimigo extends Personagem {

    private int expRecompensa; // Quanto de EXP o jogador ganha ao vencer

    // Construtor Padrão (Calcula EXP automático)
    public Inimigo(String nome, int hpMax, int atk, int def, int agi, int nivel) {
        super(nome, hpMax, atk, def, agi, nivel);
        // Fórmula simples: Nível * 10 + Metade da Vida
        this.expRecompensa = (nivel * 10) + (hpMax / 2);
    }

    // Construtor Completo (Define EXP manualmente - útil para Bosses)
    public Inimigo(String nome, int hpMax, int atk, int def, int agi, int nivel, int expRecompensa) {
        super(nome, hpMax, atk, def, agi, nivel);
        this.expRecompensa = expRecompensa;
    }

    public int getExpRecompensa() {
        return expRecompensa;
    }

    // --- Métodos Abstratos Obrigatórios (Herança) ---

    @Override
    public String getHabilidadeExploracao() {
        return "Ameaça";
    }
    
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        return this.nome + " rosna para " + alvo + ".";
    }

    // Inimigos geralmente não sobem de nível durante o jogo, então deixamos vazio.
    @Override
    protected void levelUp() {
        // Nada acontece.
    }
    
    // Como Inimigo não é abstrato, ele precisa implementar o método de crescimento de atributos
    // caso você tenha colocado aquele método abstrato em Personagem (se colocou apenas em Jogador, ignore isso).
    protected void aplicarAtributosDeNivel() {
        // Nada acontece.
    }
}