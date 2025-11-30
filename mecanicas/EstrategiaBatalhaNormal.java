package mecanicas;

import java.util.Scanner;
import personagem.Personagem;

public class EstrategiaBatalhaNormal implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        // Opções padrão para uma batalha normal
        return new String[] {"Lutar", "Proteger", "Fugir"};
    }

    @Override
    public void executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Scanner scanner, Batalha batalha) {
        // Lógica de batalha normal
        switch (escolha) {
            case 1: // Lutar
                System.out.printf("%s ataca %s!\n", p1.getNome(), p2.getNome());
                int dano = p1.atacar(p2);
                
                // --- LINHA CORRIGIDA ABAIXO ---
                // Adicionado p2.getNome() que estava faltando
                System.out.printf("%s causou %d de dano. (HP de %s: %d/%d)\n", 
                        p1.getNome(), dano, p2.getNome(), p2.getHp(), p2.getHpMax());
                
                if (!p2.estaVivo()) {
                    batalha.setBatalhaTerminou(true);
                }
                break;
            case 2: // Proteger
                System.out.println(p1.getNome() + " se prepara para o próximo ataque. (DEF aumentada temporariamente!)");
                // Nota: Em uma implementação completa, aumentaria a DEF aqui, mas narrativamente serve.
                break;
            case 3: // Fugir
                System.out.println(p1.getNome() + " tenta fugir...");
                if (p1.getAgi() > p2.getAgi()) {
                    System.out.println("...e consegue escapar!");
                    batalha.setBatalhaTerminou(true);
                } else {
                    System.out.println("...mas não consegue!");
                }
                break;
        }
    }

    @Override
    public void executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        // Lógica de ataque inimigo normal
        System.out.printf("%s ataca %s!\n", p2.getNome(), p1.getNome());
        int dano = p2.atacar(p1);
        System.out.printf("%s causou %d de dano. (HP de %s: %d/%d)\n", 
                p2.getNome(), dano, p1.getNome(), p1.getHp(), p1.getHpMax());
    }

    @Override
    public boolean isBatalhaImpossivel() {
        return false;
    }
}