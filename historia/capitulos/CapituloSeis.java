package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaNormal;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;

public class CapituloSeis implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 6: O PLANO ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());
        System.out.println("(Sua mentalidade mudou. Você não obedece mais por medo. Você obedece para observar.)");
        System.out.println("(Seu treinador tornou-se imprudente. Ele sabe que falhou e está desesperado.)");

        Util.aguardarEnter(scanner);

        // --- MINI-DESAFIO 1: O MAPA ---
        boolean temMapa = jogador.temPista("MAPA DE JOHTO");

        if (!temMapa) {
            System.out.println("\n(Cena: O Banheiro do Parque)");
            System.out.println("O humano deixa a bolsa no chão para ir ao banheiro público.");
            System.out.println("Você vê um mapa saindo dela. É sua chance de saber para onde eles vão.");

            int escolhaMapa = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                    "[AGARRAR RÁPIDO] (Correr, pegar e voltar. Arriscado.)",
                    "[DERRUBAR VASO] (Fazer barulho para distraí-lo mais tempo.)");

            System.out.println("Você age rápido.");
            if (escolhaMapa == 1) {
                System.out.println("Você corre, puxa o papel com os dentes e volta para a gaiola.");
            } else {
                System.out.println("Você derruba um vaso. O barulho ecoa. Enquanto ele se assusta, você puxa o mapa.");
            }
            
            System.out.println("Você consegue ler rapidamente: 'RITUAL FINAL: TORRE QUEIMADA - ECRUTEAK'.");
            jogador.adicionarPista("MAPA DE JOHTO");
            jogador.ganharExp(20);
        } else {
            System.out.println("\n(Você já memorizou o mapa no apartamento. Você sabe que o destino é a Torre Queimada.)");
            System.out.println("(Você se sente um passo à frente.)");
        }

        Util.aguardarEnter(scanner);

        // --- MINI-DESAFIO 2: A CONVERSA (ESPIONAGEM) ---
        
        System.out.println("\n(Cena: O Parque da Cidade)");
        System.out.println("Seu treinador te força a uma batalha de treino com outro cultista encapuzado.");
        System.out.println("Cultista: \"Você parece fraco, Ketch. O Mestre não tolera fraqueza.\"");
        System.out.println("Cultista: \"Vamos ver se seu Pokémon vale o ar que respira.\"");
        
        System.out.println("(O Cultista libera um Raticate agressivo.)");
        System.out.println("(Você percebe algo: Eles estão cochichando sobre o ritual. Se você vencer rápido, eles se calam.)");
        System.out.println("(Se você parecer fraco e perder, eles vão baixar a guarda e conversar.)");

        int escolhaEstrategia = Util.obterEscolha(scanner, "ESTRATÉGIA DE BATALHA:",
                "[LUTAR COM TUDO] (Mostre sua força! Mas eles ficarão desconfiados.)",
                "[PERDER DE PROPÓSITO] (Pareça patético. Deixe-os falar.)");

        if (escolhaEstrategia == 1) {
            // Vencer a batalha
            Inimigo raticate = new Inimigo("Raticate do Culto", 20, 6, 4, 6, 4);
            Batalha batalha = new Batalha(jogador, raticate, scanner, new EstrategiaBatalhaNormal());
            batalha.iniciarBatalha();
            
            if (jogador.estaVivo()) {
                System.out.println("\nCultista: \"Hmph. Forte. Mas arrogante.\"");
                System.out.println("Ele recolhe o Raticate e se afasta, desconfiado. Você não ouviu a conversa.");
                System.out.println("(Você terá que confiar apenas no mapa.)");
                jogador.ganharExp(30); // Bônus de Batalha
            }
        } else {
            // Perder de propósito
            System.out.println("\nVocê finge tropeçar. Você erra seus ataques de propósito.");
            System.out.println("O Raticate te morde. Dói, mas você aguenta. (Você perde 5 HP na encenação).");
            jogador.receberDano(5);
            
            System.out.println("Cultista: \"Patético. Como seu treinador.\"");
            System.out.println("Eles te deixam de lado, jogado na grama. E então, começam a falar.");
            
            Util.aguardarEnter(scanner);
            
            System.out.println("Cultista: \"...sim, o Iniciado trará o talismã menor.\"");
            System.out.println("Cultista: \"Esteja na Torre Queimada em TRÊS DIAS. O Mestre começará o ritual ao anoitecer.\"");
            
            jogador.adicionarPista("DATA DO RITUAL");
            jogador.ganharExp(50); // Bônus de Estratégia
            System.out.println("(Você obteve a informação completa: ONDE e QUANDO.)");
        }
        
        jogador.ganharExp(50); // Bônus de Capitulo
        System.out.println("\n(Fim do Capítulo 6.)");
        Util.aguardarEnter(scanner);
        return ResultadoCapitulo.continuar();
    }
}