package personagem;
import java.util.ArrayList;
import java.util.List;


public abstract class Jogador extends Personagem {

    private List<String> inventarioPistas;
    private int contadorInacao = 0;
    
    private int contadorTraicao;
    private int contadorLealdade;

    //Método construtor:
    public Jogador(String nome, int hpMax, int atk, int def, int agi, int nivel) {
        super(nome, hpMax, atk, def, agi, nivel);
        
        this.inventarioPistas = new ArrayList<>();
        this.contadorTraicao = 0;
        this.contadorLealdade = 0;
    }

    @Override
    protected void levelUp() {
        super.levelUp();
        this.hpMax += 2; // +2 HP Max
        this.hp = this.hpMax; // Cura total ao subir de nível
        this.atk += 1; // +1 ATK
        this.def += 1; // +1 DEF
        this.agi += 1; // +1 AGI
        
        System.out.printf("   | HP Max: %d | ATK: %d | DEF: %d | AGI: %d\n", this.hpMax, this.atk, this.def, this.agi);
    }

    public void adicionarPista(String pista) {
        if (!this.inventarioPistas.contains(pista)) {
            this.inventarioPistas.add(pista);
            System.out.printf("   | [PISTA ADQUIRIDA: %s]\n", pista);
        }
    }

    public boolean temPista(String pista) {
        return this.inventarioPistas.contains(pista);
    }

    public void incrementarInacao() {
        this.contadorInacao++;
    }

    public int getContadorInacao() {
        return this.contadorInacao;
    }
    
    public void adicionarTraicao(int valor) {
        this.contadorTraicao += valor;
        System.out.println("   | (Sua relação com seus aliados mudou...)");
    }

    public void adicionarLealdade(int valor) {
        this.contadorLealdade += valor;
        System.out.println("   | (Um laço de lealdade foi formado.)");
    }

    public int getContadorTraicao() {
        return this.contadorTraicao;
    }

    public int getContadorLealdade() {
        return this.contadorLealdade;
    }
    
}