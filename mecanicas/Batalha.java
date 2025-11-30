// Na pasta "mecanicas"
package mecanicas;

import java.util.Scanner;
import personagem.Personagem;
import util.Util;

public class Batalha {
    // ... (Construtores e variáveis p1, p2, etc. permanecem iguais) ...
    
    private Personagem p1;
    private Personagem p2;
    private Scanner scanner;
    private IEstrategiaBatalha estrategia;
    private boolean batalhaTerminou;

    // Construtor Padrão
    public Batalha(Personagem p1, Personagem p2, Scanner scanner) {
        this.p1 = p1;
        this.p2 = p2;
        this.scanner = scanner;
        this.batalhaTerminou = false;
        this.estrategia = new EstrategiaBatalhaNormal(); 
    }

    // Construtor para lógicas especiais
    public Batalha(Personagem p1, Personagem p2, Scanner scanner, IEstrategiaBatalha estrategia) {
        this.p1 = p1;
        this.p2 = p2;
        this.scanner = scanner;
        this.estrategia = estrategia;
        this.batalhaTerminou = false;
    }

    public void setBatalhaTerminou(boolean terminou) {
        this.batalhaTerminou = terminou;
    }

    // ... (iniciarBatalha() e turnoInimigo() permanecem os mesmos) ...
    public boolean iniciarBatalha() {
        System.out.printf("\n--- BATALHA INICIADA: %s vs %s ---\n", p1.getNome(), p2.getNome());
        Util.pausar(1);

        while (p1.estaVivo() && p2.estaVivo() && !batalhaTerminou) {

            if (p1.getAgi() >= p2.getAgi()) {
                turnoJogador();
                if (!p2.estaVivo() || !p1.estaVivo() || batalhaTerminou) break;
                turnoInimigo();
            } else {
                turnoInimigo();
                if (!p1.estaVivo() || !p2.estaVivo() || batalhaTerminou) break;
                turnoJogador();
            }
        }

        // Fim da batalha
        if (p1.estaVivo() && !p2.estaVivo()) {
            System.out.printf("\n%s venceu a batalha!\n", p1.getNome());
            return true;
        } else if (estrategia.isBatalhaImpossivel() && p1.getHp() == 1) {
            System.out.println("\n(Você foi derrotado, mas sobreviveu...)");
            return false;
        } else if (!p1.estaVivo()) {
            System.out.printf("\n%s foi derrotado...\n", p1.getNome());
            return false;
        } else {
            System.out.println("\nA batalha terminou.");
            return true;
        }
    }


    /**
     * Lógica do turno do jogador
     */
    private void turnoJogador() {
        String[] opcoes = estrategia.getOpcoesDoJogador();
        
        int escolha = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?", opcoes);
        
        estrategia.executarTurnoJogador(escolha, p1, p2, scanner, this);
    }

    /**
     * Lógica do turno do inimigo
     */
    private void turnoInimigo() {
        if (!p2.estaVivo() || batalhaTerminou)
            return;
        System.out.println("\nTurno de " + p2.getNome());
        estrategia.executarTurnoInimigo(p1, p2, this);
    }
}