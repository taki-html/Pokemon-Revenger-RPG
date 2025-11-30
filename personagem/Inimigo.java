package personagem;

public class Inimigo extends Personagem {

    // Método construtor:
    public Inimigo(String nome, int hpMax, int atk, int def, int agi, int nivel) {
        super(nome, hpMax, atk, def, agi, nivel);
    }

    //Implementação de Método Abstrato
    @Override
    public String getHabilidadeExploracao() {
        return "Nenhuma";
    }
    
    @Override
    public String usarHabilidadeExploracao(String alvo) {
        return this.nome + " não pode fazer isso.";
    }

    //LevelUp() para inimigos -> Não sobem de nível durante as batalhas nem ganham EXP
    @Override
    protected void levelUp() {
    }
}