package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import personagem.Jogador;
import util.Util;

public class CapituloQuatro implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 4: O COVIL DO TREINADOR ---");
        System.out.printf("(Status: Nível %d | HP %d/%d)\n", jogador.getNivel(), jogador.getHp(), jogador.getHpMax());

        Util.aguardarEnter(scanner);

        System.out.println("\n\"Casa\" é uma palavra generosa para este lugar.");
        System.out.println("É um apartamento térreo sujo, nos fundos de um prédio.");
        System.out.println("O humano te joga no quintal dos fundos – um pedaço de terra batida com uma poça de lama.");
        System.out.println("Ele amarra sua gaiola a uma cerca enferrujada.");

        Util.aguardarEnter(scanner);

        System.out.println("(A noite cai. O frio aumenta.)");
        System.out.println("Você observa o humano através da porta de vidro suja. Ele anda de um lado para o outro, soca a parede.");
        System.out.println("Ele parece... quebrado. Recebendo ordens pelo telefone.");
        
        Util.aguardarEnter(scanner);
        
        System.out.println("(O humano apaga no sofá com uma garrafa na mão.)");
        System.out.println("(Lá fora, o frio corta sua pele. Você perde 1 HP pelo frio.)");
        jogador.receberDano(1);
        System.out.printf("(HP Atual: %d/%d)\n", jogador.getHp(), jogador.getHpMax());

        Util.aguardarEnter(scanner);

        int escolha = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                "[NÃO FAZER NADA] (A noite é fria, mas segura. Melhor economizar energia.)",
                "[INVESTIGAR A CASA] (Aquele humano é apenas um peão. Você precisa de respostas.)");

        if (escolha == 1) {
            // --- CAMINHO A: NÃO FAZER NADA ---
            System.out.println("\nVocê se encolhe no fundo da gaiola, tremendo.");
            System.out.println("A noite parece interminável. Você fecha os olhos e apenas... suporta.");
            jogador.incrementarInacao(); // +1 Inação

            // VERIFICAÇÃO DO FINAL RUIM 2
            if (jogador.getContadorInacao() >= 2) {
                Util.pausar(2);
                System.out.println("\n...E assim os dias passam.");
                System.out.println("O quintal frio. Batalhas sem sentido. A comida ruim.");
                System.out.println("Cada vez que você teve uma chance de descobrir a verdade, o medo o manteve parado.");
                System.out.println("Você se acostumou com a dor. Você esqueceu o cheiro das Oran Berries.");
                System.out.println("Você se tornou o parceiro silencioso do fracasso do seu treinador.");
                
                String msgFinal = "[FINAL RUIM 2: A ESPERANÇA PERDIDA]\n" +
                                  "Você aceitou seu destino de sofrimento, esperando um milagre que nunca veio.\n" +
                                  "Milagres não vêm para aqueles que não lutam por eles.";
                return ResultadoCapitulo.finalRuim(msgFinal);
            }

            // Se sobreviveu à inação:
            System.out.println("(Você sobrevive à noite. A manhã chega.)");
            System.out.println("(O humano acorda, te ignora e sai. Você perdeu a chance de investigar, mas está vivo.)");
            
            // CORREÇÃO: Adicionando o XP de Capítulo aqui também
            System.out.println("(Fim do Capítulo 4. EXP +50 [Bônus de Capítulo].)");
            jogador.ganharExp(50);

            return ResultadoCapitulo.continuar();

        } else {
            // --- CAMINHO B: INVESTIGAR A CASA ---
            System.out.println("\nVocê não pode simplesmente esperar. O frio é ruim, mas a ignorância é pior.");
            System.out.println("Você força a trava da gaiola novamente. Ela cede.");
            System.out.println("Silenciosamente, você vai até a porta dos fundos.");
            
            System.out.println("\n(Usando Habilidade: " + jogador.getHabilidadeExploracao() + ")");
            System.out.println("Você usa suas habilidades naturais para entrar na casa sem fazer barulho.");
            
            Util.aguardarEnter(scanner);

            System.out.println("O cheiro lá dentro é horrível (álcool e lixo), mas está quente.");
            System.out.println("Você sobe na mesa de centro. Há papéis espalhados.");
            System.out.println("Um mapa de Johto. Vários locais marcados com aquele Símbolo Estranho.");
            
            System.out.println("-> A Torre Queimada (Ecruteak)");
            System.out.println("-> O Farol (Olivine)");
            System.out.println("-> Hotel Abandonado (Rota 16)");

            jogador.adicionarPista("MAPA DE JOHTO");
            jogador.ganharExp(40); // Bônus de Investigação (+40 conforme PDF)

            System.out.println("\nVocê está prestes a memorizar mais detalhes quando ouve... vozes.");
            System.out.println("Lá fora. Passos se aproximando da porta da frente.");
            System.out.println("Você corre e se esconde debaixo do sofá velho, bem a tempo.");

            // Bônus de Capítulo
            System.out.println("(Fim do Capítulo 4. EXP +50 [Bônus de Capítulo].)");
            jogador.ganharExp(50);
            
            Util.aguardarEnter(scanner);
            return ResultadoCapitulo.continuar();
        }
    }
}