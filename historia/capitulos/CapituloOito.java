package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloOito extends Capitulo {

    private Inimigo inimigoAtivo; // Reutilizaremos para as duas batalhas

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
                sb.append("\n--- CAPÍTULO 8: A TORRE QUEIMADA ---\n");
                sb.append("(Status Atual: Nível " + jogador.getNivel() + ". HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                sb.append("(Ecruteak City. A velha torre se ergue contra o céu noturno chuvoso.)\n");
                sb.append("O lugar está cercado de cultistas. É uma fortaleza.\n");
                sb.append("Você entra por uma fenda nas ruínas.\n\n");

                sb.append("[SALA 1: O HALL DE ENTRADA]\n");
                sb.append("Dois guardas patrulham a escada para o subsolo com lanternas.\n");
                sb.append("O chão está cheio de escombros.\n");
                sb.append("COMO PASSAR?\n");
                sb.append("1. [FURTIVIDADE] (Tentar passar sem ser visto - Requer Agilidade ou Habilidade)\n");
                sb.append("2. [LUTAR] (Atacar os guardas de surpresa)");
                
                etapa = 1;
                break;

            case 1:
                // --- RESOLUÇÃO SALA 1 (ESCOLHA) ---
                if (input.equals("1")) {
                    // Teste de Furtividade
                    String habilidade = jogador.getHabilidadeExploracao();
                    boolean passouTeste = false;

                    // Verifica classes furtivas ou Agilidade alta (>= 7)
                    if (habilidade.equals("Voo Silencioso") || habilidade.equals("Aderência") || jogador.getAgi() >= 7) {
                        passouTeste = true;
                    }

                    if (passouTeste) {
                        sb.append("\n> SUCESSO!\n");
                        sb.append("Você usa " + habilidade + " (ou sua agilidade natural) para passar pelas sombras.\n");
                        sb.append("Os guardas passam direto por você.\n");
                        sb.append("(+30 EXP)\n");
                        jogador.ganharExp(30);
                        
                        avancarParaSala2(sb); // Pula a batalha
                    } else {
                        sb.append("\n> FALHA!\n");
                        sb.append("Você tenta ser silencioso, mas chuta uma pedra!\n");
                        sb.append("Guarda: \"Ei! Um intruso!\"\n");
                        iniciarBatalhaSala1(sb); // Força a batalha
                    }

                } else if (input.equals("2")) {
                    sb.append("\n> VOCÊ ESCOLHE A VIOLÊNCIA.\n");
                    iniciarBatalhaSala1(sb);
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;

            case 2:
                // --- LOOP DE BATALHA (SALA 1 - GUARDAS) ---
                return processarBatalha(input, jogador, 3, "Você caiu na entrada da torre.");

            case 3:
                // --- SALA 2: PRISIONEIROS ---
                sb.append("\n[SALA 2: O CORREDOR DOS PRISIONEIROS]\n");
                sb.append("Gaiolas alinham as paredes. Outros Pokémons capturados.\n");
                sb.append("Um Geodude, um Poliwag, um Pidgey. Eles te olham com esperança.\n\n");
                sb.append("O TEMPO É CURTO.\n");
                sb.append("1. [LIBERTAR] (Recupera HP, ganha EXP, mas gasta energia)\n");
                sb.append("2. [IGNORAR] (Focar na vingança. O Líder está perto)");
                
                etapa = 4;
                break;

            case 4:
                // --- RESOLUÇÃO SALA 2 ---
                if (input.equals("1")) {
                    sb.append("\n> ATO DE HEROÍSMO\n");
                    sb.append("Você corre para as gaiolas e quebra as travas!\n");
                    sb.append("Poliwag: \"Obrigado! Pegue isso!\"\n");
                    sb.append("(Eles te dão uma Sitrus Berry. HP TOTALMENTE RECUPERADO!)\n");
                    jogador.setHp(jogador.getHpMax());
                    jogador.ganharExp(60); 

                } else if (input.equals("2")) {
                    sb.append("\n> FOCO TOTAL\n");
                    sb.append("Você fecha os olhos para o sofrimento deles e corre.\n");
                    sb.append("O objetivo é a vingança, não o resgate.\n");
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }

                // Avança para a Sala 3 (Boss)
                sb.append("\nVocê ouve cânticos vindos da próxima sala...\n");
                
                // REMOVIDO: Util.aguardarEnter(); -> Não funciona dentro do processar()
                
                sb.append("\n[SALA 3: A ANTECÂMARA]\n");
                sb.append("Um cultista de manto vermelho guarda a porta final.\n");
                sb.append("Guarda de Elite: \"O Mestre não será interrompido! ARCANINE, QUEIME-O!\"\n");

                // Setup Boss
                this.inimigoAtivo = new Inimigo("Arcanine de Elite", 35, 10, 8, 9, 8);
                
                sb.append("\n--- BATALHA CONTRA CHEFE ---\n");
                sb.append("Inimigo: " + inimigoAtivo.getNome() + " (HP: " + inimigoAtivo.getHp() + ")\n");
                sb.append("1. [ATACAR]\n2. [PROTEGER]");
                
                etapa = 6; // Vai para batalha do Boss
                break;

            case 6: // Pulei o 5 para manter consistência
                // --- LOOP DE BATALHA (SALA 3 - ARCANINE) ---
                return processarBatalha(input, jogador, 99, 
                    "[FINAL RUIM 5: GUARDIÃO CAÍDO]\nVocê lutou bravamente contra a elite, mas não foi o bastante.\nVocê cai no chão de pedra da torre.");
        }

        return sb.toString();
    }

    // --- MÉTODOS AUXILIARES ---

    private void avancarParaSala2(StringBuilder sb) {
        sb.append("\nVocê avança silenciosamente para o próximo corredor.\n");
        sb.append("Pressione ENTER para continuar."); // Instrução visual apenas
        etapa = 3; // Vai para Sala 2
    }

    private void iniciarBatalhaSala1(StringBuilder sb) {
        sb.append("Um Machoke e um Hypno bloqueiam o caminho!\n");
        // Status mais fortes
        this.inimigoAtivo = new Inimigo("Machoke & Hypno", 25, 15, 8, 7, 5);
        sb.append("--- BATALHA INICIADA ---\n");
        sb.append("Inimigo: " + inimigoAtivo.getNome() + " (HP: " + inimigoAtivo.getHp() + ")\n");
        sb.append("1. [ATACAR]\n2. [PROTEGER]");
        etapa = 2; // Vai para loop de batalha 1
    }

    // Lógica genérica de batalha para reutilizar nas duas lutas
    private String processarBatalha(String input, Jogador jogador, int etapaVitoria, String msgDerrota) {
        StringBuilder sb = new StringBuilder();
        int danoPlayer = 0;
        boolean playerProtegeu = false;

        // 1. Turno Jogador
        if (input.equals("1")) {
            danoPlayer = jogador.atacar(inimigoAtivo);
            sb.append("> Você ataca! (Dano: " + danoPlayer + ")\n");
        } else if (input.equals("2")) {
            sb.append("> Você se protege.\n");
            playerProtegeu = true;
        } else {
            return "Opção inválida. Digite 1 ou 2.";
        }

        // 2. Vitória?
        if (!inimigoAtivo.estaVivo()) {
            sb.append("\nO inimigo foi derrotado!\n");
            jogador.ganharExp(etapaVitoria == 99 ? 100 : 60); // XP varia conforme a batalha
            
            if (etapaVitoria == 99) {
                // Fim do Capítulo
                sb.append("O Arcanine cai. O cultista foge, aterrorizado.\n");
                sb.append("A porta para o ritual está à sua frente.\n");
                sb.append("(Fim do Capítulo 8)");
                etapa = 99;
            } else {
                // Vai para Sala 2
                avancarParaSala2(sb);
                etapa = 3; 
            }
            return sb.toString();
        }

        // 3. Turno Inimigo
        if (playerProtegeu) {
            sb.append("O inimigo ataca, mas você defende o golpe.\n");
        } else {
            int danoInimigo = inimigoAtivo.atacar(jogador);
            sb.append(inimigoAtivo.getNome() + " contra-ataca! (Dano: " + danoInimigo + ")\n");
        }

        // 4. Derrota?
        if (jogador.getHp() <= 0) {
            sb.append("\n" + msgDerrota + "\n");
            sb.append("Fim de Jogo.");
            etapa = 99;
            return sb.toString();
        }

        // 5. Continua
        sb.append("\nSEU HP: " + jogador.getHp() + " | INIMIGO: " + inimigoAtivo.getHp() + "\n");
        sb.append("1. [ATACAR]\n2. [PROTEGER]");
        
        return sb.toString();
    }
}