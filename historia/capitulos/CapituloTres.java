package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloTres extends Capitulo {

    private Inimigo quilava;
    private int turnosHesitacao = 0; // Contador para o final "Lealdade"

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        // Proteção contra input nulo do front-end
        if (input == null) {
            input = "";
        }

        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0: // --- CENA 1: INTRODUÇÃO ---
                sb.append("--- CAPÍTULO 3: O ENCONTRO ---\n");
                sb.append(String.format("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n\n",
                        jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel()));

                sb.append("Você está sendo carregado na gaiola. O humano ('Ketch') está suando e visivelmente nervoso.\n");
                sb.append("Ele te leva para um prédio abandonado e te deixa no corredor.\n");
                sb.append("(O Quilava está com ele, parecendo tão nervoso quanto o mestre.)\n");
                
                sb.append("\n[Digite algo para continuar]");
                etapa = 1;
                break;

            case 1: // --- CENA 2: A SAÍDA DE KETCH ---
                sb.append("Ketch: \"Quil, vigie a 'mercadoria'. Eu já volto. E FIQUE QUIETO.\"\n\n");
                sb.append("(Ele entra em uma sala. Você ouve vozes abafadas. A trava da gaiola ainda está frouxa, como o Quilava deixou.)\n");
                sb.append("(O Quilava te olha. Ele parece implorar para que você não faça barulho.)\n");
                
                sb.append("\nO QUE VOCÊ FAZ?\n");
                sb.append("1. [ESPIAR A REUNIÃO] (Você precisa saber o que está acontecendo.)\n");
                sb.append("2. [FICAR QUIETO E OUVIR] (É muito arriscado se mover. É melhor só escutar.)");
                etapa = 2; // Aguarda decisão de espionagem
                break;

            case 2: // --- CENA 3: RESULTADO DA ESCOLHA ---
                if (input.equals("1")) {
                    // CAMINHO A: ESPIAR
                    sb.append("\n> Você empurra a trava. Clic. Você pisa fora da gaiola.\n");
                    sb.append("(O Quilava treme, mas não faz nada para te impedir.)\n");
                    sb.append("Você se esgueira até a fresta da porta.\n\n");
                    
                    sb.append("(Você vê 'Ketch' falando com uma mulher alta, de uniforme preto e um sorriso frio.)\n");
                    sb.append("Mulher: \"...e sua 'mercadoria' está ferida, Ketch. Você é um incompetente.\"\n");
                    sb.append("Ketch entrega uma caixa com Pokébolas marcadas com um SÍMBOLO estranho.\n");
                    
                    sb.append("\n(PISTA ADICIONADA: SÍMBOLO DA EQUIPE)\n");
                    jogador.adicionarPista("SÍMBOLO DA EQUIPE");
                    jogador.ganharExp(30);

                    sb.append("(Você corre de volta para a gaiola ao ouvir passos!)\n");

                } else if (input.equals("2")) {
                    // CAMINHO B: OUVIR
                    sb.append("\n> Você se encolhe no fundo da gaiola. O medo é maior.\n");
                    sb.append("(O Quilava solta um suspiro de alívio.)\n");
                    sb.append("Você se concentra nas vozes abafadas...\n\n");
                    
                    sb.append("Voz (Ketch): \"...Eu juro, ele é forte! Ele tem o espírito!\"\n");
                    sb.append("Voz (Fria): \"Sua 'mercadoria' parece patética. O *Projeto Trovão* não existe. Você está dispensado.\"\n");
                    sb.append("Voz (Ketch): \"Não! Espere! Eu posso provar!\"\n");
                    
                    sb.append("\n(PISTA ADICIONADA: PROJETO TROVÃO)\n");
                    jogador.adicionarPista("PROJETO TROVÃO");
                    jogador.incrementarInacao(); // Salva a inação
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                
                sb.append("\n[Digite algo para esperar o clímax]");
                etapa = 3;
                break;

            case 3: // --- CENA 4: O CLÍMAX ---
                sb.append("A porta da sala se abre com um estrondo.\n");
                sb.append("A Mulher do Uniforme sai, te olhando com desprezo.\n");
                sb.append("Ketch a segue, pálido e tremendo de raiva.\n\n");
                
                sb.append("Ketch: \"Fraco? Você me acha fraco?\"\n");
                sb.append("Ketch: \"EU VOU TE MOSTRAR QUEM É FRACO!\"\n");
                
                sb.append("\n[Digite algo...]");
                etapa = 4;
                break;

            case 4: // --- CENA 5: O COMANDO ---
                sb.append("(Ele chuta sua gaiola e a abre violentamente.)\n");
                sb.append("Ketch: \"SAIA! Quilava! POSIÇÃO DE BATALHA!\"\n\n");
                sb.append("O Quilava te encara, com as chamas baixas. Ele está apavorado.\n");
                sb.append("Ketch: \"A 'mercadoria' precisa de um teste final. ATAQUE O QUILAVA. AGORA!\"\n");
                
                // Inicializa o inimigo para controle interno (simulação)
                this.quilava = new Inimigo("Quilava Assustado", 20, 5, 5, 7, 0);
                
                sb.append("\n[Digite algo para entrar em combate]");
                etapa = 5;
                break;

            case 5: // --- CENA 6: MENU DE BATALHA (SIMULADO) ---
                sb.append("\n--- COMBATE INICIADO ---\n");
                sb.append("Ketch está gritando ordens. Quilava está hesitante.\n");
                sb.append("Quilava HP: " + quilava.getHp() + "\n");
                
                sb.append("\nO QUE VOCÊ FAZ?\n");
                sb.append("1. [ATACAR] (Obedecer Ketch e ferir Quilava)\n");
                sb.append("2. [HESITAR/PROTEGER] (Recusar-se a lutar)");
                
                etapa = 6; // Loop de batalha
                break;

            case 6: // --- CENA 7: LÓGICA DE COMBATE ---
                if (input.equals("1")) {
                    // --- OPÇÃO: ATACAR ---
                    int dano = jogador.getAtaque(); // Dano base do jogador
                    quilava.receberDano(dano);
                    
                    sb.append("\n> Você avança e ataca o Quilava!\n");
                    sb.append("O Quilava grita de dor. Ele não revida. (Dano: " + dano + ")\n");
                    sb.append("Ketch: \"ISSO! MAIS FORTE!\"\n");

                    if (!quilava.estaVivo()) {
                        // Quilava derrotado -> CAMINHO TRAIÇÃO
                        sb.append("\n(O Quilava cai no chão, nocauteado.)\n");
                        sb.append("[Digite algo para ver o resultado]");
                        etapa = 7; // Final Ruim/Traição
                    } else {
                        // Continua a batalha
                        sb.append("\nEle ainda está de pé, mas fraco.\n");
                        sb.append("[Digite algo para o próximo turno]");
                        etapa = 5; // Volta pro menu
                    }

                } else if (input.equals("2")) {
                    // --- OPÇÃO: HESITAR ---
                    turnosHesitacao++;
                    sb.append("\n> Você se recusa a atacar. Você encara Ketch.\n");
                    
                    if (turnosHesitacao >= 2) {
                        // Hesitou o suficiente -> CAMINHO LEALDADE
                        sb.append("Ketch: \"INÚTIL! EU VOU ACABAR COM ISSO EU MESMO!\"\n");
                        sb.append("[Digite algo para ver o resultado]");
                        etapa = 8; // Final Bom/Lealdade
                    } else {
                        sb.append("Ketch: \"O QUE ESTÁ ESPERANDO? EU MANDEI MATAR!\"\n");
                        sb.append("A Mulher observa, impaciente.\n");
                        sb.append("[Digite algo para o próximo turno]");
                        etapa = 5; // Volta pro menu
                    }
                } else {
                    return "Opção inválida. Digite 1 para Atacar ou 2 para Hesitar.";
                }
                break;

            case 7: // --- CENA 8: CONCLUSÃO (TRAIÇÃO) ---
                sb.append("Ketch: \"VIU SÓ? VIU O PODER? EU DISSE!\"\n");
                sb.append("A Mulher: \"...Interessante. Talvez. Traga-o.\"\n\n");
                sb.append("(Ela vai embora. Ketch te joga na gaiola com um sorriso cruel.)\n");
                sb.append("Ketch: \"Você... você é forte. Você vai me fazer ser respeitado.\"\n\n");
                
                sb.append("(Você traiu seu único aliado. EXP +50)\n");
                jogador.adicionarTraicao(1);
                jogador.ganharExp(50);
                
                sb.append("\n--- FIM DO CAPÍTULO 3 ---");
                etapa = 99;
                break;

            case 8: // --- CENA 9: CONCLUSÃO (LEALDADE) ---
                sb.append("A Mulher: \"Patético. Ketch, seu tempo acabou.\"\n");
                sb.append("(Ela se vira para ir embora.)\n\n");
                sb.append("Ketch: \"NÃO! É CULPA SUA!\"\n");
                sb.append("(Ele levanta a bota pesada para chutar sua cabeça.)\n");
                sb.append("(VULT! O Quilava salta na frente, recebendo o golpe brutal e protegendo você!)\n\n");
                
                sb.append("A Mulher para. \"...Basta.\" (Ela vai embora, rindo.)\n");
                sb.append("Ketch cai de joelhos, derrotado. \"Você... você arruinou tudo!\"\n\n");
                
                sb.append("(Você protegeu seu aliado. EXP +70)\n");
                jogador.adicionarLealdade(1);
                jogador.ganharExp(70);
                
                sb.append("\n--- FIM DO CAPÍTULO 3 ---");
                etapa = 99;
                break;
        }

        return sb.toString();
    }
}