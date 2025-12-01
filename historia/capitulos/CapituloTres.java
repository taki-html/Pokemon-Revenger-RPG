package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloTres extends Capitulo {

    @Override
    public String processar(String input, Jogador jogador) {

        // --- INÍCIO ---
        if (etapa == 0) {
            etapa++;
            return "--- CAPÍTULO 3: O ENCONTRO ---\n" +
                   "(Status Atual: Nível " + jogador.getNivel() + " | HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n" +
                   "(Pressione Enter)";
        }

        if (etapa == 1) {
            etapa++;
            return "Você está sendo carregado na gaiola. O humano ('Ketch') está suando e visivelmente nervoso.\n" +
                   "Ele te leva para um prédio abandonado e te deixa no corredor.\n" +
                   "(O Quilava está com ele, parecendo tão nervoso quanto o mestre.)";
        }

        if (etapa == 2) {
            etapa++;
            return "Ketch: \"Quil, vigie a 'mercadoria'. Eu já volto. E FIQUE QUIETO.\"\n" +
                   "(Ele entra em uma sala. Você ouve vozes abafadas. A trava da gaiola ainda está frouxa.)\n" +
                   "(O Quilava te olha. Ele parece implorar para que você não faça barulho.)";
        }

        if (etapa == 3) {
            etapa++;
            return "O QUE VOCÊ FAZ?\n" +
                   "1. [ESPIAR A REUNIÃO] (Você precisa saber o que está acontecendo.)\n" +
                   "2. [FICAR QUIETO E OUVIR] (É muito arriscado se mover. É melhor só escutar.)";
        }

        // --- ESCOLHA DE EXPLORAÇÃO ---

        if (etapa == 4) {
            if (input.equals("1")) {
                // CAMINHO A: ESPIAR
                etapa = 10; // Pula para rota 1
                return "Você empurra a trava. Clic. Você pisa fora da gaiola.\n" +
                       "(O Quilava treme, mas não faz nada para te impedir.)\n" +
                       "Você se esgueira até a fresta da porta.\n\n" +
                       "(Pressione Enter)";
            } else if (input.equals("2")) {
                // CAMINHO B: OUVIR
                etapa = 20; // Pula para rota 2
                return "Você se encolhe no fundo da gaiola. O medo é maior.\n" +
                       "(O Quilava solta um suspiro de alívio.)\n" +
                       "Você se concentra nas vozes abafadas...\n\n" +
                       "(Pressione Enter)";
            } else {
                return "Escolha inválida. Digite 1 ou 2.";
            }
        }

        // --- ROTA A: ESPIAR (10+) ---
        if (etapa == 10) {
            etapa = 30; // Converge para o clímax
            
            // Lógica de Pistas
            if (!jogador.temPista("SÍMBOLO DA EQUIPE")) {
                jogador.adicionarPista("SÍMBOLO DA EQUIPE");
                jogador.ganharExp(30);
            }

            return "(Você vê 'Ketch' falando com uma mulher alta, de uniforme preto e um sorriso frio.)\n" +
                   "Mulher: \"...e sua 'mercadoria' está ferida, Ketch. Você é um incompetente.\"\n" +
                   "Ketch: \"Mas ele é forte! Ele tem o espírito!\"\n" +
                   "Mulher: \"Espírito não paga as contas. Onde está o pagamento?\"\n" +
                   "(Ketch entrega a ela uma pequena caixa. Você vê o que tem dentro: Pokébolas... marcadas com um SÍMBOLO estranho.)\n" +
                   "Mulher: \"Bom. Agora, o teste final. Traga-o.\"\n\n" +
                   "(Você corre de volta para a gaiola, fechando a porta a tempo!)\n" +
                   "[PISTA ADQUIRIDA: SÍMBOLO DA EQUIPE]";
        }

        // --- ROTA B: OUVIR (20+) ---
        if (etapa == 20) {
            etapa = 30; // Converge para o clímax
            
            if (!jogador.temPista("PROJETO TROVÃO")) {
                jogador.adicionarPista("PROJETO TROVÃO");
                jogador.incrementarInacao();
            }

            return "Voz (Ketch): \"...Eu juro, ele é forte! Ele tem o espírito!\"\n" +
                   "Voz (Fria): \"Sua 'mercadoria' parece patética, Ketch. Ele está ferido. Você falhou na captura.\"\n" +
                   "Voz (Ketch): \"Mas... mas o símbolo! Eu vi! O Símbolo do Trovão!\"\n" +
                   "Voz (Fria): \"Isso é lenda. O *Projeto Trovão* não existe. Você está dispensado.\"\n" +
                   "Voz (Ketch): \"Não! Espere! Eu posso provar! Eu vou provar!\"\n\n" +
                   "[PISTA ADQUIRIDA: PROJETO TROVÃO]";
        }

        // --- CLÍMAX (30+) ---

        if (etapa == 30) {
            etapa++;
            return "(Clímax do Capítulo)\n" +
                   "A porta da sala se abre com um estrondo.\n" +
                   "A Mulher do Uniforme sai, te olhando com desprezo.\n" +
                   "Ketch a segue, pálido e tremendo de raiva.";
        }

        if (etapa == 31) {
            etapa++;
            return "Ketch: \"Fraco? Você me acha fraco? Você acha minha captura 'patética'?\"\n" +
                   "Ketch: \"EU VOU TE MOSTRAR QUEM É FRACO!\"";
        }

        if (etapa == 32) {
            etapa++;
            return "(Ele chuta sua gaiola e a abre.)\n" +
                   "Ketch: \"SAIA!\"\n" +
                   "Ketch: \"Quilava! POSIÇÃO DE BATALHA!\"";
        }

        if (etapa == 33) {
            etapa++;
            return "O Quilava te encara, com as chamas baixas. Ele está apavorado.\n" +
                   "Ketch: \"A 'mercadoria' precisa de um teste final. ATAQUE O QUILAVA. Mostre a ela do que você é capaz! AGORA!\"\n\n" +
                   "Prepare-se para lutar... ou não.";
        }

        // --- GATILHO DA BATALHA ---
        if (etapa == 34) {
            etapa++;
            return "[BATALHA]";
        }

        // --- PÓS-BATALHA ---
        // O Jogo.java vai chamar processar("QUILAVA_MORREU", jogador) ou "QUILAVA_VIVO"
        // Mas como Jogo.java é genérico, ele vai chamar processar("", jogador) normal.
        // Precisamos saber o resultado.
        
        // TRUQUE: Vamos usar o input para saber o resultado.
        // O Jogo.java precisará ser atualizado para mandar o resultado da batalha no input?
        // NÃO. O Jogo.java manda o input do usuário.
        
        // Vamos assumir que a Etapa 35 recebe o veredicto.
        if (etapa == 35) {
            // Se o input contém uma flag especial do sistema (vamos configurar isso no Jogo.java)
            if (input.equals("VITORIA_POR_MORTE")) {
                etapa = 100; // Rota Traição
            } else {
                etapa = 200; // Rota Lealdade
            }
            // Precisamos retornar algo para processar o texto da próxima etapa imediatamente?
            // Não, o input aqui foi técnico. Vamos chamar recursivamente ou pedir Enter.
            return "(A poeira baixa...)\n(Pressione Enter)";
        }

        // --- ROTA TRAIÇÃO (100+) ---
        if (etapa == 100) {
            etapa = 300; // Finalizar
            jogador.adicionarTraicao(1);
            jogador.ganharExp(50);
            return "(O Quilava está caído no chão, nocauteado. Ele não revidou.)\n" +
                   "Ketch: \"VIU SÓ? VIU O PODER? EU DISSE!\"\n" +
                   "A Mulher: \"...Interessante. Talvez. Traga-o.\"\n" +
                   "(Ela vai embora. Ketch te joga na gaiola.)\n" +
                   "Ketch: \"Você... você é forte. Você vai me fazer ser respeitado.\"\n\n" +
                   "(Você traiu seu único aliado. EXP + 50)";
        }

        // --- ROTA LEALDADE (200+) ---
        if (etapa == 200) {
            etapa = 300; // Finalizar
            jogador.adicionarLealdade(1);
            jogador.ganharExp(70);
            return "(Você se recusou a lutar.)\n" +
                   "Ketch: \"O QUE VOCÊ ESTÁ FAZENDO? LUTE! SEU LIXO INÚTIL!\"\n" +
                   "A Mulher: \"Patético. Ketch, seu tempo acabou.\"\n" +
                   "(Ela se vira para ir embora.)\n" +
                   "Ketch: \"NÃO! É CULPA SUA!\" (Ele levanta a bota para chutar sua gaiola.)\n" +
                   "(O Quilava salta na frente, recebendo o golpe e protegendo você!)\n" +
                   "A Mulher: \"...Basta.\" (Ela vai embora, rindo.)\n" +
                   "Ketch: \"Você... você arruinou tudo!\"\n\n" +
                   "(Você protegeu seu aliado. EXP + 70)";
        }

        if (etapa == 300) {
            etapa++;
            return "(Fim do Capítulo 3)";
        }

        return "";
    }

    @Override
    public boolean estaFinalizado() {
        return etapa > 300;
    }
}