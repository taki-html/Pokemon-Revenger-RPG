package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloNove extends Capitulo {

    private Inimigo tyranitarBoss;

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- INTRODUÇÃO: O ANTRO DO LÍDER ---
                sb.append("\n--- CAPÍTULO 9: O CORAÇÃO DO CULTO ---\n");
                sb.append("(Status Atual: Nível " + jogador.getNivel() + ". HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                
                sb.append("Você arromba a porta final. É uma caverna subterrânea sob a torre.\n");
                sb.append("O local vibra com uma energia roxa e doentia.\n\n");
                
                sb.append("No centro, diante de um altar, está o LÍDER DO CULTO.\n");
                sb.append("Ele é alto, imponente. O Talismã na mão dele brilha intensamente.\n");
                sb.append("No canto, encolhido como um servo, está Ketch, seu antigo treinador.\n\n");
                
                sb.append("Líder: \"Então... o rato sobreviveu.\"\n");
                sb.append("Ele não está com raiva. Ele está... divertido.\n");
                sb.append("Líder: \"Você vem para vingar seus amigos patéticos? A fraqueza deve ser purificada.\"\n");
                
                sb.append("\n(Pressione ENTER para continuar)");
                etapa = 1;
                break;

            case 1:
                // --- A INVOCAÇÃO ---
                sb.append("\nLíder: \"A 'Grande Arma' nos dará esse poder.\"\n");
                sb.append("Ele levanta uma Ultra Ball.\n");
                sb.append("Líder: \"Guardião... entretenha nosso convidado.\"\n\n");

                sb.append("Desta bola, não sai um Pokémon comum... sai uma besta.\n");
                sb.append("Um TYRANITAR SOMBRIO. Olhos brilhando em roxo, corrompido pela energia do culto.\n");
                sb.append("Ele ruge, e a caverna treme.\n");

                // Configuração do Boss Final
                this.tyranitarBoss = new Inimigo("Tyranitar Sombrio", 40, 12, 10, 10, 10);
                
                sb.append("\n!!! BATALHA FINAL !!!\n");
                sb.append("(Se você perder aqui, o mundo acaba.)\n");
                sb.append("Inimigo: " + tyranitarBoss.getNome() + " (HP: " + tyranitarBoss.getHp() + ")\n");
                sb.append("1. [ATACAR] (Usar tudo o que você tem)\n");
                sb.append("2. [PROTEGER] (Tentar sobreviver ao impacto)");
                
                etapa = 2; // Loop de Batalha
                break;

            case 2:
                // --- LOOP DE BATALHA (TYRANITAR) ---
                int danoPlayer = 0;
                boolean playerProtegeu = false;

                // 1. TURNO JOGADOR
                if (input.equals("1")) {
                    danoPlayer = jogador.atacar(tyranitarBoss);
                    sb.append("> Você ataca com fúria! (Dano: " + danoPlayer + ")\n");
                } else if (input.equals("2")) {
                    sb.append("> Você endurece suas defesas.\n");
                    playerProtegeu = true;
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }

                // 2. CHECAGEM DE VITÓRIA
                if (!tyranitarBoss.estaVivo()) {
                    sb.append("\nO Tyranitar ruge uma última vez e cai.\n");
                    sb.append("A energia roxa desaparece de seus olhos. Ele parece... aliviado. Ele foi libertado.\n");
                    sb.append("(+200 EXP - LEVEL UP MASSIVO)\n");
                    jogador.ganharExp(200);

                    sb.append("\nO Líder do Culto grita de raiva. \"NÃO! INÚTIL!\"\n");
                    sb.append("Ele corre para o Talismã no altar, que está sobrecarregado e instável.\n");
                    sb.append("Líder: \"EU MESMO FAREI ISSO! O PODER SERÁ MEU!\"\n\n");
                    
                    sb.append("O LÍDER ESTÁ VULNERÁVEL. ACABE COM ISSO.\n");
                    sb.append("1. [ATACAR O LÍDER] (Acabar com ele. A vingança final.)\n");
                    sb.append("2. [DESTRUIR O TALISMÃ] (Acabar com a fonte do poder. Salvar a todos.)");
                    
                    etapa = 3; // Decisão Final
                    return sb.toString();
                }

                // 3. TURNO INIMIGO
                if (playerProtegeu) {
                    sb.append("O Tyranitar destrói o chão ao seu redor, mas você aguenta o tranco.\n");
                } else {
                    int danoInimigo = tyranitarBoss.atacar(jogador);
                    sb.append("A besta sombria te ataca! (Dano: " + danoInimigo + ")\n");
                }

                // 4. CHECAGEM DE DERROTA (GAME OVER FINAL)
                if (jogador.getHp() <= 0) {
                    sb.append("\n[FINAL RUIM 6: A PURIFICAÇÃO]\n");
                    sb.append("O Tyranitar te joga contra a parede da caverna. A escuridão toma conta.\n");
                    sb.append("O Líder do Culto ri: \"A fraqueza foi purificada.\"\n");
                    sb.append("O ritual é concluído. Johto cai em sombras.\n");
                    sb.append("Fim de Jogo.");
                    etapa = 99;
                    return sb.toString();
                }

                // 5. CONTINUA
                sb.append("\nSEU HP: " + jogador.getHp() + " | BOSS HP: " + tyranitarBoss.getHp() + "\n");
                sb.append("1. [ATACAR]\n2. [PROTEGER]");
                break;

            case 3:
                // --- ESCOLHA FINAL ---
                if (input.equals("1")) {
                    sb.append("\n> CAMINHO DA VINGANÇA\n");
                    sb.append("Você cede ao ódio. Você dispara seu ataque contra o humano.\n");
                    sb.append("Ele é atingido e cai.\n");
                    sb.append("Mas o Talismã, sem controle, EXPLODE.\n");
                    sb.append("A caverna começa a desmoronar sobre todos vocês.\n");
                    
                } else if (input.equals("2")) {
                    sb.append("\n> CAMINHO DA REDENÇÃO\n");
                    sb.append("Você ignora o humano. Você mira no cristal.\n");
                    sb.append("Você usa seu ataque mais forte contra o Talismã.\n");
                    sb.append("Líder: \"NÃO! O QUE VOCÊ FEZ?!\"\n");
                    sb.append("CRACK! O Talismã explode em mil pedaços.\n");
                    sb.append("A energia roxa se dissipa. O Líder cai de joelhos, impotente.\n");
                } else {
                    return "Decida o destino de todos. 1 ou 2.";
                }

                sb.append("\nKetch observa tudo do canto, em choque. Está acabado.\n");
                sb.append("A vingança está completa. O Culto caiu.\n");
                sb.append("(Fim do Capítulo 9)");
                
                etapa = 99; // Fim do Capítulo
                break;
        }

        return sb.toString();
    }
}