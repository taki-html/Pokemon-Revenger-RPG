package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloUm extends Capitulo {

    // Controle de exploração
    private boolean comeu = false;
    private boolean brincou = false;
    private boolean explorou = false;
    
    // Controle da Batalha roteirizada
    private int turnosBatalha = 0;

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        // Proteção: Se o front enviar null, tratamos como string vazia para não quebrar
        if (input == null) {
            input = "";
        }

        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0: // --- CENA 1: O DESPERTAR (Atmosfera) ---
                sb.append("--- CAPÍTULO 1: O PARAÍSO PERDIDO ---\n\n");
                sb.append("(O sol está quente e gentil, filtrando-se pelas folhas das árvores.)\n");
                sb.append("(O ar possui um cheiro agradável de orvalho, terra úmida e ao pólen doce das flores próximas.)\n");
                sb.append("(Você está cercado de vida: o som distante de Pidgeys e o zumbido de um Beedrill são como música.)\n\n");
                sb.append("Ao seu lado, um Sentret de pelo marrom-claro, com anéis pretos brincalhões em sua cauda, boceja.\n");
                sb.append("Ele se espreguiça e te cutuca com o focinho úmido.\n\n");
                sb.append("\"Acorde " + jogador.getNome() + "! O dia está perfeito!\"\n");
                // Mudança na instrução para deixar claro que é necessário digitar
                sb.append("\n[Digite qualquer coisa e envie para continuar]");
                
                etapa = 1; // Avança para o menu de exploração
                break;

            case 1: // --- CENA 2: TRANSIÇÃO PARA EXPLORAÇÃO ---
                sb.append("(Você olha a natureza ao seu redor e se sente pleno. Este é o seu lar. Um refúgio.)\n");
                sb.append("Vocês dois correm para o centro da clareira. O que fazer primeiro?\n");
                sb.append(getMenuExploracao());
                
                etapa = 2; // Vai para o loop de decisão
                break;

            case 2: // --- CENA 2: LOOP DE EXPLORAÇÃO ---
                String acaoTexto = "";
                
                // Validação simples caso o jogador envie vazio num menu de escolha
                if (input.trim().isEmpty()) {
                     return "Por favor, digite o número da opção (1, 2 ou 3).\n" + getMenuExploracao();
                }

                if (input.equals("1") && !comeu) {
                    acaoTexto = "> [CENA]: Você e Sentret farejam os arbustos... O cheiro doce é inconfundível!\n" +
                                "O suco da fruta explode na sua boca. Perfeito. (HP MÁXIMO!)\n";
                    jogador.ganharExp(10);
                    comeu = true;
                } else if (input.equals("2") && !brincou) {
                    acaoTexto = "> [CENA]: Você dá uma patada de leve em Sentret e dispara!\n" +
                                "Ele te persegue, rindo, e vocês rolam pela grama macia. Pura alegria. (+10 EXP)\n";
                    jogador.ganharExp(10);
                    brincou = true;
                } else if (input.equals("3") && !explorou) {
                    acaoTexto = "> [CENA]: A água fria e cristalina corre sobre suas patas.\n" +
                                "Um Poliwag sonolento flutua, observando vocês. Ele boceja bolhas. (+10 EXP)\n";
                    jogador.ganharExp(10);
                    explorou = true;
                } else {
                    return "Essa opção não está disponível ou você já fez isso.\n" + getMenuExploracao();
                }

                sb.append(acaoTexto);
                sb.append("\n------------------------------------------------\n");

                // Verifica se completou tudo
                if (comeu && brincou && explorou) {
                    sb.append("\n(O sol está alto. Satisfeitos e levemente cansados, vocês se deitam na grama.)\n");
                    sb.append("Sentret: \"Ufa! Que dia! Estou... uau... exausto! Hora de comer Apricorns na colina!\"\n");
                    sb.append("\n(Vocês partem para a colina...)\n");
                    sb.append("[Digite qualquer coisa para caminhar]");
                    etapa = 3; // Vai para o conflito
                } else {
                    sb.append("(Ainda há tempo para mais alguma coisa.)\n");
                    sb.append(getMenuExploracao());
                    // Mantém na etapa 2
                }
                break;

            case 3: // --- CENA 3: O CHEIRO (Suspense) ---
                sb.append("Vocês caminham alegres, mas de repente... você para.\n\n");
                sb.append("Sentret: \"Ei, por que você parou?\"\n");
                sb.append("O canto dos Pidgeys parou. O silêncio é pesado.\n");
                sb.append("E então, o cheiro. Não é terra. Não é flor.\n");
                sb.append("É algo químico. Áspero. Frio. Metal.\n\n");
                sb.append("(Uma sombra longa cai sobre vocês. Botas pesadas esmagam a grama macia.)\n");
                sb.append("[Digite qualquer coisa para olhar para cima]");
                etapa = 4;
                break;

            case 4: // --- CENA 4: O INIMIGO ---
                sb.append("Era um HUMANO. Alto, sorriso cruel, boné escondendo os olhos.\n");
                sb.append("Ao lado dele, rosnando baixo, um QUILAVA (Nível 5).\n");
                sb.append("O Pokémon fede a arrogância e cinzas.\n\n");
                sb.append("Humano: \"...Hoje não parece ser o meu dia de sorte. Mas que Sentret patético...\"\n");
                sb.append("Humano: \"Acabe com ele!\"\n\n");
                sb.append("(O Quilava avança com brasas na boca, focado no seu amigo aterrorizado.)\n");
                sb.append("(Não há tempo para pensar. Você precisa agir!)\n");
                sb.append(getMenuBatalha());
                etapa = 5; // Início da Batalha Roteirizada
                break;

            case 5: // --- CENA 5: A BATALHA (Mecânica recuperada) ---
                
                sb.append("Turno de Batalha " + (turnosBatalha + 1) + "\n");
                
                if (input.equals("1")) { // Investida
                    sb.append("Você tenta uma Investida desesperada contra o Quilava!\n");
                    sb.append("O Quilava nem se move. Ele recebe o golpe rindo.\n");
                } else if (input.equals("2")) { // Rosnar/Defender
                    sb.append("Você tenta intimidar o inimigo para proteger o Sentret.\n");
                    sb.append("O Humano apenas ri da sua tentativa.\n");
                } else {
                    return "Você precisa agir rápido! Digite 1 ou 2.\n" + getMenuBatalha();
                }

                // Contra-ataque scriptado
                sb.append("\nO Quilava contra-ataca com 'Brasas'!\n");
                sb.append("O fogo queima sua pele. A dor é insuportável.\n");
                sb.append("(HP Crítico!)\n\n");
                
                turnosBatalha++;

                if (turnosBatalha < 2) {
                    sb.append("Você mal consegue ficar de pé, mas precisa tentar de novo!\n");
                    sb.append(getMenuBatalha());
                    // Mantém na etapa 5 para mais um turno
                } else {
                    // Fim da batalha forçada
                    sb.append("Você cai no chão, sem forças...\n");
                    sb.append("[Digite qualquer coisa para continuar]");
                    etapa = 6;
                }
                break;

            case 6: // --- CENA 6: A CAPTURA ---
                sb.append("Você está ofegante. O gosto de poeira na boca.\n");
                sb.append("Sentret fugiu para a grama alta graças à sua distração.\n\n");
                sb.append("Humano: \"Hmph. Patético. Mas você... você tem espírito. Eu gosto disso.\"\n");
                sb.append("(Ele joga um objeto esférico e metálico em sua direção.)\n\n");
                sb.append("Uma luz vermelha explode. Não é quente como o sol. É fria. Artificial.\n");
                sb.append("Ela te consome.\n");
                sb.append("[Digite qualquer coisa...]");
                etapa = 7;
                break;

            case 7: // --- CENA 7: O FIM ---
                sb.append("Tudo fica escuro. Silencioso.\n");
                sb.append("O som do seu mundo se fechando.\n");
                sb.append("...Clic.\n\n");
                
                sb.append("Você foi capturado.\n");
                sb.append("(EXP +70 Ganho por bravura)\n");
                jogador.ganharExp(70);
                
                sb.append("\n--- FIM DO CAPÍTULO 1 ---");
                etapa = 99; // Finaliza o capítulo
                break;
        }

        return sb.toString();
    }

    // --- MÉTODOS AUXILIARES PARA OS MENUS ---

    private String getMenuExploracao() {
        StringBuilder menu = new StringBuilder();
        menu.append("\nO QUE VOCÊ FAZ?\n");
        if (!comeu) menu.append("1. Vasculhar os arbustos de Oran Berries\n");
        if (!brincou) menu.append("2. Brincar de pega-pega com Sentret\n");
        if (!explorou) menu.append("3. Investigar o riacho cintilante\n");
        return menu.toString();
    }
    
    private String getMenuBatalha() {
        return "\nOPÇÕES DE COMBATE:\n" +
               "1. Usar Investida (Atacar)\n" +
               "2. Proteger o Sentret (Defender)\n";
    }
}