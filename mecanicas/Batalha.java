package mecanicas;

import personagem.Personagem;

public class Batalha {

    private Personagem p1;
    private Personagem p2;
    private IEstrategiaBatalha estrategia;
    private boolean batalhaTerminou;
    private boolean vitoriaJogador; // Para saber se ganhou ou perdeu

    public Batalha(Personagem p1, Personagem p2) {
        this(p1, p2, new EstrategiaBatalhaNormal());
    }

    public Batalha(Personagem p1, Personagem p2, IEstrategiaBatalha estrategia) {
        this.p1 = p1;
        this.p2 = p2;
        this.estrategia = estrategia;
        this.batalhaTerminou = false;
        this.vitoriaJogador = false;
    }

    // Retorna as opções para exibir no frontend (ex: 1. Atacar, 2. Fugir)
    public String getTextoOpcoes() {
        String[] ops = estrategia.getOpcoesDoJogador();
        StringBuilder sb = new StringBuilder();
        sb.append("\nO que você fará?\n");
        for (int i = 0; i < ops.length; i++) {
            sb.append((i + 1)).append(". ").append(ops[i]).append("\n");
        }
        return sb.toString();
    }

    // Processa UM input do frontend (Input -> Lógica Player -> Lógica Inimigo -> Retorno Texto)
    public String processarRodada(String input) {
        StringBuilder logBatalha = new StringBuilder();
        int escolha;

        try {
            escolha = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "Digite o número da opção (ex: 1).";
        }

        // 1. Turno do Jogador
        logBatalha.append(estrategia.executarTurnoJogador(escolha, p1, p2, this));

        if (batalhaTerminou) {
            return finalizarBatalha(logBatalha);
        }

        if (!p2.estaVivo()) { // Inimigo morreu
            this.batalhaTerminou = true;
            return finalizarBatalha(logBatalha);
        }

        // 2. Turno do Inimigo (Contra-ataque imediato)
        logBatalha.append("\n");
        logBatalha.append(estrategia.executarTurnoInimigo(p1, p2, this));

        if (!p1.estaVivo()) { // Jogador morreu
            this.batalhaTerminou = true;
            return finalizarBatalha(logBatalha);
        }

        // Se ninguém morreu, mostra opções de novo
        logBatalha.append(getTextoOpcoes());
        return logBatalha.toString();
    }

    private String finalizarBatalha(StringBuilder log) {
        log.append("\n--- FIM DA BATALHA ---\n");
        
        if (p1.estaVivo() && !p2.estaVivo()) {
            // --- VITÓRIA DO JOGADOR ---
            this.vitoriaJogador = true;
            log.append(p1.getNome()).append(" venceu!\n");
            
            if (!estrategia.isBatalhaImpossivel()) {
                int xpGanho = 50; // Valor padrão de segurança

                // Verifica se o oponente é um Inimigo para pegar o XP correto
                if (p2 instanceof personagem.Inimigo) {
                    xpGanho = ((personagem.Inimigo) p2).getExpRecompensa();
                }

                p1.ganharExp(xpGanho); // Aplica a matemática (soma exp, sobe nível)
                log.append(" >> Você ganhou ").append(xpGanho).append(" EXP.\n");
            }

        } else if (!p1.estaVivo()) {
            // --- DERROTA DO JOGADOR ---
            this.vitoriaJogador = false;
            
            if (estrategia.isBatalhaImpossivel()) {
                // É uma derrota roteirizada (ex: Capítulo 1), o jogo não acaba.
                log.append("(Você cai derrotado, mas a história continua...)\n");
                this.vitoriaJogador = true; // Define como true para Jogo.java continuar
            } else {
                // Game Over real
                log.append("GAME OVER.\n");
            }

        } else {
            // --- FUGA OU EMPATE ---
            this.vitoriaJogador = true; 
        }

        return log.toString();
    }

    // Getters para o Jogo saber se acabou
    public boolean isTerminou() { return batalhaTerminou; }
    public boolean isVitoriaJogador() { return vitoriaJogador; }
    
    public void setBatalhaTerminou(boolean b) { this.batalhaTerminou = b; }
}