package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaCap1;
import mecanicas.IEstrategiaBatalha;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;

public class CapituloUm implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {

        // --- CENA 1: AMBIENTAÇÃO ---

        System.out.println("\n--- CAPÍTULO 1: O PARAÍSO PERDIDO ---");

        Util.aguardarEnter(scanner);

        System.out.println("(O sol está quente e gentil, filtrando-se pelas folhas das árvores)");

        Util.aguardarEnter(scanner);

        System.out.println("(O ar possuí um cheiro agradável de orvalho, terra úmida e ao pólen doce das flores próximas.)");

        Util.aguardarEnter(scanner);

        System.out.println("(Você está cercado de vida: o som distante de Pidgeys e o zumbido de um Beedrill são como música para seus ouvidos.)");

        Util.aguardarEnter(scanner);

        System.out.println("\n(Ao seu lado, um Sentret de pelo marrom-claro, com anéis pretos brincalhões em sua cauda fofa e ereta, boceja. É o seu melhor amigo.)");

        Util.aguardarEnter(scanner);

        System.out.println("(Seus olhos redondos e curiosos te encaram. Ele é pequeno, mas sua energia é contagiante.)");

        Util.aguardarEnter(scanner);

        System.out.println("(Ele, então, se espreguiça longamente e te cutuca com o focinho úmido.)");

        Util.aguardarEnter(scanner);

        System.out.println("Acorde " + jogador.getNome() + "! O dia está perfeito");

        Util.aguardarEnter(scanner);

        System.out.println("(Você olha a natureza ao seu redor e se sente pleno. Este é o seu lar. Um refúgio.)");

        Util.aguardarEnter(scanner);

        // --- CENA 2: EXPLORAÇÃO ---

        System.out.println("\nVocês dois correm para o centro da clareira. O que fazer primeiro?");
        Util.pausar(1);
        
        List<String> acoesDisponiveis = new ArrayList<>();
        acoesDisponiveis.add("Vasculhar os arbustos de Oran Berries");
        acoesDisponiveis.add("Brincar de pega-pega com Sentret");
        acoesDisponiveis.add("Investigar o riacho cintilante");

        while (!acoesDisponiveis.isEmpty()) {
            
            String[] opcoesArray = acoesDisponiveis.toArray(new String[0]);
            int escolhaIndex = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?", opcoesArray);
            String acaoEscolhida = acoesDisponiveis.get(escolhaIndex - 1);

            switch (acaoEscolhida) {
                case "Vasculhar os arbustos de Oran Berries":
                    System.out.println("Você e Sentret farejam os arbustos... O cheiro doce é inconfundível!");
                    System.out.println("O suco da fruta explode na sua boca. Perfeito. (HP MÁXIMO!)");
                    jogador.ganharExp(10);
                    Util.pausar(1);
                    break;
                    
                case "Brincar de pega-pega com Sentret":
                    System.out.println("Você dá uma patada de leve em Sentret e dispara! Ele te persegue, rindo, e vocês rolam pela grama macia.");
                    System.out.println("É uma explosão de energia e pura alegria. (EXP +10)");
                    jogador.ganharExp(10);
                    Util.pausar(1);
                    break;
                    
                case "Investigar o riacho cintilante":
                    System.out.println("A água fria e cristalina corre sobre suas patas. É refrescante.");
                    System.out.println("Um Poliwag sonolento flutua, observando vocês com curiosidade. Ele boceja bolhas. (EXP +10)");
                    jogador.ganharExp(10);
                    Util.pausar(1);
                    break;
            }
            acoesDisponiveis.remove(acaoEscolhida);
        }

        Util.aguardarEnter(scanner);

        // Conclusão da Exploração
        System.out.println("\n(O sol está alto. Satisfeitos e levemente cansados, vocês se deitam na grama.)");
        System.out.println("(Sentret solta um longo suspiro de contentamento. Nada poderia estragar este dia.)");
        Util.pausar(2);

        Util.aguardarEnter(scanner);

        
        // --- CENA 3: O CONFLITO ---
        
        System.out.println("\nSentret: \"Ufa! Que dia! Estou... uau... exausto!\"");
        System.out.println("(Sentret se joga na grama e se espreguiça ao sol)");
        System.out.println("\nSentret: \"Hora de comer Apricorns! Elas ficam logo ali, depois da colina. Vamos!\"");

        Util.aguardarEnter(scanner);

        System.out.println("(Você segue alegremente ao lado dele. A grama roça em suas pernas. O ar está agradável.)");
        System.out.println("(Você está a poucos metros da colina quando...)");

        Util.aguardarEnter(scanner);

        System.out.println("(...você para)");

        Util.aguardarEnter(scanner);

        System.out.println("\nSentret: \"Ei, por que você parou?\"");

        Util.aguardarEnter(scanner);

        System.out.println("Você levanta a cabeça para tentar entender o que ocorreu. O ar mudou, e se tornou estranhamente denso.");

        Util.aguardarEnter(scanner);

        System.out.println("O canto dos Pidgeys parou. O zumbido do Beedrill cessou.");
        System.out.println("O silêncio é pesado.");

        Util.aguardarEnter(scanner);

        System.out.println("E então, o cheiro. ");
        System.out.println("Não é terra. Não é flor. Não é água.");
        System.out.println("É algo químico. Áspero. Frio. Metal. Despertando-lhe sensações que você jamais esperava sentir");

        Util.aguardarEnter(scanner);
        
        System.out.println("(Uma sombra longa e antinatural cai sobre vocês, bloqueando o sol quente.)");

        Util.aguardarEnter(scanner);

        System.out.println("(Era humano. Diferente de todos aqueles que você já avistou ao longe.)");

        Util.aguardarEnter(scanner);

        System.out.println("(Botas pesadas esmagam a grama macia onde vocês acabaram de rolar. Você não se lembrava de ver um deles tão bruto quanto este)");
        System.out.println("(Ele é alto, usa um boné que esconde os olhos e tem um sorriso cruel. Ao lado dele, rosnando baixo, está um Pokémon que fede a arrogância e cinzas: um Quilava. Ele parece Nível 5, no mínimo. Ele parece forte, treinado... e infeliz.)");

        Util.aguardarEnter(scanner);

        System.out.println("Ele não olha para o riacho com seus olhos frios e sem sentimentos");

        Util.aguardarEnter(scanner);
        
        System.out.println("Humano: \"...Hoje não parece ser o meu dia de sorte. Mas que Sentret patético e inútil...\"");
        System.out.println("(Ele reclama ao avistar o seu amigo. Sentret estava atordoado e com as patas tremendo de medo)");
        System.out.println("Humano \"Acabe com ele!\"");
        System.out.println("(Ele ordena ao Quilava)");

        Util.aguardarEnter(scanner);

        System.out.println("\n(O Quilava dá um passo à frente, com brasas saindo de sua boca, focado no Sentret aterrorizado.)");
        System.out.println("(Não há tempo para pensar. Você precisa agir!)");

        Util.aguardarEnter(scanner);

        System.out.println("O QUE VOCÊ FAZ?");

        Inimigo quilava = new Inimigo("Quilava", 25, 8, 6, 7, 0); 
        IEstrategiaBatalha estrategiaCap1 = new EstrategiaBatalhaCap1();
        Batalha batalhaCap1 = new Batalha(jogador, quilava, scanner, estrategiaCap1);
        batalhaCap1.iniciarBatalha();

        Util.aguardarEnter(scanner);

        // --- CENA 5: A CAPTURA ---

        System.out.println("Você está no chão, ofegante (HP " + jogador.getHp() + "/" + jogador.getHpMax() + "). O gosto de poeira na boca.");
        System.out.println("(Onde está Sentret? Você não o vê. Ele deve ter fugido para a grama alta quando você o protegeu...)");
        Util.aguardarEnter(scanner);

        System.out.println("Humano: \"Hmph. Patético. Selvagens nunca aprendem a hora de desistir.\"");
        System.out.println("Humano: \"Mas você... você tem espírito. Eu gosto disso. Você será muito útil.\"");
        Util.aguardarEnter(scanner);

        System.out.println("Ele joga o objeto esférico, metálico. Aquele que cheirava a veneno.");
        System.out.println("Uma luz vermelha explode dele. Não é quente como o sol. É fria. Artificial. Ela te consome.");
        Util.aguardarEnter(scanner);

        System.out.println("Tudo fica escuro. Silencioso.");
        Util.pausar(2);
        System.out.println("Até o som final.");
        Util.pausar(1);
        System.out.println("Um som que sela seu destino. O som do seu mundo se fechando.");
        System.out.println("...Clic.");
        Util.pausar(3);

        jogador.ganharExp(70); 

        jogador.mostrarStatus();
        Util.aguardarEnter(scanner);

        System.out.println("\n(Fim do Capítulo 1)");
        Util.aguardarEnter(scanner);

        return ResultadoCapitulo.continuar();
    }
}