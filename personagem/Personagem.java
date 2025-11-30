package personagem;

public abstract class Personagem {

    protected String nome;
    protected int hp;
    protected int hpMax;
    protected int atk;
    protected int def;
    protected int agi;
    protected int nivel;
    protected int exp;
    protected int expParaProximoNivel;

    //Construtor:
    public Personagem(String nome, int hpMax, int atk, int def, int agi, int nivel) {
        this.nome = nome;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.atk = atk;
        this.def = def;
        this.agi = agi;
        this.nivel = nivel;
        this.exp = 0;
        this.expParaProximoNivel = 100;
    }

    // Lógica de ataque:
    public int atacar(Personagem alvo) {
        int dano = Math.max(1, this.atk - alvo.getDef());
        alvo.receberDano(dano);
        return dano;
    }

    //Lógica de dano
    public void receberDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    //Lógica de cura
    public void curar(int cura) {
        this.hp += cura;
        if (this.hp > this.hpMax) {
            this.hp = this.hpMax;
        }
    }

    //Verificar vida
    public boolean estaVivo() {
        return this.hp > 0;
    }

    //Métodos abstratos:

    public abstract String getHabilidadeExploracao();
    public abstract String usarHabilidadeExploracao(String alvo);

    //Adiciona EXP e verifica se o personagem subiu de nível:
    public void ganharExp(int ganho) {
        this.exp += ganho;
        System.out.printf("   | %s ganhou %d EXP. (EXP: %d/%d)\n", this.nome, ganho, this.exp, this.expParaProximoNivel);
        
        // Verifica se subiu de nível
        while (this.exp >= this.expParaProximoNivel) {
            levelUp();
        }
    }

    //Lógica subir de nível
    protected void levelUp() {
        this.nivel++;
        this.exp -= this.expParaProximoNivel;
        this.expParaProximoNivel = (int) (this.expParaProximoNivel * 1.1);
        System.out.printf("   | !!!!!!! %s subiu para o NÍVEL %d !!!!!!!\n", this.nome, this.nivel);
    }

    //Status atual do personagem
    public void mostrarStatus() {
        System.out.println("\n---------------------------------");
        System.out.printf("  STATUS: %s (Nível %d)\n", this.nome, this.nivel);
        System.out.printf("  HP: %d/%d\n", this.hp, this.hpMax);
        System.out.printf("  ATK: %d | DEF: %d | AGI: %d\n", this.atk, this.def, this.agi);
        System.out.printf("  EXP: %d/%d\n", this.exp, this.expParaProximoNivel);
        System.out.println("---------------------------------");
    }

    //Getters e Setters
    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getAgi() { return agi; }
    public int getNivel() { return nivel; }
    public int getExp() { return exp; }
    public int getExpParaProximoNivel() { return expParaProximoNivel; }
    
    // Setter para forçar um valor de HP
    public void setHp(int hp) {
        if (hp < 0) this.hp = 0;
        else if (hp > this.hpMax) this.hp = this.hpMax;
        else this.hp = hp;
    }
}