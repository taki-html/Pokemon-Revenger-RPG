package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloSeis extends Capitulo {

    private Inimigo raticateInimigo; // Para a batalha opcional

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- INTRODUÇÃO ---
                sb.append("\n--- CAPÍTULO 6: O PLANO ---\n");
                sb.append("(Status Atual: Nível " + jogador.getNivel() + ". HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                
                sb.append("(Sua mentalidade mudou. Você não obedece mais por medo. Você obedece para observar.)\n");
                sb.append("(Seu treinador tornou-se imprudente. Ele sabe que falhou e está desesperado.)\n\n");

                // --- MINI-DESAFIO 1: O MAPA ---
                boolean temMapa = jogador.temPista("MAPA DE JOHTO");

                if (!temMapa) {
                    sb.append("(Cena: O Banheiro do Parque)\n");
                    sb.append("O humano deixa a bolsa no chão para ir ao banheiro público.\n");
                    sb.append("Você vê um mapa saindo dela. É sua chance de saber para onde eles vão.\n\n");

                    sb.append("O QUE VOCÊ FAZ?\n");
                    sb.append("1. [AGARRAR RÁPIDO] (Correr, pegar e voltar. Arriscado.)\n");
                    sb.append("2. [DERRUBAR VASO] (Fazer barulho para distraí-lo mais tempo.)");
                    
                    etapa = 1; // Vai processar a escolha do mapa
                } else {
                    sb.append("(Você já memorizou o mapa no apartamento. Você sabe que o destino é a Torre Queimada.)\n");
                    sb.append("(Você se sente um passo à frente.)\n\n");
                    
                    // Pula direto para a cena do parque (Etapa 2)
                    etapa = 2;
                    sb.append(processar("", jogador)); // Chama recursivamente a próxima parte
                }
                break;

            case 1:
                // --- RESOLUÇÃO DO MAPA ---
                if (input.equals("1")) {
                    sb.append("\n> Você AGARRA RÁPIDO.\n");
                    sb.append("Você corre, puxa o papel com os dentes e volta para a gaiola.\n");
                } else if (input.equals("2")) {
                    sb.append("\n> Você DERRUBA O VASO.\n");
                    sb.append("Você derruba um vaso. O barulho ecoa. Enquanto ele se assusta, você puxa o mapa.\n");
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                
                sb.append("Você consegue ler rapidamente: 'RITUAL FINAL: TORRE QUEIMADA - ECRUTEAK'.\n");
                sb.append("[PISTA ADQUIRIDA: MAPA DE JOHTO]\n");
                sb.append("(+20 EXP)\n");
                jogador.adicionarPista("MAPA DE JOHTO");
                jogador.ganharExp(20);

                // Avança para a cena do parque
                etapa = 2;
                sb.append(processar("", jogador)); // Chama a próxima cena imediatamente
                break;

            case 2:
                // --- MINI-DESAFIO 2: A CONVERSA (ESPIONAGEM) ---
                // Verifica se veio de recursão para não adicionar quebras de linha extras desnecessárias
                if (!sb.toString().endsWith("\n\n")) sb.append("\n\n");
                
                sb.append("(Cena: O Parque da Cidade)\n");
                sb.append("Seu treinador te força a uma batalha de treino com outro cultista encapuzado.\n");
                sb.append("Cultista: \"Você parece fraco, Ketch. O Mestre não tolera fraqueza.\"\n");
                sb.append("Cultista: \"Vamos ver se seu Pokémon vale o ar que respira.\"\n\n");
                
                sb.append("(O Cultista libera um Raticate agressivo.)\n");
                sb.append("(Você percebe algo: Eles estão cochichando sobre o ritual. Se você vencer rápido, eles se calam.)\n");
                sb.append("(Se você parecer fraco e perder, eles vão baixar a guarda e conversar.)\n\n");

                sb.append("ESTRATÉGIA DE BATALHA:\n");
                sb.append("1. [LUTAR COM TUDO] (Mostre sua força! Mas eles ficarão desconfiados.)\n");
                sb.append("2. [PERDER DE PROPÓSITO] (Pareça patético. Deixe-os falar.)");
                
                etapa = 3; // Aguarda decisão da estratégia
                break;

            case 3:
                if (input.equals("1")) {
                    // --- CAMINHO A: LUTAR (Inicia Batalha) ---
                    sb.append("\n> Você escolheu LUTAR COM TUDO.\n");
                    
                    this.raticateInimigo = new Inimigo("Raticate do Culto", 20, 6, 4, 6, 4);
                    
                    sb.append("!!! BATALHA INICIADA !!!\n");
                    sb.append("Inimigo: " + raticateInimigo.getNome() + " (HP: " + raticateInimigo.getHp() + ")\n");
                    
                    sb.append("1. [ATACAR]\n");
                    sb.append("2. [PROTEGER]");
                    
                    etapa = 4; // Loop de Batalha

                } else if (input.equals("2")) {
                    // --- CAMINHO B: PERDER DE PROPÓSITO (Narrativo) ---
                    sb.append("\n> Você escolheu PERDER DE PROPÓSITO.\n");
                    sb.append("Você finge tropeçar. Você erra seus ataques de propósito.\n");
                    sb.append("O Raticate te morde. Dói, mas você aguenta. (Você perde 5 HP na encenação).\n");
                    jogador.receberDano(5);
                    
                    sb.append("Cultista: \"Patético. Como seu treinador.\"\n");
                    sb.append("Eles te deixam de lado, jogado na grama. E então, começam a falar.\n\n");
                    
                    sb.append("Cultista: \"...sim, o Iniciado trará o talismã menor.\"\n");
                    sb.append("Cultista: \"Esteja na Torre Queimada em TRÊS DIAS. O Mestre começará o ritual ao anoitecer.\"\n\n");
                    
                    sb.append("[PISTA ADQUIRIDA: DATA DO RITUAL]\n");
                    sb.append("(Você obteve a informação completa: ONDE e QUANDO.)\n");
                    sb.append("(+50 EXP de Estratégia)\n");
                    jogador.adicionarPista("DATA DO RITUAL");
                    jogador.ganharExp(50);
                    
                    sb.append("\n(Fim do Capítulo 6.)");
                    sb.append("(+50 EXP de Capítulo)\n");
                    jogador.ganharExp(50);
                    etapa = 99; // Fim
                    
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;

            case 4:
                // --- LOOP DE BATALHA (RATICATE) ---
                // Lógica simples: Player bate -> Inimigo bate (se vivo)
                
                int danoPlayer = 0;
                boolean playerProtegeu = false;

                if (input.equals("1")) {
                    danoPlayer = jogador.atacar(raticateInimigo);
                    sb.append("> Você ataca o Raticate! (Dano: " + danoPlayer + ")\n");
                } else if (input.equals("2")) {
                    sb.append("> Você se protege.\n");
                    playerProtegeu = true;
                } else {
                    return "Opção de batalha inválida.";
                }

                // Checa vitória
                if (!raticateInimigo.estaVivo()) {
                    sb.append("\nO Raticate cai derrotado.\n");
                    sb.append("Cultista: \"Hmph. Forte. Mas arrogante.\"\n");
                    sb.append("Ele recolhe o Raticate e se afasta, desconfiado. Você não ouviu a conversa.\n");
                    sb.append("(Você terá que confiar apenas no mapa.)\n");
                    
                    sb.append("(+30 EXP de Batalha)\n");
                    jogador.ganharExp(30);
                    
                    sb.append("\n(Fim do Capítulo 6.)");
                    sb.append("(+50 EXP de Capítulo)\n");
                    jogador.ganharExp(50);
                    etapa = 99;
                    return sb.toString();
                }

                // Turno do Inimigo
                if (playerProtegeu) {
                    sb.append("O Raticate ataca, mas você bloqueia a maior parte do dano.\n");
                } else {
                    int danoInimigo = raticateInimigo.atacar(jogador);
                    sb.append("O Raticate usa Mordida! (Dano: " + danoInimigo + ")\n");
                }

                // Status e Loop
                sb.append("SEU HP: " + jogador.getHp() + " | RATICATE HP: " + raticateInimigo.getHp() + "\n");
                
                if (jogador.getHp() <= 0) {
                    sb.append("\nVocê desmaiou durante o treino...\n");
                    sb.append("[GAME OVER]\n");
                    etapa = 99;
                } else {
                    sb.append("1. [ATACAR]\n2. [PROTEGER]");
                    // Mantém etapa 4
                }
                break;
        }

        return sb.toString();
    }
}