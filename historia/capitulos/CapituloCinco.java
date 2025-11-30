package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import personagem.Jogador;
import util.Util;

public class CapituloCinco implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 5: O SACRIFÍCIO ---");
        
        // Verifica se o jogador estava escondido (veio da investigação) ou na gaiola
        // Para simplificar a narrativa linear deste script, assumimos o ponto de convergência:
        // O jogador acaba observando a cena, seja debaixo do sofá ou da gaiola trazida para dentro.
        
        System.out.println("(A porta da frente se abre. O humano acorda assustado no sofá.)");
        System.out.println("Humano: \"V... vocês não deveriam vir aqui!\"");
        
        Util.aguardarEnter(scanner);

        System.out.println("Uma Voz Fria responde: \"Cale-se, Iniciado. Estamos aqui pela sua falha.\"");
        System.out.println("Três pessoas em mantos escuros entram. O Símbolo está em seus capuzes.");
        System.out.println("Um dos cultistas joga uma gaiola pequena no chão da sala.");
        
        Util.aguardarEnter(scanner);

        System.out.println("Seu coração para.");
        System.out.println("Aquele cheiro. Você conhece.");
        System.out.println("Dentro da gaiola, tremendo, está o SENTRET.");
        System.out.println("Seu melhor amigo do Capítulo 1.");

        Util.aguardarEnter(scanner);

        System.out.println("Sentret: \"" + jogador.getNome() + "...? Onde... onde estou? Estou com medo...\"");
        System.out.println("(Ele te fareja, mas não consegue te ver).");
        
        System.out.println("Os cultistas desenham um círculo no chão e colocam o Sentret no centro.");
        System.out.println("Voz Fria: \"Um sacrifício puro para apagar o fracasso. Trará grande poder para o Mestre.\"");
        
        Util.aguardarEnter(scanner);

        System.out.println("Eles começam a cantar. O humano (seu treinador) está no canto, de olhos fechados, covarde.");
        System.out.println("O Sentret está chorando. Eles estão distraídos.");
        System.out.println("Você poderia atacar. Mas são três. E eles têm Pokébolas na cintura.");

        int escolha = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                "[TENTAR SALVAR O AMIGO] (Você não pode deixar isso acontecer! Ataque!)",
                "[OBSERVAR, IMPOTENTE] (Se você atacar, ambos morrerão. Você precisa sobreviver.)");

        if (escolha == 1) {
            // --- CAMINHO A: TENTAR SALVAR (FINAL RUIM 3) ---
            System.out.println("\nVocê explode do seu esconderijo com um grito de guerra!");
            System.out.println("\"SENTRET!\"");
            System.out.println("Você usa seu ataque mais forte no cultista mais próximo.");
            
            Util.pausar(1);
            
            System.out.println("O Sentret te vê. Os olhos dele brilham de esperança: \"[NOME]!\"");
            System.out.println("Por um segundo, você acha que pode funcionar.");
            
            Util.aguardarEnter(scanner);
            
            System.out.println("Voz Fria: \"Um inseto.\"");
            System.out.println("Ele joga uma Pokébola casualmente. Um GENGAR aparece das sombras.");
            System.out.println("Antes que você possa reagir, o Gengar te acerta com um Raio Sombrio (Shadow Ball).");
            
            System.out.println("(A dor é absoluta. Seu HP cai para 0 instantaneamente.)");
            jogador.setHp(0);
            
            Util.pausar(2);
            
            System.out.println("Voz Fria: \"Segure-o. Que ele assista.\"");
            System.out.println("O Gengar te prende no chão. Você não consegue se mexer.");
            System.out.println("Você vê o ritual terminar. Você vê a luz roxa engolir seu amigo.");
            
            String msgFinal = "[FINAL RUIM 3: O CULPADO]\n" +
                              "Você falhou. O ritual foi concluído.\n" +
                              "O Sentret desapareceu. E você foi capturado pelo Culto para 'reeducação'.\n" +
                              "Sua bravura foi nobre, mas imprudente. O mundo é cruel com os heróis fracos.";
            return ResultadoCapitulo.finalRuim(msgFinal);

        } else {
            // --- CAMINHO B: OBSERVAR (CAMINHO DA VINGANÇA) ---
            System.out.println("\nVocê... trava seus músculos. Você enfia as garras no chão para não gritar.");
            System.out.println("Você fecha os olhos. Lágrimas silenciosas escorrem pelo seu rosto.");
            
            Util.pausar(2);
            
            System.out.println("Você ouve o canto ficar mais alto.");
            System.out.println("Sentret: \"" + jogador.getNome() + "... socorro...\"");
            System.out.println("Então... um clarão de luz roxa. Um som de vácuo.");
            System.out.println("...e silêncio.");
            
            Util.aguardarEnter(scanner);

            System.out.println("Você abre os olhos. O Sentret se foi.");
            System.out.println("Seu coração se parte em mil pedaços. O paraíso do Capítulo 1 está morto.");
            System.out.println("Mas algo nasce no lugar. Um ódio frio. Profundo.");
            
            System.out.println("Voz Fria: \"O poder foi liberado. O Mestre precisa da 'Grande Arma' na Torre Queimada.\"");
            System.out.println("Eles saem, deixando o humano vomitando no chão de medo.");
            
            Util.aguardarEnter(scanner);
            
            System.out.println("Você fica no escuro por horas, tremendo de raiva.");
            System.out.println("Você tem um nome: TORRE QUEIMADA.");
            System.out.println("Você tem um objetivo: VINGANÇA.");
            
            // Recompensas narrativas
            jogador.ganharExp(30); // Bônus de Sobrevivência Dolorosa
            jogador.ganharExp(50); // Bônus de Capítulo
            
            System.out.println("\n(Fim do Capítulo 5.)");
            
            // Atualizar o arquivo Jogo.java para incluir estes capítulos no array!
            return ResultadoCapitulo.continuar();
        }
    }
}