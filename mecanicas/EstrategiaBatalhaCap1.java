// Na pasta "mecanicas/estrategias"
package mecanicas;

import java.util.Scanner;
import personagem.Personagem;
import util.Util;

public class EstrategiaBatalhaCap1 implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        return new String[] {
            "[LUTAR]: (Você se prepara para usar seu ataque mais forte contra o Quilava.)",
            "[PROTEGER]: (Você se joga na frente do Sentret para protegê-lo.)",
            "[FUGIR]: (Você tenta correr para a grama alta, esperando que o Sentret o siga.)"
        };
    }

    @Override
    public void executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Scanner scanner, Batalha batalha) {
        
        Util.pausar(1);

        switch (escolha) {
            case 1: // Lutar
                System.out.println("Você dispara seu ataque (ATK " + p1.getAtk() + "), mas o Quilava (AGI " + p2.getAgi() + ") o desvia com um movimento treinado...");
                System.out.println("...e te acerta com um Ataque Rápido (Quick Attack) que te joga no chão. A dor é aguda.");
                break;
            case 2: // Proteger
                System.out.println("Você se coloca na frente do Sentret, que corre assustado.");
                System.out.println("O Quilava te atinge com um Brasa (Ember) de aviso, queimando seu pelo/pele.");
                System.out.println("Você grita de dor, uma dor que nunca sentiu antes.");
                break;
            case 3: // Fugir
                System.out.println("Você mal dá dois passos antes que o Quilava (AGI " + p2.getAgi() + ") use Ataque Rápido e apareça na sua frente...");
                System.out.println("...bloqueando seu caminho. Não há escapatória.");
                break;
        }

        // Define mecanicamente a derrota
        p1.setHp(1); 
        batalha.setBatalhaTerminou(true); // Termina a batalha
        
        // --- CONCLUSÃO ---
        Util.pausar(2);
        System.out.println("\n(Qualquer escolha que você faça é fútil. A diferença de poder é muito grande.)");
        System.out.println("(Esta é uma batalha impossível de vencer...)");
        System.out.println("(HP " + p1.getHp() + "/" + p1.getHpMax() + ")");
        
        // Aguarda o jogador para o Capítulo 1 continuar
        Util.aguardarEnter(scanner); 
    }

    @Override
    public void executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        if (p1.estaVivo()) {
             System.out.println(p2.getNome() + " observa, esperando ordens.");
        }
    }

    @Override
    public boolean isBatalhaImpossivel() {
        return true;
    }
}