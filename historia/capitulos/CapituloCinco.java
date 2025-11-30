package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloCinco extends Capitulo {

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- CENA INICIAL ---
                sb.append("\n--- CAPÍTULO 5: O SACRIFÍCIO ---\n\n");
                
                sb.append("(A porta da frente se abre. O humano acorda assustado no sofá.)\n");
                sb.append("Humano: \"V... vocês não deveriam vir aqui!\"\n\n");

                sb.append("Uma Voz Fria responde: \"Cale-se, Iniciado. Estamos aqui pela sua falha.\"\n");
                sb.append("Três pessoas em mantos escuros entram. O Símbolo está em seus capuzes.\n");
                sb.append("Um dos cultistas joga uma gaiola pequena no chão da sala.\n\n");

                sb.append("Seu coração para.\n");
                sb.append("Aquele cheiro. Você conhece.\n");
                sb.append("Dentro da gaiola, tremendo, está o SENTRET.\n");
                sb.append("Seu melhor amigo do Capítulo 1.\n\n");

                sb.append("Sentret: \"" + jogador.getNome() + "...? Onde... onde estou? Estou com medo...\"\n");
                sb.append("(Ele te fareja, mas não consegue te ver).\n\n");
                
                sb.append("Os cultistas desenham um círculo no chão e colocam o Sentret no centro.\n");
                sb.append("Voz Fria: \"Um sacrifício puro para apagar o fracasso. Trará grande poder para o Mestre.\"\n\n");

                sb.append("Eles começam a cantar. O humano (seu treinador) está no canto, de olhos fechados, covarde.\n");
                sb.append("O Sentret está chorando. Eles estão distraídos.\n");
                sb.append("Você poderia atacar. Mas são três. E eles têm Pokébolas na cintura.\n\n");

                sb.append("O QUE VOCÊ FAZ?\n");
                sb.append("1. [TENTAR SALVAR O AMIGO] (Você não pode deixar isso acontecer! Ataque!)\n");
                sb.append("2. [OBSERVAR, IMPOTENTE] (Se você atacar, ambos morrerão. Você precisa sobreviver.)");

                etapa = 1; // Aguarda a escolha fatal
                break;

            case 1:
                if (input.equals("1")) {
                    // --- CAMINHO A: TENTAR SALVAR (FINAL RUIM 3) ---
                    sb.append("\n> Você escolheu SALVAR O AMIGO.\n");
                    sb.append("Você explode do seu esconderijo com um grito de guerra!\n");
                    sb.append("\"SENTRET!\"\n");
                    sb.append("Você usa seu ataque mais forte no cultista mais próximo.\n\n");
                    
                    sb.append("O Sentret te vê. Os olhos dele brilham de esperança: \"[NOME]!\"\n");
                    sb.append("Por um segundo, você acha que pode funcionar.\n\n");
                    
                    sb.append("Voz Fria: \"Um inseto.\"\n");
                    sb.append("Ele joga uma Pokébola casualmente. Um GENGAR aparece das sombras.\n");
                    sb.append("Antes que você possa reagir, o Gengar te acerta com um Raio Sombrio (Shadow Ball).\n\n");
                    
                    sb.append("(A dor é absoluta. Seu HP cai para 0 instantaneamente.)\n");
                    jogador.setHp(0);
                    
                    sb.append("Voz Fria: \"Segure-o. Que ele assista.\"\n");
                    sb.append("O Gengar te prende no chão. Você não consegue se mexer.\n");
                    sb.append("Você vê o ritual terminar. Você vê a luz roxa engolir seu amigo.\n\n");
                    
                    sb.append("[FINAL RUIM 3: O CULPADO]\n");
                    sb.append("Você falhou. O ritual foi concluído.\n");
                    sb.append("O Sentret desapareceu. E você foi capturado pelo Culto para 'reeducação'.\n");
                    sb.append("Sua bravura foi nobre, mas imprudente. O mundo é cruel com os heróis fracos.\n\n");
                    sb.append("Fim de Jogo.");
                    
                    etapa = 99; // Fim

                } else if (input.equals("2")) {
                    // --- CAMINHO B: OBSERVAR (CAMINHO DA VINGANÇA) ---
                    sb.append("\n> Você escolheu OBSERVAR.\n");
                    sb.append("Você... trava seus músculos. Você enfia as garras no chão para não gritar.\n");
                    sb.append("Você fecha os olhos. Lágrimas silenciosas escorrem pelo seu rosto.\n\n");
                    
                    sb.append("Você ouve o canto ficar mais alto.\n");
                    sb.append("Sentret: \"" + jogador.getNome() + "... socorro...\"\n");
                    sb.append("Então... um clarão de luz roxa. Um som de vácuo.\n");
                    sb.append("...e silêncio.\n\n");

                    sb.append("Você abre os olhos. O Sentret se foi.\n");
                    sb.append("Seu coração se parte em mil pedaços. O paraíso do Capítulo 1 está morto.\n");
                    sb.append("Mas algo nasce no lugar. Um ódio frio. Profundo.\n\n");
                    
                    sb.append("Voz Fria: \"O poder foi liberado. O Mestre precisa da 'Grande Arma' na Torre Queimada.\"\n");
                    sb.append("Eles saem, deixando o humano vomitando no chão de medo.\n\n");
                    
                    sb.append("Você fica no escuro por horas, tremendo de raiva.\n");
                    sb.append("Você tem um nome: TORRE QUEIMADA.\n");
                    sb.append("Você tem um objetivo: VINGANÇA.\n\n");
                    
                    sb.append("(Bônus de Sobrevivência Dolorosa: +30 EXP)\n");
                    sb.append("(Bônus de Capítulo: +50 EXP)\n");
                    jogador.ganharExp(30);
                    jogador.ganharExp(50);
                    
                    sb.append("\n(Fim do Capítulo 5.)");
                    etapa = 99; // Continua para o Cap 6

                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;
        }

        return sb.toString();
    }
}