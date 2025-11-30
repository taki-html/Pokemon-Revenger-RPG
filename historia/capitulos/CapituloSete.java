package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaNormal;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;

public class CapituloSete implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 7: O PONTO DE NÃO RETORNO ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());
        System.out.println("(Três dias se passaram. É o dia do ritual.)");
        
        Util.aguardarEnter(scanner);

        System.out.println("Seu treinador está nervoso, arrumando a bolsa. Ele coloca um Talismã menor no bolso.");
        System.out.println("Ele abre a porta da frente para sair, deixando você no quintal frio.");
        System.out.println("Ele te olha. \"Você... você fica. Você só me traz problemas.\"");
        
        Util.aguardarEnter(scanner);
        
        System.out.println("Ele vai te deixar para trás. Preso. Enquanto eles destroem tudo.");
        System.out.println("Esta é a sua única chance.");
        
        System.out.println("\nEnquanto ele vira as costas para trancar a porta, você corre!");
        System.out.println("Você usa toda a força que acumulou. Você se joga contra a cerca podre/porta de vidro.");
        System.out.println("CRASH! O vidro quebra. Você está na rua.");

        System.out.println("Humano: \"NÃO! VOCÊ NÃO VAI!\"");
        System.out.println("Ele te persegue até o beco. Ele bloqueia a única saída.");
        System.out.println("Humano: \"Você não vai estragar isso para mim! Eu não vou falhar por causa de um Pokémon estúpido!\"");
        
        System.out.println("Ele joga uma Pokébola. A única que ele tem.");
        System.out.println("Humano: \"QUIL! PARE-O! USE TUDO!\"");
        
        Util.aguardarEnter(scanner);

        System.out.println("O QUILAVA aparece. Ele parece mais forte (Nível 5), mas seus olhos estão tristes.");
        System.out.println("Ele sabe que esta é uma luta de vida ou morte.");
        System.out.println("Mas ele obedece. Ele não tem a sua coragem.");

        // Configura a Batalha Decisiva
        // O Quilava agora é mais forte para desafiar o jogador
        Inimigo quilavaBoss = new Inimigo("Quilava (Rival)", 20, 6, 6, 7, 5);
        
        System.out.println("\n--- BATALHA DECISIVA ---");
        Batalha batalha = new Batalha(jogador, quilavaBoss, scanner, new EstrategiaBatalhaNormal());
        boolean venceu = batalha.iniciarBatalha();

        if (venceu) {
            // CAMINHO A: VENCER A BATALHA
            System.out.println("\nO Quilava cai, exausto. Ele olha para você... e parece sorrir levemente. Ele está livre da luta.");
            System.out.println("O humano cai de joelhos, boquiaberto. \"Não... não...\"");
            System.out.println("Ele tenta te agarrar com as próprias mãos.");
            
            System.out.println("Você rosna. Uma chama/energia brilha em seus olhos. Ele recua, apavorado.");
            System.out.println("Você o deixa para trás, um homem quebrado chorando no beco.");
            
            System.out.println("\nVocê corre em direção à saída da cidade.");
            System.out.println("Destino: ECRUTEAK. TORRE QUEIMADA.");
            
            // Cura total após a batalha decisiva
            System.out.println("(Você descansa brevemente na floresta e come Oran Berries. HP Recuperado.)");
            jogador.setHp(jogador.getHpMax());
            
            // Level Up garantido para a finale
            jogador.ganharExp(130); 
            
            System.out.println("(Fim do Capítulo 7.)");
            return ResultadoCapitulo.continuar();
            
        } else {
            // CAMINHO B: PERDER A BATALHA (FINAL RUIM 4)
            System.out.println("\nO Quilava te acerta com um último golpe. Você cai, sem forças.");
            System.out.println("Humano: \"Veja? Eu não falhei. Eu peguei ele de volta.\"");
            
            System.out.println("Ele te coloca de volta na Pokébola. A escuridão retorna.");
            System.out.println("Você acorda horas depois. Você está na Torre Queimada.");
            System.out.println("Mas não como herói. Como sacrifício.");
            
            String msgFinal = "[FINAL RUIM 4: A CORRENTE ETERNA]\n" +
                              "Você foi derrotado e levado ao ritual como um sacrifício extra.\n" +
                              "Sua fuga falhou. O ciclo de abuso continua.";
            return ResultadoCapitulo.finalRuim(msgFinal);
        }
    }
}