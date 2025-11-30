package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;
import java.util.ArrayList;
import java.util.List;

public class CapituloUm extends Capitulo {

    private List<String> acoesRealizadas = new ArrayList<>();

    @Override
    public String processar(String input, Jogador jogador) {
        
        // --- CENA 1: AMBIENTAÇÃO ---
        
        if (etapa == 0) {
            etapa++;
            return "--- CAPÍTULO 1: O PARAÍSO PERDIDO ---\n\n" +
                   "(O sol está quente e gentil, filtrando-se pelas folhas das árvores)\n" +
                   "(O ar possui um cheiro agradável de orvalho, terra úmida e ao pólen doce das flores próximas.)\n\n" +
                   "(Pressione Enter para continuar)";
        }

        if (etapa == 1) {
            etapa++;
            return "(Você está cercado de vida: o som distante de Pidgeys e o zumbido de um Beedrill são como música para seus ouvidos.)\n\n" +
                   "(Ao seu lado, um Sentret de pelo marrom-claro, com anéis pretos brincalhões em sua cauda fofa e ereta, boceja. É o seu melhor amigo.)";
        }

        if (etapa == 2) {
            etapa++;
            return "(Seus olhos redondos e curiosos te encaram. Ele é pequeno, mas sua energia é contagiante.)\n" +
                   "(Ele, então, se espreguiça longamente e te cutuca com o focinho úmido.)\n\n" +
                   "Sentret: \"Acorde " + jogador.getNome() + "! O dia está perfeito!\"";
        }

        if (etapa == 3) {
            etapa++;
            return "(Você olha a natureza ao seu redor e se sente pleno. Este é o seu lar. Um refúgio.)\n\n" +
                   "Vocês dois correm para o centro da clareira. O que fazer primeiro?\n" +
                   mostrarOpcoesExploracao();
        }

        // --- CENA 2: EXPLORAÇÃO (Loop interativo CORRIGIDO) ---

        if (etapa == 4) {
            // Verifica se já fez
            if (acoesRealizadas.contains(input)) {
                return "Você já fez isso. Escolha outra coisa.\n" + mostrarOpcoesExploracao();
            }

            String textoAcao = "";

            // Processa a escolha
            switch (input) {
                case "1":
                    acoesRealizadas.add("1");
                    jogador.ganharExp(10);
                    textoAcao = "Você e Sentret farejam os arbustos... O cheiro doce é inconfundível!\n" +
                                "O suco da fruta explode na sua boca. Perfeito. (HP MÁXIMO! +10 EXP)";
                    break;
                case "2":
                    acoesRealizadas.add("2");
                    jogador.ganharExp(10);
                    textoAcao = "Você dá uma patada de leve em Sentret e dispara! Ele te persegue, rindo, e vocês rolam pela grama macia.\n" +
                                "É uma explosão de energia e pura alegria. (+10 EXP)";
                    break;
                case "3":
                    acoesRealizadas.add("3");
                    jogador.ganharExp(10);
                    textoAcao = "A água fria e cristalina corre sobre suas patas. É refrescante.\n" +
                                "Um Poliwag sonolento flutua, observando vocês com curiosidade. Ele boceja bolhas. (+10 EXP)";
                    break;
                default:
                    return "Escolha inválida. Digite 1, 2 ou 3.";
            }

            // Verifica SE acabou TUDO agora
            if (acoesRealizadas.size() >= 3) {
                etapa++; // Avança para a próxima cena
                return textoAcao + "\n\n(O sol está alto. Satisfeitos e levemente cansados, vocês se deitam na grama.)\n" +
                       "(Sentret solta um longo suspiro de contentamento. Nada poderia estragar este dia.)\n\n" +
                       "(Pressione Enter)";
            }

            // Se não acabou, mostra o menu de novo
            return textoAcao + "\n\nO que mais vocês fazem?\n" + mostrarOpcoesExploracao();
        }

        // --- CENA 3: O CONFLITO ---

        if (etapa == 5) {
            etapa++;
            return "Sentret: \"Ufa! Que dia! Estou... uau... exausto!\"\n" +
                   "(Sentret se joga na grama e se espreguiça ao sol)\n\n" +
                   "Sentret: \"Hora de comer Apricorns! Elas ficam logo ali, depois da colina. Vamos!\"";
        }

        if (etapa == 6) {
            etapa++;
            return "(Você segue alegremente ao lado dele. A grama roça em suas pernas. O ar está agradável.)\n" +
                   "(Você está a poucos metros da colina quando...)\n\n" +
                   "(...você para)";
        }

        if (etapa == 7) {
            etapa++;
            return "Sentret: \"Ei, por que você parou?\"\n\n" +
                   "Você levanta a cabeça para tentar entender o que ocorreu. O ar mudou, e se tornou estranhamente denso.\n" +
                   "O canto dos Pidgeys parou. O zumbido do Beedrill cessou.\n" +
                   "O silêncio é pesado.";
        }

        if (etapa == 8) {
            etapa++;
            return "E então, o cheiro.\n" +
                   "Não é terra. Não é flor. Não é água.\n" +
                   "É algo químico. Áspero. Frio. Metal. Despertando-lhe sensações que você jamais esperava sentir.\n\n" +
                   "(Uma sombra longa e antinatural cai sobre vocês, bloqueando o sol quente.)";
        }

        if (etapa == 9) {
            etapa++;
            return "(Era humano. Diferente de todos aqueles que você já avistou ao longe.)\n\n" +
                   "(Botas pesadas esmagam a grama macia onde vocês acabaram de rolar. Você não se lembrava de ver um deles tão bruto quanto este)";
        }

        if (etapa == 10) {
            etapa++;
            return "(Ele é alto, usa um boné que esconde os olhos e tem um sorriso cruel. Ao lado dele, rosnando baixo, está um Pokémon que fede a arrogância e cinzas: um Quilava.)\n" +
                   "(Ele parece Nível 5, no mínimo. Ele parece forte, treinado... e infeliz.)\n" +
                   "Ele não olha para o riacho com seus olhos frios e sem sentimentos.";
        }

        if (etapa == 11) {
            etapa++;
            return "Humano: \"...Hoje não parece ser o meu dia de sorte. Mas que Sentret patético e inútil...\"\n" +
                   "(Ele reclama ao avistar o seu amigo. Sentret estava atordoado e com as patas tremendo de medo)\n\n" +
                   "Humano: \"Acabe com ele!\"\n" +
                   "(Ele ordena ao Quilava)";
        }

        if (etapa == 12) {
            etapa++;
            return "(O Quilava dá um passo à frente, com brasas saindo de sua boca, focado no Sentret aterrorizado.)\n" +
                   "(Não há tempo para pensar. Você precisa agir!)\n\n";
        }

        // --- CENA 4: A BATALHA (Gatilho) ---

        if (etapa == 13) {
            if (!input.matches("[1-3]")) return "Escolha inválida. Digite 1, 2 ou 3.";

            etapa++; // Avança
            
            // Retorna a flag para o Jogo.java
            return "[BATALHA]"; 
        }

        // --- CENA 5: A CAPTURA (Pós-Batalha) ---

        if (etapa == 14) {
            etapa++;
            return "Você está no chão, ofegante (HP " + jogador.getHp() + "/" + jogador.getHpMax() + "). O gosto de poeira na boca.\n" +
                   "(Onde está Sentret? Você não o vê. Ele deve ter fugido para a grama alta quando você o protegeu...)";
        }

        if (etapa == 15) {
            etapa++;
            return "Humano: \"Hmph. Patético. Selvagens nunca aprendem a hora de desistir.\"\n" +
                   "Humano: \"Mas você... você tem espírito. Eu gosto disso. Você será muito útil.\"";
        }

        if (etapa == 16) {
            etapa++;
            return "Ele joga o objeto esférico, metálico. Aquele que cheirava a veneno.\n" +
                   "Uma luz vermelha explode dele. Não é quente como o sol. É fria. Artificial. Ela te consome.";
        }

        if (etapa == 17) {
            etapa++;
            return "Tudo fica escuro. Silencioso.\n" +
                   "Até o som final.\n" +
                   "Um som que sela seu destino. O som do seu mundo se fechando.\n\n" +
                   "...Clic.";
        }

        if (etapa == 18) {
            etapa++;
            jogador.ganharExp(70);
            return "(Você ganhou 70 de EXP)\n" +
                   "(Fim do Capítulo 1)";
        }

        return "";
    }

    @Override
    public boolean estaFinalizado() {
        return etapa > 18;
    }

    // --- Método Auxiliar ---

    private String mostrarOpcoesExploracao() {
        StringBuilder sb = new StringBuilder();
        if (!acoesRealizadas.contains("1")) sb.append("1. Vasculhar os arbustos de Oran Berries\n");
        if (!acoesRealizadas.contains("2")) sb.append("2. Brincar de pega-pega com Sentret\n");
        if (!acoesRealizadas.contains("3")) sb.append("3. Investigar o riacho cintilante\n");
        return sb.toString();
    }
}