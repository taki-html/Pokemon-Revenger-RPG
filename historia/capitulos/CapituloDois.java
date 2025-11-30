package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import personagem.Jogador;
import util.Util;

public class CapituloDois implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 2: A GAIOLA ---");
        
        // Força o HP do jogador para 1 (continua da captura)
        jogador.setHp(1);
        System.out.println("\n(A tela está preta.)");
        System.out.println("...frio... ...metal... ...dor...");
        Util.aguardarEnter(scanner);
        
        System.out.println("A dor é a primeira coisa que você sente. (HP: " + jogador.getHp() + "/" + jogador.getHpMax() + " [PERIGO])");
        System.out.println("Você força seus olhos a abrirem. Você está em uma gaiola de metal, em um porão úmido.");
        Util.aguardarEnter(scanner);

        System.out.println("(Passos na escada. A porta range. É o humano.)");
        System.out.println("Humano: \"Ah, o 'prêmio' acordou. Ótimo.\"");
        Util.aguardarEnter(scanner);

        System.out.println("(Ele não chuta a gaiola. Ele a abre.)");
        System.out.println("Humano: \"Quil. Hora de comer.\"");
        System.out.println("(Ele joga uma tigela com ração dura e cinzenta no chão de concreto.)");
        System.out.println("Humano: \"Coma. Você precisa parecer forte para o cliente. Não me faça te forçar.\"");
        Util.aguardarEnter(scanner);

        System.out.println("(Você está com 1 HP. A dor é imensa. Você mal consegue se mover, quanto mais comer.)");
        System.out.println("Humano: \"Hmph. Patético. Espírito selvagem inútil.\"");
        System.out.println("Humano: \"Quil, fique de olho. Se não comer em cinco minutos, use um Brasa de aviso. Precisamos quebrar essa teimosia.\"");
        Util.aguardarEnter(scanner);

        System.out.println("(O humano sobe as escadas. Silêncio. É só você e o Quilava.)");
        System.out.println("(O Quilava te encara. As chamas em suas costas queimam baixas, incertas.)");
        System.out.println("(Ele olha para a ração, depois para você. Ele solta um pequeno bufo de fumaça.)");
        Util.aguardarEnter(scanner);
        
        System.out.println("(Ele cutuca a tigela com o focinho, empurrando-a para mais perto. Você não se move.)");
        Util.aguardarEnter(scanner);

        System.out.println("(O Quilava olha para a escada, nervoso. Então, ele se vira, e com um movimento rápido da pata, empurra algo para dentro da sua gaiola.)");
        System.out.println("(Não é a ração. É uma... Oran Berry, amassada e pela metade.)");
        Util.aguardarEnter(scanner);
        
        System.out.println("(É a *dele*. Ele guardou para você.)");
        System.out.println("(Você come a Oran Berry. A dor diminui. HP: 10/" + jogador.getHpMax() + ")");
        jogador.setHp(10); // Recupera um pouco
        Util.aguardarEnter(scanner);

        System.out.println("(O Quilava vê você comer. Ele se aproxima da sua gaiola.)");
        System.out.println("(Com um 'clic' baixo, ele usa uma garra para empurrar a trava da sua porta, soltando-a.)");
        System.out.println("(Ele então se vira e se deita no canto, fingindo dormir.)");
        Util.aguardarEnter(scanner);
        
        System.out.println("(A porta está solta. A escada está logo ali. O Quilava te deu uma chance.)");

        int escolha = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                "[FUGIR AGORA] (O Quilava te ajudou. Você deve aproveitar a chance!)",
                "[ESPERAR E OBSERVAR] (Por que ele te ajudou? Isso é um teste?)");

        if (escolha == 1) {

            // CAMINHO A: FUGIR (FINAL RUIM 1)
            System.out.println("\nVocê empurra a porta da gaiola. O metal range baixo.");
            System.out.println("Você corre para as escadas, uma pata de cada vez.");
            System.out.println("Você ouve a voz do humano no andar de cima, distraído.");
            System.out.println("Você chega ao topo... um beco sujo. É o cheiro da liberdade.");
            Util.pausar(2);
            
            System.out.println("(No momento em que você pisa na rua, você ouve um grito do porão.)");
            System.out.println("Humano: \"O QUÊ? SEU... TRAIDOR INÚTIL!\"");
            System.out.println("(Um som alto de 'SMACK', e o grito de dor inconfundível do Quilava.)");
            Util.aguardarEnter(scanner);
            
            System.out.println("(Você escapou.)");

            // Retorna o resultado do Final Ruim
            String msgFinal = "[FINAL RUIM 1: O TRAIDOR]\n" +
                              "Você conseguiu. Você está livre. Você eventualmente se curou.\n" +
                              "Mas você nunca esqueceu o som daquele grito.\n" +
                              "Você nunca mais viu o Quilava. Você nunca soube o que aconteceu com Sentret.\n" +
                              "Você vive na floresta, mas agora se esconde de todos os humanos.\n" +
                              "Você está livre. Mas você se pergunta se sua liberdade valeu o preço pago por outro.";
            return ResultadoCapitulo.finalRuim(msgFinal);

        } else {

            // CAMINHO B: ESPERAR
            System.out.println("\nNão. Você não pode. Se você fugir, ele vai pagar o preço.");
            System.out.println("Você puxa a trava solta de volta, para parecer que está fechada.");
            System.out.println("Você se encolhe no canto da gaiola, fingindo estar fraco.");
            Util.pausar(2);
            System.out.println("(O Quilava abre um olho. Ele vê o que você fez. Ele te entende.)");
            System.out.println("(Uma aliança silenciosa foi formada.)");
            Util.aguardarEnter(scanner);
            
            System.out.println("\n(Você acorda com o som da porta do porão se abrindo.)");
            System.out.println("(O humano desce. Ele vê a tigela de ração vazia... e a Oran Berry desaparecida.)");
            System.out.println("Humano: \"Hã. Comeu, é? Bom. Viu, Quil? Medo funciona.\"");
            Util.aguardarEnter(scanner);

            System.out.println("Humano: \"Levanta. Temos um encontro. E tente não parecer tão patético.\"");

            // Bônus de EXP do Capítulo (por construir uma aliança)
            System.out.println("(EXP +50 por sobreviver e ganhar um aliado.)");
            jogador.ganharExp(50);
            
            System.out.println("\n(Fim do Capítulo 2.)");
            Util.aguardarEnter(scanner);
            return ResultadoCapitulo.continuar();
        }
    }
}