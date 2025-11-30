package historia.capitulos;

import historia.Capitulo;
import personagem.Inimigo;
import personagem.Jogador;

public class CapituloTres extends Capitulo {

    private Inimigo quilavaInimigo; // Variável para guardar o estado do inimigo entre os turnos

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- INTRODUÇÃO ---
                sb.append("\n--- CAPÍTULO 3: O ENCONTRO ---\n");
                sb.append("(Status: HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");
                
                sb.append("Ketch te leva para um prédio abandonado.\n");
                sb.append("Ele entra numa sala e diz para você e o Quilava ficarem quietos.\n");
                sb.append("A trava da gaiola está frouxa.\n\n");

                sb.append("1. [ESPIAR] Sair e ver o que está acontecendo.\n");
                sb.append("2. [OUVIR] Ficar na gaiola.");
                
                etapa = 1;
                break;

            case 1:
                // --- INVESTIGAÇÃO ---
                if (input.equals("1")) {
                    sb.append("\n> Você ESPIA.\n");
                    sb.append("Você vê uma mulher de uniforme preto recebendo Pokébolas.\n");
                    sb.append("Você descobre o SÍMBOLO DA EQUIPE. (+30 EXP)\n");
                    jogador.adicionarPista("SÍMBOLO");
                    jogador.ganharExp(30);
                } else if (input.equals("2")) {
                    sb.append("\n> Você OUVE.\n");
                    sb.append("Você ouve que Ketch é insignificante e que o 'Projeto Trovão' é considerado lenda.\n");
                    sb.append("Você obteve informações sobre o culto.\n");
                    jogador.incrementarInacao();
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }

                sb.append("\nKetch sai da sala furioso com a Mulher.\n");
                sb.append("Ketch: 'Vou provar que minha captura é forte! QUILAVA, ATAQUE!'\n");
                sb.append("Ele chuta a gaiola. O Quilava (Nível 5) te encara, tremendo, obrigado a lutar.\n");
                
                // --- SETUP DA BATALHA ---
                // Criamos o inimigo aqui. Ele tem HP e atributos.
                this.quilavaInimigo = new Inimigo("Quilava Assustado", 25, 4, 2, 5, 5);
                
                sb.append("\n!!! BATALHA INICIADA !!!\n");
                sb.append("Inimigo: " + quilavaInimigo.getNome() + " (HP: " + quilavaInimigo.getHp() + ")\n");
                sb.append("Ketch: 'Não se segure! ACABE COM ELE!'\n\n");
                
                sb.append("1. [ATACAR] (Dano baseado no seu ATK)\n");
                sb.append("2. [PROTEGER] (Aumenta DEF temporariamente)\n");
                sb.append("3. [RECUSAR/HESITAR] (Tentar parar a luta)");

                etapa = 3; // Manda para o loop de batalha
                break;

            case 3: 
                // --- LOOP DE BATALHA (TURNO A TURNO) ---
                
                // 1. TURNO DO JOGADOR
                boolean jogadorAtacou = false;
                boolean jogadorHesitou = false;
                int danoCausado = 0;
                
                if (input.equals("1")) {
                    // Atacar
                    danoCausado = jogador.atacar(quilavaInimigo);
                    sb.append("> Você avança e ataca o Quilava!\n");
                    sb.append("Causou " + danoCausado + " de dano.\n");
                    jogadorAtacou = true;
                } else if (input.equals("2")) {
                    // Proteger
                    sb.append("> Você entra em posição defensiva.\n");
                    // (Poderia aumentar DEF temporária aqui, mas vamos simplificar narrativamente)
                } else if (input.equals("3")) {
                    // Hesitar (Mecânica especial deste capítulo)
                    sb.append("> Você olha nos olhos do Quilava e se recusa a bater.\n");
                    jogadorHesitou = true;
                } else {
                    return "Opção inválida na batalha. Digite 1, 2 ou 3.";
                }

                // 2. VERIFICAÇÃO DE FIM DE COMBATE (VITÓRIA POR NOCAUTE)
                if (!quilavaInimigo.estaVivo()) {
                    sb.append("\nO Quilava cai no chão, nocauteado pelo seu golpe final.\n");
                    sb.append("Ketch: 'ISSO! VIU COMO ELE É FORTE?!'\n");
                    
                    sb.append("\n(Você venceu, mas traiu a confiança do Quilava. +50 EXP)\n");
                    jogador.adicionarTraicao(1);
                    jogador.ganharExp(50);
                    
                    sb.append("\n(Fim do Capítulo 3)");
                    etapa = 99;
                    return sb.toString();
                }

                // 3. VERIFICAÇÃO DE FIM DE COMBATE (VITÓRIA POR PIEDADE)
                if (jogadorHesitou) {
                    sb.append("\nKetch fica furioso com sua recusa.\n");
                    sb.append("Ketch: 'INÚTIL! LUTE!'\n");
                    sb.append("Ele tenta te chutar, mas o Quilava (ainda com HP " + quilavaInimigo.getHp() + ") entra na frente e recebe o golpe!\n");
                    sb.append("A Mulher ri da incompetência de Ketch e vai embora.\n");
                    
                    sb.append("\n(Você protegeu seu aliado. +70 EXP)\n");
                    jogador.adicionarLealdade(1);
                    jogador.ganharExp(70);
                    
                    sb.append("\n(Fim do Capítulo 3)");
                    etapa = 99;
                    return sb.toString();
                }

                // 4. TURNO DO INIMIGO (Se ninguém ganhou ainda)
                sb.append("\n[Turno do Inimigo]\n");
                if (jogadorAtacou) {
                    // Se você bateu, ele revida por medo
                    int danoInimigo = quilavaInimigo.atacar(jogador);
                    sb.append("O Quilava, assustado e ferido, lança uma brasa fraca.\n");
                    sb.append("Você sofreu " + danoInimigo + " de dano.\n");
                } else {
                    // Se você protegeu, ele hesita
                    sb.append("O Quilava vê que você não quer lutar. Ele erra o ataque de propósito.\n");
                }

                // 5. ATUALIZAÇÃO DE STATUS
                sb.append("\n--------------------------\n");
                sb.append("SEU HP: " + jogador.getHp() + "/" + jogador.getHpMax() + "\n");
                sb.append("QUILAVA HP: " + quilavaInimigo.getHp() + "/" + quilavaInimigo.getHpMax() + "\n");
                sb.append("--------------------------\n");

                if (jogador.getHp() <= 0) {
                    sb.append("\nVocê caiu em combate...\n");
                    sb.append("[GAME OVER - Você morreu na batalha de teste]\n");
                    etapa = 99;
                } else {
                    // Continua o loop
                    sb.append("O QUE VOCÊ FAZ?\n");
                    sb.append("1. [ATACAR]\n2. [PROTEGER]\n3. [TENTAR PARAR]");
                    // Mantém etapa = 3 para continuar no loop
                }
                break;
        }

        return sb.toString();
    }
}