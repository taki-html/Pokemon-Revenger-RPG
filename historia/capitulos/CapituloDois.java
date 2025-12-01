package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloDois extends Capitulo {

    @Override
    public String processar(String input, Jogador jogador) {

        // --- INÍCIO ---
        if (etapa == 0) {
            jogador.setHp(1); // Força HP 1
            etapa++;
            return "--- CAPÍTULO 2: A GAIOLA ---\n\n" +
                   "(Tudo está preto.)\n" +
                   "...frio... ...metal... ...dor...\n\n" +
                   "(Pressione Enter)";
        }

        if (etapa == 1) {
            etapa++;
            return "A dor é a primeira coisa que você sente. (HP: " + jogador.getHp() + "/" + jogador.getHpMax() + " [PERIGO])\n" +
                   "Você força seus olhos a abrirem. Você está em uma gaiola de metal, em um porão úmido.";
        }

        if (etapa == 2) {
            etapa++;
            return "(Passos na escada. A porta range. É o humano.)\n\n" +
                   "Humano: \"Ah, o 'prêmio' acordou. Ótimo.\"";
        }

        if (etapa == 3) {
            etapa++;
            return "(Ele não chuta a gaiola. Ele a abre.)\n" +
                   "Humano: \"Quil. Hora de comer.\"\n" +
                   "(Ele joga uma tigela com ração dura e cinzenta no chão de concreto.)\n" +
                   "Humano: \"Coma. Você precisa parecer forte para o cliente. Não me faça te forçar.\"";
        }

        if (etapa == 4) {
            etapa++;
            return "(Você está com 1 HP. A dor é imensa. Você mal consegue se mover, quanto mais comer.)\n\n" +
                   "Humano: \"Hmph. Patético. Espírito selvagem inútil.\"\n" +
                   "Humano: \"Quil, fique de olho. Se não comer em cinco minutos, use um Brasa de aviso. Precisamos quebrar essa teimosia.\"";
        }

        if (etapa == 5) {
            etapa++;
            return "(O humano sobe as escadas. Silêncio. É só você e o Quilava.)\n" +
                   "(O Quilava te encara. As chamas em suas costas queimam baixas, incertas.)\n" +
                   "(Ele olha para a ração, depois para você. Ele solta um pequeno bufo de fumaça.)";
        }

        if (etapa == 6) {
            etapa++;
            return "(Ele cutuca a tigela com o focinho, empurrando-a para mais perto. Você não se move.)";
        }

        if (etapa == 7) {
            etapa++;
            return "(O Quilava olha para a escada, nervoso. Então, ele se vira, e com um movimento rápido da pata, empurra algo para dentro da sua gaiola.)\n" +
                   "(Não é a ração. É uma... Oran Berry, amassada e pela metade.)";
        }

        if (etapa == 8) {
            etapa++;
            // Recupera vida
            int cura = 10;
            if (jogador.getHp() + cura > jogador.getHpMax()) cura = jogador.getHpMax() - jogador.getHp();
            jogador.curar(10);
            
            return "(É a *dele*. Ele guardou para você.)\n" +
                   "(Você come a Oran Berry. A dor diminui. HP: " + jogador.getHp() + "/" + jogador.getHpMax() + ")";
        }

        if (etapa == 9) {
            etapa++;
            return "(O Quilava vê você comer. Ele se aproxima da sua gaiola.)\n" +
                   "(Com um 'clic' baixo, ele usa uma garra para empurrar a trava da sua porta, soltando-a.)\n" +
                   "(Ele então se vira e se deita no canto, fingindo dormir.)";
        }

        if (etapa == 10) {
            etapa++;
            return "(A porta está solta. A escada está logo ali. O Quilava te deu uma chance.)\n\n" +
                   "O QUE VOCÊ FAZ?\n" +
                   "1. [FUGIR AGORA] (O Quilava te ajudou. Você deve aproveitar a chance!)\n" +
                   "2. [ESPERAR E OBSERVAR] (Por que ele te ajudou? Isso é um teste?)";
        }

        // --- A ESCOLHA ---

        if (etapa == 11) {
            if (input.equals("1")) {
                // --- CAMINHO A: BAD ENDING ---
                etapa = 99; // Pula para fim ruim
                return "Você empurra a porta da gaiola. O metal range baixo.\n" +
                       "Você corre para as escadas, uma pata de cada vez.\n" +
                       "Você ouve a voz do humano no andar de cima, distraído.\n" +
                       "Você chega ao topo... um beco sujo. É o cheiro da liberdade.\n\n" +
                       "(Pressione Enter)";
            } else if (input.equals("2")) {
                // --- CAMINHO B: CONTINUA ---
                etapa = 20; // Pula para continuação
                return "Não. Você não pode. Se você fugir, ele vai pagar o preço.\n" +
                       "Você puxa a trava solta de volta, para parecer que está fechada.\n" +
                       "Você se encolhe no canto da gaiola, fingindo estar fraco.\n\n" +
                       "(Pressione Enter)";
            } else {
                return "Escolha inválida. Digite 1 ou 2.";
            }
        }

        // --- ROTA BAD ENDING (99+) ---

        if (etapa == 99) {
            etapa++;
            return "(No momento em que você pisa na rua, você ouve um grito do porão.)\n" +
                   "Humano: \"O QUÊ? SEU... TRAIDOR INÚTIL!\"\n" +
                   "(Um som alto de 'SMACK', e o grito de dor inconfundível do Quilava.)";
        }

        if (etapa == 100) {
            etapa++;
            return "(Você escapou.)\n\n" +
                   "[FINAL RUIM 1: O TRAIDOR]\n" +
                   "Você conseguiu. Você está livre. Você eventualmente se curou.\n" +
                   "Mas você nunca esqueceu o som daquele grito.\n" +
                   "Você nunca mais viu o Quilava. Você nunca soube o que aconteceu com Sentret.\n" +
                   "Você vive na floresta, mas agora se esconde de todos os humanos.\n" +
                   "Você está livre. Mas você se pergunta se sua liberdade valeu o preço pago por outro.\n\n" +
                   "[FIM DE JOGO]";
        }

        // --- ROTA CONTINUAÇÃO (20+) ---

        if (etapa == 20) {
            etapa++;
            return "(O Quilava abre um olho. Ele vê o que você fez. Ele te entende.)\n" +
                   "(Uma aliança silenciosa foi formada.)";
        }

        if (etapa == 21) {
            etapa++;
            return "\n(Você acorda com o som da porta do porão se abrindo.)\n" +
                   "(O humano desce. Ele vê a tigela de ração vazia... e a Oran Berry desaparecida.)\n" +
                   "Humano: \"Hã. Comeu, é? Bom. Viu, Quil? Medo funciona.\"";
        }

        if (etapa == 22) {
            etapa++;
            jogador.ganharExp(50);
            return "Humano: \"Levanta. Temos um encontro. E tente não parecer tão patético.\"\n\n" +
                   "(EXP +50 por sobreviver e ganhar um aliado.)\n" +
                   "(Fim do Capítulo 2)";
        }

        return "";
    }

    @Override
    public boolean estaFinalizado() {
        // Se etapa > 100 (Fim Ruim) ou > 22 (Fim Bom)
        return etapa > 100 || (etapa > 22 && etapa < 90);
    }
}