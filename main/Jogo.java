package main;

import historia.Capitulo;
import historia.capitulos.*; // Seus capítulos
import personagem.Jogador;
import personagem.jogaveis.*; // Seus pokémons
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    // Estados do Jogo
    private enum Estado {
        INTRO,
        CRIANDO_NOME,
        CRIANDO_POKEMON,
        JOGANDO,
        FIM
    }

    private Estado estadoAtual;
    private Jogador jogador;
    private List<Capitulo> listaCapitulos;
    private int indiceCapituloAtual;
    private String bufferTexto; // Guarda o texto para enviar ao front

    public Jogo() {
        this.estadoAtual = Estado.INTRO;
        this.indiceCapituloAtual = 0;
        this.listaCapitulos = new ArrayList<>();
        // Inicializa lista (Aqui você colocará todos os 10 adaptados futuramente)
        this.listaCapitulos.add(new CapituloUm()); 
        this.listaCapitulos.add(new CapituloDois()); 
        this.listaCapitulos.add(new CapituloTres()); 
        this.listaCapitulos.add(new CapituloQuatro()); 
        this.listaCapitulos.add(new CapituloCinco()); 
        this.listaCapitulos.add(new CapituloSeis()); 
        this.listaCapitulos.add(new CapituloSete()); 
        this.listaCapitulos.add(new CapituloOito()); 
        this.listaCapitulos.add(new CapituloNove()); 
        this.listaCapitulos.add(new CapituloDez()); 

    }

    // Método principal chamado pelo Servidor
    public String processarEntrada(String input) {
        bufferTexto = ""; // Limpa o buffer

        switch (estadoAtual) {
            case INTRO:
                adicionarTexto("Bem-vindo ao Pokémon: Revenge!");
                adicionarTexto("Para começar, qual é o seu nome?");
                estadoAtual = Estado.CRIANDO_NOME;
                break;

            case CRIANDO_NOME:
                if (input == null || input.trim().isEmpty()) {
                    return "Por favor, digite um nome válido.";
                }
                // Cria um jogador temporário só para guardar o nome por enquanto
                // Vamos instanciar o real na próxima etapa
                String nomeTemp = input;
                adicionarTexto("Prazer, " + nomeTemp + "!");
                adicionarTexto("Agora, quem é você?");
                adicionarTexto("1. Charmander\n2. Turtwig\n3. Piplup\n4. Tepig\n5. Rowlet\n6. Froakie");
                adicionarTexto("Digite o número (1-6):");
                
                // Hack: Guardar o nome em uma variável temporária ou criar um Jogador genérico
                this.jogador = new Charmander(nomeTemp); // Temporário
                estadoAtual = Estado.CRIANDO_POKEMON;
                break;

            case CRIANDO_POKEMON:
                try {
                    int escolha = Integer.parseInt(input);
                    String nome = this.jogador.getNome(); // Recupera o nome

                    switch (escolha) {
                        case 1: this.jogador = new Charmander(nome); break;
                        case 2: this.jogador = new Turtwig(nome); break;
                        case 3: this.jogador = new Piplup(nome); break;
                        case 4: this.jogador = new Tepig(nome); break;
                        case 5: this.jogador = new Rowlet(nome); break;
                        case 6: this.jogador = new Froakie(nome); break;
                        default: 
                            return "Escolha inválida. Digite entre 1 e 6.";
                    }
                    adicionarTexto("Você escolheu: " + this.jogador.getNome() + "!");
                    this.jogador.mostrarStatus(); // Você pode adaptar esse método para retornar String depois
                    
                    adicionarTexto("\n--- CAPÍTULO 1 ---");
                    // Chama a primeira vez o capítulo 1 (input vazio para iniciar)
                    String textoCap = listaCapitulos.get(0).processar("", jogador);
                    adicionarTexto(textoCap);
                    
                    estadoAtual = Estado.JOGANDO;
                    
                } catch (NumberFormatException e) {
                    return "Digite apenas o número.";
                }
                break;

            case JOGANDO:
                if (indiceCapituloAtual >= listaCapitulos.size()) {
                    return "O Jogo Acabou. Obrigado por jogar!";
                }

                Capitulo capAtual = listaCapitulos.get(indiceCapituloAtual);
                String respostaCapitulo = capAtual.processar(input, jogador);
                adicionarTexto(respostaCapitulo);

                if (capAtual.estaFinalizado()) {
                    indiceCapituloAtual++;
                    if (indiceCapituloAtual < listaCapitulos.size()) {
                        adicionarTexto("\n--- PRÓXIMO CAPÍTULO ---");
                        // Inicia o próximo já
                        adicionarTexto(listaCapitulos.get(indiceCapituloAtual).processar("", jogador));
                    } else {
                        estadoAtual = Estado.FIM;
                        adicionarTexto("\n[FIM DE JOGO]");
                    }
                }
                break;
                
            case FIM:
                return "O jogo acabou. Reinicie a página para jogar novamente.";
        }

        return bufferTexto;
    }

    private void adicionarTexto(String t) {
        if (bufferTexto.length() > 0) bufferTexto += "\n";
        bufferTexto += t;
    }
}