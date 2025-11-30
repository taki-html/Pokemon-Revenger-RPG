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
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- LÓGICA INICIAL ---
                // Força o HP do jogador para 1 (continua da captura)
                jogador.setHp(1);

                // --- NARRATIVA COMPLETA (SEM RESUMO) ---
                sb.append("\n--- CAPÍTULO 2: A GAIOLA ---\n\n");
                
                sb.append("(A tela está preta.)\n");
                sb.append("...frio... ...metal... ...dor...\n\n");
                
                sb.append("A dor é a primeira coisa que você sente. (HP: " + jogador.getHp() + "/" + jogador.getHpMax() + " [PERIGO])\n");
                sb.append("Você força seus olhos a abrirem. Você está em uma gaiola de metal, em um porão úmido.\n\n");

                sb.append("(Passos na escada. A porta range. É o humano.)\n");
                sb.append("Humano: \"Ah, o 'prêmio' acordou. Ótimo.\"\n\n");

                sb.append("(Ele não chuta a gaiola. Ele a abre.)\n");
                sb.append("Humano: \"Quil. Hora de comer.\"\n");
                sb.append("(Ele joga uma tigela com ração dura e cinzenta no chão de concreto.)\n");
                sb.append("Humano: \"Coma. Você precisa parecer forte para o cliente. Não me faça te forçar.\"\n\n");

                sb.append("(Você está com 1 HP. A dor é imensa. Você mal consegue se mover, quanto mais comer.)\n");
                sb.append("Humano: \"Hmph. Patético. Espírito selvagem inútil.\"\n");
                sb.append("Humano: \"Quil, fique de olho. Se não comer em cinco minutos, use um Brasa de aviso. Precisamos quebrar essa teimosia.\"\n\n");

                sb.append("(O humano sobe as escadas. Silêncio. É só você e o Quilava.)\n");
                sb.append("(O Quilava te encara. As chamas em suas costas queimam baixas, incertas.)\n");
                sb.append("(Ele olha para a ração, depois para você. Ele solta um pequeno bufo de fumaça.)\n\n");
                
                sb.append("(Ele cutuca a tigela com o focinho, empurrando-a para mais perto. Você não se move.)\n\n");

                sb.append("(O Quilava olha para a escada, nervoso. Então, ele se vira, e com um movimento rápido da pata, empurra algo para dentro da sua gaiola.)\n");
                sb.append("(Não é a ração. É uma... Oran Berry, amassada e pela metade.)\n\n");
                
                sb.append("(É a *dele*. Ele guardou para você.)\n");
                
                // Lógica de recuperação antes de exibir o texto
                jogador.setHp(10); 
                sb.append("(Você come a Oran Berry. A dor diminui. HP: 10/" + jogador.getHpMax() + ")\n\n");

                sb.append("(O Quilava vê você comer. Ele se aproxima da sua gaiola.)\n");
                sb.append("(Com um 'clic' baixo, ele usa uma garra para empurrar a trava da sua porta, soltando-a.)\n");
                sb.append("(Ele então se vira e se deita no canto, fingindo dormir.)\n\n");
                
                sb.append("(A porta está solta. A escada está logo ali. O Quilava te deu uma chance.)\n\n");

                // --- OPÇÕES ---
                sb.append("O QUE VOCÊ FAZ?\n");
                sb.append("1. [FUGIR AGORA] (O Quilava te ajudou. Você deve aproveitar a chance!)\n");
                sb.append("2. [ESPERAR E OBSERVAR] (Por que ele te ajudou? Isso é um teste?)");

                etapa = 1; // Aguarda resposta do jogador
                break;

            case 1:
                if (input.equals("1")) {
                    // --- CAMINHO A: FUGIR (FINAL RUIM 1) ---
                    sb.append("\nVocê empurra a porta da gaiola. O metal range baixo.\n");
                    sb.append("Você corre para as escadas, uma pata de cada vez.\n");
                    sb.append("Você ouve a voz do humano no andar de cima, distraído.\n");
                    sb.append("Você chega ao topo... um beco sujo. É o cheiro da liberdade.\n\n");
                    
                    sb.append("(No momento em que você pisa na rua, você ouve um grito do porão.)\n");
                    sb.append("Humano: \"O QUÊ? SEU... TRAIDOR INÚTIL!\"\n");
                    sb.append("(Um som alto de 'SMACK', e o grito de dor inconfundível do Quilava.)\n\n");
                    
                    sb.append("(Você escapou.)\n\n");

                    sb.append("[FINAL RUIM 1: O TRAIDOR]\n");
                    sb.append("Você conseguiu. Você está livre. Você eventualmente se curou.\n");
                    sb.append("Mas você nunca esqueceu o som daquele grito.\n");
                    sb.append("Você nunca mais viu o Quilava. Você nunca soube o que aconteceu com Sentret.\n");
                    sb.append("Você vive na floresta, mas agora se esconde de todos os humanos.\n");
                    sb.append("Você está livre. Mas você se pergunta se sua liberdade valeu o preço pago por outro.");
                    
                    etapa = 99; // Fim de Jogo (Game Over)

                } else if (input.equals("2")) {
                    // --- CAMINHO B: ESPERAR ---
                    sb.append("\nNão. Você não pode. Se você fugir, ele vai pagar o preço.\n");
                    sb.append("Você puxa a trava solta de volta, para parecer que está fechada.\n");
                    sb.append("Você se encolhe no canto da gaiola, fingindo estar fraco.\n\n");

                    sb.append("(O Quilava abre um olho. Ele vê o que você fez. Ele te entende.)\n");
                    sb.append("(Uma aliança silenciosa foi formada.)\n\n");
                    
                    sb.append("(Você acorda com o som da porta do porão se abrindo.)\n");
                    sb.append("(O humano desce. Ele vê a tigela de ração vazia... e a Oran Berry desaparecida.)\n");
                    sb.append("Humano: \"Hã. Comeu, é? Bom. Viu, Quil? Medo funciona.\"\n\n");

                    sb.append("Humano: \"Levanta. Temos um encontro. E tente não parecer tão patético.\"\n\n");

                    sb.append("(EXP +50 por sobreviver e ganhar um aliado.)\n");
                    jogador.ganharExp(50);
                    
                    sb.append("\n(Fim do Capítulo 2.)");
                    etapa = 99; // Finaliza o capítulo com sucesso
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;
        }

        return sb.toString();
    }
}