package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloDez extends Capitulo {

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- INTRODUÇÃO E DESFECHO IMEDIATO ---
                sb.append("\n--- CAPÍTULO 10: O NOVO AMANHECER ---\n");
                sb.append("(Status Atual: Nível " + jogador.getNivel() + ". HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                
                sb.append("(A polícia de Ecruteak chega, alertada pela explosão ou pelos Pokémons libertos.)\n");
                sb.append("Os cultistas estão sendo presos. Você vê Ketch sendo algemado.\n");
                sb.append("Ele te vê. Ele não diz nada. Ele apenas abaixa a cabeça, derrotado.\n\n");
                
                sb.append("Você sobe no topo de uma colina próxima, longe da confusão.\n");
                sb.append("O sol está nascendo sobre Johto. O mesmo sol que te acordou no Capítulo 1.\n");
                sb.append("Está silencioso. Você está livre.\n\n");
                
                sb.append("O cheiro das Oran Berries... você quase pode senti-lo vindo da floresta.\n");
                sb.append("Mas... o que fazer agora?\n");
                sb.append("Você não é mais o mesmo Pokémon inocente daquela clareira.\n");
                sb.append("O Sentret se foi. O paraíso se foi.\n\n");

                sb.append("O QUE VOCÊ FAZ?\n");
                sb.append("1. [VOLTAR PARA CASA] (O culto acabou. Descansar na clareira.)\n");
                sb.append("2. [PROTEGER OS OUTROS] (O mundo é perigoso. Tornar-se um Guardião.)");
                
                etapa = 1;
                break;

            case 1:
                // --- ESCOLHA DO FINAL ---
                
                sb.append("\n============================================================\n");
                
                if (input.equals("1")) {
                    // --- FINAL BOM: A PAZ RECONQUISTADA ---
                    sb.append("#           FINAL BOM: A PAZ RECONQUISTADA                 #\n");
                    sb.append("============================================================\n\n");
                    
                    sb.append("Você viaja por dias. De volta por onde veio.\n");
                    sb.append("Você chega na colina. Você chega na clareira.\n");
                    sb.append("Está... quieta. O Poliwag não está no riacho.\n");
                    sb.append("A árvore do Sentret está vazia.\n\n");
                    
                    sb.append("Mas a grama ainda é macia. As Oran Berries ainda crescem.\n");
                    sb.append("Você se deita ao sol, no exato local onde acordou no início.\n");
                    sb.append("Você está ferido, com cicatrizes, mudado para sempre.\n");
                    sb.append("Mas você está em casa.\n");
                    sb.append("Você fecha os olhos. Pela primeira vez em muito tempo, você se sente em paz.\n\n");
                    
                    sb.append("[FIM DE JOGO]");
                    etapa = 99;

                } else if (input.equals("2")) {
                    // --- FINAL VERDADEIRO: O GUARDIÃO ---
                    sb.append("#           FINAL VERDADEIRO: O GUARDIÃO                   #\n");
                    sb.append("============================================================\n\n");

                    sb.append("Você olha para o sol nascente. Você não pode voltar.\n");
                    sb.append("A inocência se foi. O Sentret se foi.\n");
                    sb.append("Mas você pode garantir que isso nunca aconteça com mais ninguém.\n\n");
                    
                    sb.append("Seu antigo treinador era apenas um sintoma. O Líder era apenas um homem.\n");
                    sb.append("O problema é maior. O mundo precisa de quem o defenda.\n");
                    
                    sb.append("Você sente algo queimar dentro de você. Não é ódio. É PROPÓSITO.\n");
                    sb.append("Uma luz branca e brilhante te envolve. É quente. É poderosa.\n\n");
                    
                    sb.append("(O que?! " + jogador.getNome() + " está evoluindo!)\n");
                    sb.append("Seu corpo cresce. Suas feridas se fecham. Você atinge sua forma final.\n\n");
                    
                    sb.append("Você se vira, não para sua antiga casa, mas para o horizonte desconhecido.\n");
                    sb.append("Você não é mais uma vítima.\n");
                    sb.append("Você é um protetor. Um Guardião.\n\n");
                    
                    sb.append("[FIM DE JOGO]");
                    etapa = 99;

                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;
        }

        return sb.toString();
    }
}