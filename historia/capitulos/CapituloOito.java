package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaNormal;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;

public class CapituloOito implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 8: A TORRE QUEIMADA ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());
        System.out.println("(Ecruteak City. A velha torre se ergue contra o céu noturno chuvoso.)");
        System.out.println("O lugar está cercado de cultistas. É uma fortaleza.");
        System.out.println("Você entra por uma fenda nas ruínas.");

        Util.aguardarEnter(scanner);

        // --- SALA 1: A ENTRADA (Teste de Stealth/Agilidade) ---
        System.out.println("\n[SALA 1: O HALL DE ENTRADA]");
        System.out.println("Dois guardas patrulham a escada para o subsolo com lanternas.");
        System.out.println("O chão está cheio de escombros.");

        int escolhaSala1 = Util.obterEscolha(scanner, "COMO PASSAR?",
                "[FURTIVIDADE] (Usar as sombras e escombros.)",
                "[LUTAR] (Atacar os guardas de surpresa.)");

        if (escolhaSala1 == 1) {
            // Teste de Habilidade de Classe ou Agilidade
            String habilidade = jogador.getHabilidadeExploracao();
            boolean passouTeste = false;

            // Verifica classes furtivas ou Agilidade alta
            if (habilidade.equals("Voo Silencioso") || habilidade.equals("Aderência") || jogador.getAgi() >= 7) {
                passouTeste = true;
            }

            if (passouTeste) {
                System.out.println("Sucesso! Você usa " + habilidade + " (ou sua agilidade natural) para passar sem ser visto.");
                jogador.ganharExp(30);
            } else {
                System.out.println("Você tenta ser silencioso, mas chuta uma pedra!");
                System.out.println("Guarda: \"Ei! Um intruso!\"");
                // Batalha Forçada
                batalhaSala1(jogador, scanner);
                if (!jogador.estaVivo()) return ResultadoCapitulo.finalRuim("Você caiu na entrada da torre.");
            }
        } else {
            // Escolheu lutar
            batalhaSala1(jogador, scanner);
            if (!jogador.estaVivo()) return ResultadoCapitulo.finalRuim("Você caiu na entrada da torre.");
        }

        // --- SALA 2: CORREDOR DOS PRISIONEIROS ---
        System.out.println("\n[SALA 2: O CORREDOR]");
        System.out.println("Gaiolas alinham as paredes. Outros Pokémons capturados.");
        System.out.println("Um Geodude, um Poliwag, um Pidgey. Eles te olham com esperança.");
        
        int escolhaSala2 = Util.obterEscolha(scanner, "O TEMPO É CURTO.",
                "[LIBERTAR PRISIONEIROS] (Gasta tempo, requer habilidade, mas é o certo a fazer.)",
                "[IGNORAR E AVANÇAR] (Você precisa chegar ao Líder. Sentret não foi salvo, por que eles seriam?)");

        if (escolhaSala2 == 1) {
            System.out.println("Você corre para as gaiolas.");
            // Uso da Habilidade de Classe
            System.out.println(jogador.usarHabilidadeExploracao("gaveta")); // Reaproveitando a string "gaveta" ou similar que abre coisas, ou apenas narrativo
            System.out.println("Você quebra as travas!");
            
            System.out.println("Poliwag: \"Obrigado! Pegue isso!\"");
            System.out.println("(Eles te dão uma Sitrus Berry. HP TOTALMENTE RECUPERADO!)");
            jogador.setHp(jogador.getHpMax());
            jogador.ganharExp(60); // Bônus de Heroísmo
            jogador.adicionarLealdade(2);
        } else {
            System.out.println("Você fecha os olhos para o sofrimento deles e corre.");
            System.out.println("O objetivo é a vingança, não o resgate.");
            // Sem bônus
        }

        Util.aguardarEnter(scanner);

        // --- SALA 3: O GUARDA DE ELITE ---
        System.out.println("\n[SALA 3: A ANTECÂMARA]");
        System.out.println("Um cultista de manto vermelho guarda a porta final.");
        System.out.println("Guarda de Elite: \"O Mestre não será interrompido! ARCANINE, QUEIME-O!\"");
        
        Inimigo arcanine = new Inimigo("Arcanine de Elite", 35, 10, 8, 9, 8);
        
        System.out.println("\n(Cuidado! Se você morrer aqui, é o fim.)");
        Batalha batalhaElite = new Batalha(jogador, arcanine, scanner, new EstrategiaBatalhaNormal());
        boolean venceuElite = batalhaElite.iniciarBatalha();

        if (venceuElite) {
            System.out.println("O Arcanine cai com um baque pesado.");
            System.out.println("O cultista foge, aterrorizado.");
            System.out.println("A porta para o ritual está à sua frente.");
            
            jogador.ganharExp(100); // Level Up provável para Nível 9 ou 10
            
            return ResultadoCapitulo.continuar();
        } else {
            String msgFinal = "[FINAL RUIM 5: GUARDIÃO CAÍDO]\n" +
                              "Você lutou bravamente contra a elite, mas não foi o bastante.\n" +
                              "Você cai no chão de pedra da torre.\n" +
                              "O último som que você ouve é o canto do ritual ficando mais alto.";
            return ResultadoCapitulo.finalRuim(msgFinal);
        }
    }

    // No método auxiliar batalhaSala1: Ajuste de Inimigos e XP
    private void batalhaSala1(Jogador jogador, Scanner scanner) {
        System.out.println("Um Machoke e um Hypno bloqueiam o caminho!");
        // Status mais fortes para refletir Machoke/Hypno
        Inimigo guardas = new Inimigo("Machoke & Hypno", 25, 15, 8, 7, 5); 
        
        Batalha b = new Batalha(jogador, guardas, scanner, new EstrategiaBatalhaNormal());
        boolean venceu = b.iniciarBatalha();
        
        if (venceu) {
            jogador.ganharExp(60); // O Bônus de Batalha prometido no texto
        }
    }
}