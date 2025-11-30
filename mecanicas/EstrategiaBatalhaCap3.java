package mecanicas;

import java.util.Scanner;
import personagem.Personagem;

public class EstrategiaBatalhaCap3 implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        return new String[] {
            "[ATACAR]: (Você ataca o Quilava com força total.)",
            "[PROTEGER]: (Você assume uma postura defensiva.)",
            "[HESITAR]: (Você olha para o Quilava... e se recusa a lutar.)"
        };
    }

    @Override
    public void executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Scanner scanner, Batalha batalha) {
        
        switch (escolha) {
            case 1: // Atacar
                System.out.printf("%s ataca %s!\n", p1.getNome(), p2.getNome());
                System.out.println("(O Quilava não se defende!)");
                int dano = p1.atacar(p2);
                System.out.printf("Você causou %d de dano. (HP de %s: %d/%d)\n", dano, p2.getNome(), p2.getHp(), p2.getHpMax());
                
                if (!p2.estaVivo()) {
                    System.out.println(p2.getNome() + " foi nocauteado.");
                    batalha.setBatalhaTerminou(true); // O jogador "venceu"
                } else {
                     System.out.println("Ketch: \"MAIS FORTE! ACABE COM ELE!\"");
                }
                break;

            case 2: // Proteger
            case 3: // Hesitar
                System.out.println("Você se recusa a lutar.");
                System.out.println("Ketch: \"O QUE ESTÁ FAZENDO? LUTE!\"");
                // O jogador não faz nada, a batalha termina
                batalha.setBatalhaTerminou(true); 
                break;
        }
    }

    @Override
    public void executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        System.out.println(p2.getNome() + " te encara, com medo. Ele não vai revidar.");
    }

    @Override
    public boolean isBatalhaImpossivel() {
        return true;
    }
}