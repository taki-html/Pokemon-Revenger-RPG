package main;
import historia.Capitulo;
import historia.ResultadoCapitulo;
import historia.capitulos.CapituloUm;
import historia.capitulos.CapituloDois;
import historia.capitulos.CapituloTres;
import historia.capitulos.CapituloQuatro;
import historia.capitulos.CapituloCinco;
import historia.capitulos.CapituloSeis;
import historia.capitulos.CapituloSete;
import historia.capitulos.CapituloOito;
import historia.capitulos.CapituloNove;
import historia.capitulos.CapituloDez;
import java.util.InputMismatchException;
import java.util.Scanner;
import personagem.Jogador;
import personagem.jogaveis.Charmander;
import personagem.jogaveis.Froakie;
import personagem.jogaveis.Piplup;
import personagem.jogaveis.Rowlet;
import personagem.jogaveis.Tepig;
import personagem.jogaveis.Turtwig;
import util.Util;

public class Jogo {

    private Jogador jogador;
    private Scanner scanner;

    private Capitulo[] capitulos;

    public Jogo() {
        this.scanner = new Scanner(System.in);
    }

    //Ponto de entrada principal do jogo
    public static void main(String[] args) {
        Jogo rpg = new Jogo();
        try {
            rpg.iniciar();
        } catch (Exception e) {
            System.err.println("\nUm erro inesperado ocorreu e o jogo precisou ser encerrado.");
            e.printStackTrace();
        } finally {
            rpg.scanner.close();
        }
    }

    //Inicia a lógica principal do jogo
    public void iniciar() {
        mostrarIntroducao();
        this.jogador = criarPersonagem();
        this.jogador.mostrarStatus();

        // Inicializa os capítulos
        this.capitulos = new Capitulo[] {
            new CapituloUm(),
            new CapituloDois(),
            new CapituloTres(),
            new CapituloQuatro(),
            new CapituloCinco(),
            new CapituloSeis(),
            new CapituloSete(),
            new CapituloOito(),
            new CapituloNove(),
            new CapituloDez()
        };

        // Loop principal do jogo
        for (Capitulo capitulo : capitulos) {
            ResultadoCapitulo resultado = capitulo.executar(jogador, scanner);
            
            if (!resultado.jogoContinua()) {
                System.out.println("\n[FIM DE JOGO]");
                System.out.println(resultado.getMensagemFinal());
                return; // Encerra o jogo
            }
            Util.pausar(1);
        }
        
        // Se o loop terminar sem um final ruim
        System.out.println("\nParabéns! Você chegou ao final da demo.");
    }

    private void mostrarIntroducao() {
        System.out.println("############################################################");
        System.out.println("#                                                          #");
        System.out.println("#                   POKÉMON: REVENGE                       #");
        System.out.println("#                                                          #");
        System.out.println("############################################################");
        Util.pausar(2);
    }

    /**
     * Criação do personagem do jogador.
     */
    private Jogador criarPersonagem() {
        System.out.println("Seja bem-vindo ao mundo dos Pokémons! Eu sou... quer dizer, não importa quem eu sou!");
        System.out.println("Você parece ser novo por aqui...");
        System.out.println("Não sei dizer ao certo, você é um...");
        
        String[] nomesPokemon = {
            "Charmander", 
            "Turtwig", 
            "Piplup", 
            "Tepig", 
            "Rowlet", 
            "Froakie"
        };

        String[] descricoes = {
            "É mesmo, um Charmander! Eu lembro de ver outros como você por aí, com uma chama na ponta da cauda.",
            "É mesmo, um Turtwig! Eu me lembro de ver outros como você por aí, com um broto em suas costas para absorver o sol.",
            "É mesmo, um Piplup! Eu me lembro de ver outros como você por aí, suas nadadeiras são maneiras.",
            "É mesmo, um Tepig! Eu me lembro de ver outros como você por aí, sempre saltitantes.",
            "É mesmo, um Rowlet! Eu me lembro de ver outros como você por aí, voando para onde quer que fossem",
            "É mesmo, um Froakie! Eu me lembro de ver outros como você por aí, suas patas parecem ser muito habilidosas."
        };

        for (int i = 0; i < nomesPokemon.length; i++) {
            System.out.printf("%d. [%s]\n", (i + 1), nomesPokemon[i]);
        }

        int escolha = -1;
        while (escolha < 1 || escolha > nomesPokemon.length) {
            System.out.print("\nEscolha quem você é (1-6): ");
            try {
                escolha = scanner.nextInt();
                if (escolha < 1 || escolha > nomesPokemon.length) {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
        scanner.nextLine();

        String descricaoEscolhida = descricoes[escolha - 1];
        System.out.println("\n" + descricaoEscolhida);

        System.out.println("E qual é o seu nome?");
        String nome = scanner.nextLine();

        System.out.println("Muito prazer em te conhecer, " + nome + "! Você está prestes a viver uma grande aventura");
        System.out.println("Gostaria muito de poder te contar mais, porém cabe a você descobrir. Te desenho uma boa sorte!");

        Util.aguardarEnter(scanner);

        switch (escolha) {
            case 1: return new Charmander(nome);
            case 2: return new Turtwig(nome);
            case 3: return new Piplup(nome);
            case 4: return new Tepig(nome);
            case 5: return new Rowlet(nome);
            case 6: return new Froakie(nome);
            default: return new Charmander(nome);
        }
    }
}