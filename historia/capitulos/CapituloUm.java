package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloUm extends Capitulo {

    @Override
    public boolean estaFinalizado() {
        return etapa == 99; // 99 indica que o capítulo acabou
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- CENA 1: AMBIENTAÇÃO (Tudo que acontece antes da primeira escolha) ---
                sb.append("--- CAPÍTULO 1: O PARAÍSO PERDIDO ---\n\n");
                sb.append("(O sol está quente e gentil, filtrando-se pelas folhas das árvores...)\n");
                sb.append("(O ar possui um cheiro agradável de orvalho, terra úmida e pólen doce.)\n");
                sb.append("(Você ouve Pidgeys e o zumbido de um Beedrill. É música para seus ouvidos.)\n\n");
                
                sb.append("(Ao seu lado, um Sentret de pelo marrom-claro boceja. É o seu melhor amigo.)\n");
                sb.append("Sentret: 'Acorde " + jogador.getNome() + "! O dia está perfeito!'\n");
                sb.append("(Você olha a natureza ao redor. Este é o seu lar. Um refúgio.)\n\n");
                
                sb.append("Sentret: 'Rápido! As melhores Apricorns estão na Árvore Grande. Mas... estou com fome agora.'\n");
                sb.append("Vocês correm para o centro da clareira. O que fazer primeiro?\n\n");
                
                // Opções
                sb.append("1. [COMER] Vasculhar os arbustos de Oran Berries\n");
                sb.append("2. [BRINCAR] Pega-pega com Sentret\n");
                sb.append("3. [EXPLORAR] Investigar o riacho cintilante");
                
                etapa = 1; // Avança para esperar a resposta do usuário
                break;

            case 1: 
                // --- PROCESSAR ESCOLHA 1 ---
                if (input.equals("1")) {
                    sb.append("> Você escolheu COMER.\n");
                    sb.append("Você e Sentret farejam os arbustos... O cheiro doce é inconfundível!\n");
                    sb.append("O suco da fruta explode na sua boca. Perfeito. (HP MÁXIMO! +10 EXP)\n\n");
                    jogador.ganharExp(10);
                } else if (input.equals("2")) {
                    sb.append("> Você escolheu BRINCAR.\n");
                    sb.append("Você dá uma patada de leve em Sentret e dispara! Ele te persegue, rindo.\n");
                    sb.append("É uma explosão de energia e pura alegria. (+10 EXP)\n\n");
                    jogador.ganharExp(10);
                } else if (input.equals("3")) {
                    sb.append("> Você escolheu EXPLORAR.\n");
                    sb.append("A água fria e cristalina corre sobre suas patas.\n");
                    sb.append("Um Poliwag sonolento flutua, observando vocês com curiosidade. (+10 EXP)\n\n");
                    jogador.ganharExp(10);
                } else {
                    return "Opção inválida. Digite 1, 2 ou 3.";
                }

                // --- CENA 2: O ENCONTRO (Texto de transição) ---
                sb.append("(O sol está alto. Satisfeitos, vocês decidem ir para a colina das Apricorns.)\n");
                sb.append("Sentret: 'Ufa! Que dia! Vamos, as Apricorns ficam logo ali!'\n\n");
                
                sb.append("Vocês caminham alegres, mas de repente... você para.\n");
                sb.append("O cheiro mudou. Fumaça... metal... algo errado.\n");
                sb.append("Uma sombra cai sobre vocês. Um HUMANO.\n\n");
                
                sb.append("Ele é alto, usa um boné e tem um sorriso cruel.\n");
                sb.append("Ao lado dele, um QUILAVA (Nível 5) rosna, faíscas saindo de suas costas.\n");
                sb.append("Humano: 'Esse Sentret é fraco demais. Acabe com ele, Quil.'\n\n");
                
                sb.append("O Quilava avança. O Sentret grita por ajuda!\n");
                sb.append("O QUE VOCÊ FAZ?\n\n");
                
                sb.append("1. [LUTAR] Usar seu ataque mais forte\n");
                sb.append("2. [PROTEGER] Se jogar na frente do Sentret\n");
                sb.append("3. [FUGIR] Tentar correr para a grama alta");
                
                etapa = 2; // Avança para a próxima decisão
                break;

            case 2:
                // --- PROCESSAR ESCOLHA 2 (A Batalha Impossível) ---
                // Nota: Como estamos na Web, simplificamos a batalha scriptada aqui dentro
                
                if (input.equals("1")) {
                    sb.append("> Você escolheu LUTAR.\n");
                    sb.append("Você dispara seu ataque (ATK " + jogador.getAtk() + "), mas o Quilava desvia facilmente.\n");
                    sb.append("Ele te acerta com um Ataque Rápido. A dor é aguda.\n");
                } else if (input.equals("2")) {
                    sb.append("> Você escolheu PROTEGER.\n");
                    sb.append("Você pula na frente do Sentret. O Quilava te atinge com Brasa (Ember).\n");
                    sb.append("Seu pelo queima. Você grita de dor.\n");
                } else if (input.equals("3")) {
                    sb.append("> Você escolheu FUGIR.\n");
                    sb.append("Você tenta correr, mas o Quilava é muito rápido (AGI 7).\n");
                    sb.append("Ele bloqueia seu caminho num piscar de olhos.\n");
                } else {
                    return "Opção inválida. Digite 1, 2 ou 3.";
                }

                // --- CONCLUSÃO DO CAPÍTULO (Captura) ---
                sb.append("\n(Sua escolha foi fútil. A diferença de poder é muito grande.)\n");
                sb.append("Você está no chão (HP 1). O humano ri.\n");
                sb.append("Humano: 'Viu só? Selvagens não têm chance. Mas você... tem espírito.'\n\n");
                
                sb.append("Ele joga uma Pokébola. Uma luz vermelha te cega.\n");
                sb.append("Você ouve o grito aterrorizado do Sentret ao longe.\n");
                sb.append("Tudo fica escuro. O cheiro das Berries desaparece.\n");
                sb.append("...Clic.\n\n");
                
                sb.append("(Você foi capturado. A experiência traumática te forçou a endurecer.)\n");
                sb.append("[EXP +70 - BÔNUS DE SOBREVIVÊNCIA]\n");
                
                jogador.ganharExp(70); // Isso provavelmente vai dar Level Up
                
                sb.append("\n(Fim do Capítulo 1)");
                
                etapa = 99; // Finaliza o capítulo
                break;
        }

        return sb.toString();
    }
}