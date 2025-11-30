package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloSete extends Capitulo {

    private Inimigo quilavaBoss; // Persistência do inimigo durante a batalha

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- INTRODUÇÃO E DRAMA ---
                sb.append("\n--- CAPÍTULO 7: O PONTO DE NÃO RETORNO ---\n");
                sb.append("(Status Atual: Nível " + jogador.getNivel() + ". HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                
                sb.append("(Três dias se passaram. É o dia do ritual.)\n");
                sb.append("Seu treinador está nervoso, arrumando a bolsa. Ele coloca um Talismã menor no bolso.\n");
                sb.append("Ele abre a porta da frente para sair, deixando você no quintal frio.\n");
                sb.append("Ele te olha. \"Você... você fica. Você só me traz problemas.\"\n\n");
                
                sb.append("Ele vai te deixar para trás. Preso. Enquanto eles destroem tudo.\n");
                sb.append("Esta é a sua única chance.\n\n");
                
                sb.append("Enquanto ele vira as costas para trancar a porta, você corre!\n");
                sb.append("Você usa toda a força que acumulou. Você se joga contra a porta de vidro.\n");
                sb.append("CRASH! O vidro quebra. Você está na rua.\n\n");

                sb.append("Humano: \"NÃO! VOCÊ NÃO VAI!\"\n");
                sb.append("Ele te persegue até o beco. Ele bloqueia a única saída.\n");
                sb.append("Humano: \"Você não vai estragar isso para mim! Eu não vou falhar por causa de um Pokémon estúpido!\"\n\n");
                
                sb.append("Ele joga uma Pokébola. A única que ele tem.\n");
                sb.append("Humano: \"QUIL! PARE-O! USE TUDO!\"\n\n");

                sb.append("O QUILAVA aparece. Ele parece mais forte (Nível 5), mas seus olhos estão tristes.\n");
                sb.append("Ele sabe que esta é uma luta de vida ou morte.\n");
                sb.append("Mas ele obedece. Ele não tem a sua coragem.\n\n");

                // --- SETUP DO BOSS ---
                this.quilavaBoss = new Inimigo("Quilava (Rival)", 20, 6, 6, 7, 5);
                
                sb.append("--- BATALHA DECISIVA ---\n");
                sb.append("Inimigo: " + quilavaBoss.getNome() + " (HP: " + quilavaBoss.getHp() + ")\n");
                
                sb.append("1. [ATACAR] (Usar sua força total)\n");
                sb.append("2. [PROTEGER] (Defesa tática)");

                etapa = 1; // Inicia o Loop de Batalha
                break;

            case 1:
                // --- LOOP DE BATALHA ---
                
                int danoPlayer = 0;
                boolean playerProtegeu = false;

                // 1. TURNO DO JOGADOR
                if (input.equals("1")) {
                    danoPlayer = jogador.atacar(quilavaBoss);
                    sb.append("> Você ataca com ferocidade! (Dano: " + danoPlayer + ")\n");
                } else if (input.equals("2")) {
                    sb.append("> Você assume uma postura defensiva.\n");
                    playerProtegeu = true;
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }

                // 2. CHECAGEM DE VITÓRIA
                if (!quilavaBoss.estaVivo()) {
                    // CAMINHO A: VITÓRIA
                    sb.append("\nO Quilava cai, exausto. Ele olha para você... e parece sorrir levemente. Ele está livre da luta.\n");
                    sb.append("O humano cai de joelhos, boquiaberto. \"Não... não...\"\n");
                    sb.append("Ele tenta te agarrar com as próprias mãos.\n\n");
                    
                    sb.append("Você rosna. Uma chama brilha em seus olhos. Ele recua, apavorado.\n");
                    sb.append("Você o deixa para trás, um homem quebrado chorando no beco.\n\n");
                    
                    sb.append("Você corre em direção à saída da cidade.\n");
                    sb.append("Destino: ECRUTEAK. TORRE QUEIMADA.\n\n");
                    
                    // Recompensas Massivas
                    sb.append("(Você descansa na floresta e come Oran Berries. HP RECUPERADO.)\n");
                    jogador.setHp(jogador.getHpMax());
                    
                    sb.append("(Level Up garantido! +130 EXP)\n");
                    jogador.ganharExp(130);
                    
                    sb.append("\n(Fim do Capítulo 7.)");
                    etapa = 99; // Sucesso
                    return sb.toString();
                }

                // 3. TURNO DO INIMIGO
                if (playerProtegeu) {
                    sb.append("O Quilava lança chamas, mas você bloqueia o impacto.\n");
                } else {
                    int danoInimigo = quilavaBoss.atacar(jogador);
                    sb.append("O Quilava obedece o mestre e ataca! (Dano: " + danoInimigo + ")\n");
                }

                // 4. CHECAGEM DE DERROTA (GAME OVER)
                if (jogador.getHp() <= 0) {
                    // CAMINHO B: DERROTA
                    sb.append("\nO Quilava te acerta com um último golpe. Você cai, sem forças.\n");
                    sb.append("Humano: \"Veja? Eu não falhei. Eu peguei ele de volta.\"\n\n");
                    
                    sb.append("Ele te coloca de volta na Pokébola. A escuridão retorna.\n");
                    sb.append("Você acorda horas depois. Você está na Torre Queimada.\n");
                    sb.append("Mas não como herói. Como sacrifício.\n\n");
                    
                    sb.append("[FINAL RUIM 4: A CORRENTE ETERNA]\n");
                    sb.append("Você foi derrotado e levado ao ritual como um sacrifício extra.\n");
                    sb.append("Sua fuga falhou. O ciclo de abuso continua.\n");
                    sb.append("Fim de Jogo.");
                    
                    etapa = 99; // Fim Ruim
                    return sb.toString();
                }

                // 5. CONTINUA BATALHA
                sb.append("\nSEU HP: " + jogador.getHp() + " | RIVAL HP: " + quilavaBoss.getHp() + "\n");
                sb.append("1. [ATACAR]\n2. [PROTEGER]");
                // Mantém etapa 1
                break;
        }

        return sb.toString();
    }
}