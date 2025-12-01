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
import mecanicas.IEstrategiaBatalha;
import mecanicas.EstrategiaBatalhaCap1;
import mecanicas.EstrategiaBatalhaCap3;

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

    
    private Batalha batalhaAtual; 

    public Jogo() {
        this.estadoAtual = Estado.INTRO;
        this.indiceCapituloAtual = 0;
        this.listaCapitulos = new ArrayList<>();
        this.bufferTexto = "";

        
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

    // --- MÉTODOS AUXILIARES ---

    private void adicionarTexto(String t) {
        if (bufferTexto.length() > 0) bufferTexto += "\n";
        bufferTexto += t;
    }

    // Sobrecarga para batalhas normais
    public void iniciarBatalha(Personagem inimigo) {
        this.batalhaAtual = new Batalha(this.jogador, inimigo);
        configurarInicioBatalha();
    }

    // Sobrecarga para batalhas de História (com estratégia específica)
    public void iniciarBatalha(Personagem inimigo, IEstrategiaBatalha estrategia) {
        this.batalhaAtual = new Batalha(this.jogador, inimigo, estrategia); // Usa o construtor
        configurarInicioBatalha();
    }

    // Método auxiliar para não repetir código
    private void configurarInicioBatalha() {
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
                adicionarTexto("Escolha seu personagem:");
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

                if (resposta.contains("[BATALHA]")) {
                    
                    // --- SELEÇÃO DE BATALHA POR CAPÍTULO ---
                    
                    if (indiceCapituloAtual == 0) { // Capítulo 1
                        Inimigo quilava = new Inimigo("Quilava", 25, 8, 6, 7, 5);
                        IEstrategiaBatalha estrategiaCap1 = new EstrategiaBatalhaCap1();
                        iniciarBatalha(quilava, estrategiaCap1);
                        
                    } else if (indiceCapituloAtual == 2) { // Capítulo 3 (Índice 2)
                        
                        // 1. Criamos o inimigo específico deste capítulo
                        Inimigo quilavaAssustado = new Inimigo("Quilava Assustado", 20, 5, 5, 7, 0);
                        
                        // 2. Usamos a Estratégia específica que tem as opções "Hesitar/Proteger"
                        IEstrategiaBatalha estrategiaCap3 = new EstrategiaBatalhaCap3();
                        
                        // 3. Iniciamos
                        iniciarBatalha(quilavaAssustado, estrategiaCap3);
                        
                    } else {
                        // Batalha Genérica (Rattata)
                        Inimigo rato = new Inimigo("Rattata", 10, 2, 1, 2, 1);
                        iniciarBatalha(rato);
                    }

                } else {
                    // Lógica de texto normal
                    adicionarTexto(resposta);

                    if (capAtual.estaFinalizado()) {
                        // ... (Lógica de fim de capítulo igual à anterior)
                        if (resposta.contains("[FIM DE JOGO]")) {
                            estadoAtual = Estado.FIM;
                        } else {
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
                }
                break;

            // 3. O CASE QUE FALTAVA (Resolve o erro do Enum)
            case EM_BATALHA:
                if (batalhaAtual == null) {
                    estadoAtual = Estado.JOGANDO;
                    break;
                }

                String resultadoBatalha = batalhaAtual.processarRodada(input);
                adicionarTexto(resultadoBatalha);

                if (batalhaAtual.isTerminou()) {
                    if (batalhaAtual.isVitoriaJogador()) {
                        adicionarTexto("\n(A batalha terminou.)");
                        estadoAtual = Estado.JOGANDO;

                        // DECISÃO IMPORTANTE DO CAPÍTULO 3
                        String flagResultado = "VITORIA_POR_RENDICAO"; // Padrão
                        
                        if (batalhaAtual.inimigoMorreuRealmente()) {
                             flagResultado = "VITORIA_POR_MORTE";
                        }

                        // Passa o resultado para o Capítulo decidir a rota (Traição ou Lealdade)
                        String textoPosBatalha = listaCapitulos.get(indiceCapituloAtual).processar(flagResultado, jogador);
                        adicionarTexto(textoPosBatalha);
                        
                    } else {
                        adicionarTexto("\n(Você sucumbiu...)");
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