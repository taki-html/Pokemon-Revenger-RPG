package main;

import historia.Capitulo;
import historia.capitulos.*; 
import mecanicas.Batalha; // Certifique-se de importar sua classe Batalha
import personagem.Jogador;
import personagem.Personagem;
import personagem.Inimigo; // Importe Inimigo se precisar para testes
import personagem.jogaveis.*; 
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    // 1. Definição dos Estados
    private enum Estado {
        INTRO,
        CRIANDO_NOME,
        CRIANDO_POKEMON,
        JOGANDO,
        EM_BATALHA, // O caso que estava faltando
        FIM
    }

    private Estado estadoAtual;
    private Jogador jogador;
    private List<Capitulo> listaCapitulos;
    private int indiceCapituloAtual;
    private String bufferTexto;
    private String nomeTemp;

    // 2. Variável que estava dando erro de "não utilizada"
    private Batalha batalhaAtual; 

    public Jogo() {
        this.estadoAtual = Estado.INTRO;
        this.indiceCapituloAtual = 0;
        this.listaCapitulos = new ArrayList<>();
        this.bufferTexto = "";

        // Adicione seus capítulos aqui
        this.listaCapitulos.add(new CapituloUm()); 
        // this.listaCapitulos.add(new CapituloDois()); ...
    }

    // --- MÉTODOS AUXILIARES ---

    private void adicionarTexto(String t) {
        if (bufferTexto.length() > 0) bufferTexto += "\n";
        bufferTexto += t;
    }

    // Método para iniciar batalhas vindo dos Capítulos
    public void iniciarBatalha(Personagem inimigo) {
        this.batalhaAtual = new Batalha(this.jogador, inimigo);
        this.estadoAtual = Estado.EM_BATALHA;
        adicionarTexto("\n!!! UMA BATALHA COMEÇOU !!!");
        adicionarTexto(this.batalhaAtual.getTextoOpcoes());
    }

    // --- PROCESSAMENTO PRINCIPAL ---

    public String processarEntrada(String input) {
        bufferTexto = ""; 

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
                this.nomeTemp = input;
                adicionarTexto("Prazer, " + nomeTemp + "!");
                adicionarTexto("Escolha seu parceiro:");
                adicionarTexto("1. Charmander | 2. Turtwig | 3. Piplup");
                adicionarTexto("4. Tepig      | 5. Rowlet  | 6. Froakie");
                estadoAtual = Estado.CRIANDO_POKEMON;
                break;

            case CRIANDO_POKEMON:
                try {
                    int escolha = Integer.parseInt(input);
                    switch (escolha) {
                        case 1: this.jogador = new Charmander(nomeTemp); break;
                        case 2: this.jogador = new Turtwig(nomeTemp); break;
                        case 3: this.jogador = new Piplup(nomeTemp); break;
                        case 4: this.jogador = new Tepig(nomeTemp); break;
                        case 5: this.jogador = new Rowlet(nomeTemp); break;
                        case 6: this.jogador = new Froakie(nomeTemp); break;
                        default: return "Escolha inválida (1-6).";
                    }
                    
                    adicionarTexto("Você escolheu: " + this.jogador.getNome());
                    
                    // Inicia o Capítulo 1
                    adicionarTexto("\n--- CAPÍTULO 1 ---");
                    adicionarTexto(listaCapitulos.get(0).processar("", jogador));
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
                String resposta = capAtual.processar(input, jogador);

                // --- GATILHO DE BATALHA (Exemplo) ---
                // Se o texto do capítulo contiver "[BATALHA]", iniciamos o combate
                // Isso resolve o uso do campo 'batalhaAtual'
                if (resposta.contains("[BATALHA]")) {
                    // Exemplo: Criar um inimigo genérico para teste
                    // Na versão final, o Capítulo deve dizer qual é o inimigo
                    Inimigo inimigoTeste = new Inimigo("Rattata Selvagem", 10, 2, 1, 2, 1);
                    iniciarBatalha(inimigoTeste); 
                } else {
                    adicionarTexto(resposta);

                    if (capAtual.estaFinalizado()) {
                        indiceCapituloAtual++;
                        if (indiceCapituloAtual < listaCapitulos.size()) {
                            adicionarTexto("\n--- PRÓXIMO CAPÍTULO ---");
                            adicionarTexto(listaCapitulos.get(indiceCapituloAtual).processar("", jogador));
                        } else {
                            estadoAtual = Estado.FIM;
                            adicionarTexto("\n[FIM DE JOGO]");
                        }
                    }
                }
                break;

            // 3. O CASE QUE FALTAVA (Resolve o erro do Enum)
            case EM_BATALHA:
                if (batalhaAtual == null) {
                    estadoAtual = Estado.JOGANDO;
                    break;
                }

                // Envia o input do usuário para a batalha
                String resultadoBatalha = batalhaAtual.processarRodada(input);
                adicionarTexto(resultadoBatalha);

                // Verifica se acabou
                if (batalhaAtual.isTerminou()) {
                    if (batalhaAtual.isVitoriaJogador()) {
                        adicionarTexto("\n(Você venceu a batalha! Retornando à história...)");
                        estadoAtual = Estado.JOGANDO;
                        
                        // --- CORREÇÃO AQUI ---
                        // Não criamos 'Capitulo capAtual', acessamos direto da lista
                        // Chama o processar("", jogador) para pegar o texto pós-batalha (Etapa 2)
                        String textoPosBatalha = listaCapitulos.get(indiceCapituloAtual).processar("", jogador);
                        adicionarTexto(textoPosBatalha);

                    } else {
                        adicionarTexto("\n(Você sucumbiu aos ferimentos...)");
                        estadoAtual = Estado.FIM;
                    }
                }
                break;

            case FIM:
                return "O jogo acabou. Reinicie a página.";
        }

        return bufferTexto;
    }
}