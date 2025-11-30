package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import personagem.Jogador;
import util.Util;

public class CapituloDez implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 10: O NOVO AMANHECER ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());
        
        System.out.println("(A polícia de Ecruteak chega, alertada pela explosão ou pelos Pokémons libertos.)");
        System.out.println("Os cultistas estão sendo presos. Você vê Ketch sendo algemado.");
        System.out.println("Ele te vê. Ele não diz nada. Ele apenas abaixa a cabeça, derrotado.");
        
        Util.aguardarEnter(scanner);

        System.out.println("\nVocê está no topo de uma colina próxima, longe da confusão.");
        System.out.println("O sol está nascendo sobre Johto. O mesmo sol que te acordou no Capítulo 1.");
        System.out.println("Está silencioso. Você está livre.");
        
        System.out.println("O cheiro das Oran Berries... você quase pode senti-lo vindo da floresta.");
        System.out.println("Mas... o que fazer agora?");
        System.out.println("Você não é mais o mesmo Pokémon inocente daquela clareira.");
        System.out.println("O Sentret se foi. O paraíso se foi.");

        Util.aguardarEnter(scanner);

        int escolhaEpilogo = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                "[VOLTAR PARA CASA] (O culto acabou. É hora de voltar para a clareira. Descansar.)",
                "[PROTEGER OS OUTROS] (Este mundo é perigoso. Você não pode voltar a ser quem era.)");

        Util.limparTela();
        
        if (escolhaEpilogo == 1) {
            // --- FINAL BOM ---
            System.out.println("############################################################");
            System.out.println("#           FINAL BOM: A PAZ RECONQUISTADA                 #");
            System.out.println("############################################################");
            Util.pausar(1);
            
            System.out.println("\nVocê viaja por dias. De volta por onde veio.");
            System.out.println("Você chega na colina. Você chega na clareira.");
            System.out.println("Está... quieta. O Poliwag não está no riacho.");
            System.out.println("A árvore do Sentret está vazia.");
            
            System.out.println("Mas a grama ainda é macia. As Oran Berries ainda crescem.");
            System.out.println("Você se deita ao sol, no exato local onde acordou no início.");
            System.out.println("Você está ferido, com cicatrizes, mudado para sempre.");
            System.out.println("Mas você está em casa.");
            System.out.println("Você fecha os olhos. Pela primeira vez em muito tempo, você se sente em paz.");
            
            System.out.println("\n[FIM DE JOGO]");
            
        } else {
            // --- FINAL VERDADEIRO ---
            System.out.println("############################################################");
            System.out.println("#           FINAL VERDADEIRO: O GUARDIÃO                   #");
            System.out.println("############################################################");
            Util.pausar(1);

            System.out.println("\nVocê olha para o sol nascente. Você não pode voltar.");
            System.out.println("A inocência se foi. O Sentret se foi.");
            System.out.println("Mas você pode garantir que isso nunca aconteça com mais ninguém.");
            
            System.out.println("Seu antigo treinador era apenas um sintoma. O Líder era apenas um homem.");
            System.out.println("O problema é maior. O mundo precisa de quem o defenda.");
            
            System.out.println("Você sente algo queimar dentro de você. Não é ódio. É PROPÓSITO.");
            System.out.println("Uma luz branca e brilhante te envolve. É quente. É poderosa.");
            
            System.out.println("\n(O que?! " + jogador.getNome() + " está evoluindo!)");
            System.out.println("Seu corpo cresce. Suas feridas se fecham. Você atinge sua forma final.");
            
            System.out.println("\nVocê se vira, não para sua antiga casa, mas para o horizonte desconhecido.");
            System.out.println("Você não é mais uma vítima.");
            System.out.println("Você é um protetor. Um Guardião.");
            
            System.out.println("\n[FIM DE JOGO]");
        }
        
        // Retorna false para encerrar o loop do jogo no main
        return new ResultadoCapitulo("");
    }
}