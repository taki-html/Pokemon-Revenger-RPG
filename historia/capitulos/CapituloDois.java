package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloDois extends Capitulo {

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        // Proteção contra input nulo/vazio do front-end
        if (input == null) {
            input = "";
        }

        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0: // --- CENA 1: ACORDANDO NO ESCURO ---
                // Força o HP do jogador para 1 (continua da captura)
                jogador.setHp(1);

                sb.append("--- CAPÍTULO 2: A GAIOLA ---\n\n");
                sb.append("(A tela está preta.)\n");
                sb.append("...frio... ...metal... ...dor...\n\n");
                sb.append("A dor é a primeira coisa que você sente. (HP: " + jogador.getHp() + "/" + jogador.getHpMax() + " [PERIGO])\n");
                sb.append("Você força seus olhos a abrirem. Você está em uma gaiola de metal, em um porão úmido.\n");
                
                sb.append("\n[Digite algo para olhar ao redor]");
                etapa = 1;
                break;

            case 1: // --- CENA 2: O HUMANO ---
                sb.append("(Passos na escada. A porta range. É o humano.)\n");
                sb.append("Humano: \"Ah, o 'prêmio' acordou. Ótimo.\"\n\n");
                sb.append("(Ele não chuta a gaiola. Ele a abre.)\n");
                sb.append("Humano: \"Quil. Hora de comer.\"\n");
                sb.append("(Ele joga uma tigela com ração dura e cinzenta no chão de concreto.)\n");
                sb.append("Humano: \"Coma. Você precisa parecer forte para o cliente. Não me faça te forçar.\"\n");
                
                sb.append("\n[Digite algo para continuar]");
                etapa = 2;
                break;

            case 2: // --- CENA 3: A AMEAÇA ---
                sb.append("(Você está com 1 HP. A dor é imensa. Você mal consegue se mover, quanto mais comer.)\n");
                sb.append("Humano: \"Hmph. Patético. Espírito selvagem inútil.\"\n");
                sb.append("Humano: \"Quil, fique de olho. Se não comer em cinco minutos, use um Brasa de aviso. Precisamos quebrar essa teimosia.\"\n");
                
                sb.append("\n[Digite algo para esperar o humano sair]");
                etapa = 3;
                break;

            case 3: // --- CENA 4: QUILAVA ---
                sb.append("(O humano sobe as escadas. Silêncio. É só você e o Quilava.)\n");
                sb.append("(O Quilava te encara. As chamas em suas costas queimam baixas, incertas.)\n");
                sb.append("(Ele olha para a ração, depois para você. Ele solta um pequeno bufo de fumaça.)\n");
                sb.append("(Ele cutuca a tigela com o focinho, empurrando-a para mais perto. Você não se move.)\n");
                
                sb.append("\n[Digite algo...]");
                etapa = 4;
                break;

            case 4: // --- CENA 5: A ORAN BERRY ---
                sb.append("(O Quilava olha para a escada, nervoso. Então, com um movimento rápido, empurra algo para sua gaiola.)\n");
                sb.append("(Não é a ração. É uma... Oran Berry, amassada e pela metade.)\n\n");
                sb.append("(É a *dele*. Ele guardou para você.)\n");
                
                // Cura o jogador aqui
                jogador.setHp(10);
                sb.append("(Você come a Oran Berry. A dor diminui. HP recuperado para 10/" + jogador.getHpMax() + ")\n");
                
                sb.append("\n[Digite algo para agradecer]");
                etapa = 5;
                break;

            case 5: // --- CENA 6: A PORTA ABERTA (A ESCOLHA) ---
                sb.append("(O Quilava vê você comer. Ele se aproxima da sua gaiola.)\n");
                sb.append("(Com um 'clic' baixo, ele usa uma garra para empurrar a trava da sua porta, soltando-a.)\n");
                sb.append("(Ele então se vira e se deita no canto, fingindo dormir.)\n\n");
                sb.append("A porta está solta. A escada está logo ali. O Quilava te deu uma chance.\n");

                sb.append("\nO QUE VOCÊ FAZ?\n");
                sb.append("1. [FUGIR AGORA] (O Quilava te ajudou. Você deve aproveitar a chance!)\n");
                sb.append("2. [ESPERAR E OBSERVAR] (Por que ele te ajudou? Isso é um teste?)");
                
                etapa = 6; // Aguarda a decisão crítica
                break;

            case 6: // --- CENA 7: A CONSEQUÊNCIA ---
                if (input.equals("1")) {
                    // --- FINAL RUIM ---
                    sb.append("\nVocê empurra a porta da gaiola. O metal range baixo.\n");
                    sb.append("Você corre para as escadas. O cheiro da liberdade está perto.\n");
                    sb.append("(Mas no momento em que você pisa na rua, você ouve um grito do porão.)\n\n");
                    sb.append("Humano: \"O QUÊ? SEU... TRAIDOR INÚTIL!\"\n");
                    sb.append("(Um som alto de 'SMACK', e o grito de dor inconfundível do Quilava.)\n\n");
                    
                    sb.append("[FINAL RUIM 1: O TRAIDOR]\n");
                    sb.append("Você escapou. Você vive livre na floresta.\n");
                    sb.append("Mas você nunca esqueceu o som daquele grito.\n");
                    sb.append("Sua liberdade custou a vida do único que te ajudou.\n");
                    
                    etapa = 99; // Fim de Jogo (Bad End)

                } else if (input.equals("2")) {
                    // --- FINAL BOM (CONTINUAÇÃO) ---
                    sb.append("\nNão. Se você fugir, ele vai pagar o preço.\n");
                    sb.append("Você puxa a trava de volta e se encolhe no canto, fingindo estar fraco.\n\n");
                    sb.append("(O Quilava abre um olho. Ele vê o que você fez. Ele te entende.)\n");
                    sb.append("(Uma aliança silenciosa foi formada.)\n\n");
                    
                    sb.append("O humano desce e vê a comida intacta (a Berry sumiu).\n");
                    sb.append("Humano: \"Hã. Comeu, é? Bom. Viu, Quil? Medo funciona.\"\n");
                    sb.append("Humano: \"Levanta. Temos um encontro.\"\n");

                    sb.append("\n(EXP +50 por lealdade)\n");
                    jogador.ganharExp(50);
                    
                    sb.append("--- FIM DO CAPÍTULO 2 ---");
                    etapa = 99; // Finaliza o capítulo com sucesso
                } else {
                    return "Decisão inválida. Digite 1 para Fugir ou 2 para Esperar.\n" +
                           "1. [FUGIR AGORA]\n" +
                           "2. [ESPERAR E OBSERVAR]";
                }
                break;
        }

        return sb.toString();
    }
}