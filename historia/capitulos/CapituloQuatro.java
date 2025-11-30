package historia.capitulos;

import historia.Capitulo;
import personagem.Jogador;

public class CapituloQuatro extends Capitulo {

    @Override
    public boolean estaFinalizado() {
        return etapa == 99;
    }

    @Override
    public String processar(String input, Jogador jogador) {
        StringBuilder sb = new StringBuilder();

        switch (etapa) {
            case 0:
                // --- CENA INICIAL ---
                sb.append("\n--- CAPÍTULO 4: O COVIL DO TREINADOR ---\n");
                sb.append("(Status: Nível " + jogador.getNivel() + " | HP " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");

                sb.append("\"Casa\" é uma palavra generosa para este lugar.\n");
                sb.append("É um apartamento térreo sujo, nos fundos de um prédio.\n");
                sb.append("O humano te joga no quintal dos fundos – um pedaço de terra batida com uma poça de lama.\n");
                sb.append("Ele amarra sua gaiola a uma cerca enferrujada.\n\n");

                sb.append("(A noite cai. O frio aumenta.)\n");
                sb.append("Você observa o humano através da porta de vidro suja. Ele anda de um lado para o outro, soca a parede.\n");
                sb.append("Ele parece... quebrado. Recebendo ordens pelo telefone.\n\n");
                
                sb.append("(O humano apaga no sofá com uma garrafa na mão.)\n");
                
                // Lógica de Dano pelo frio
                jogador.receberDano(1);
                sb.append("(Lá fora, o frio corta sua pele. Você perde 1 HP pelo frio.)\n");
                sb.append("(HP Atual: " + jogador.getHp() + "/" + jogador.getHpMax() + ")\n\n");

                sb.append("O QUE VOCÊ FAZ?\n");
                sb.append("1. [NÃO FAZER NADA] (A noite é fria, mas segura. Economizar energia.)\n");
                sb.append("2. [INVESTIGAR A CASA] (Aquele humano é apenas um peão. Você precisa de respostas.)");

                etapa = 1; // Aguarda escolha
                break;

            case 1:
                if (input.equals("1")) {
                    // --- CAMINHO A: NÃO FAZER NADA ---
                    sb.append("\n> Você escolheu NÃO FAZER NADA.\n");
                    sb.append("Você se encolhe no fundo da gaiola, tremendo.\n");
                    sb.append("A noite parece interminável. Você fecha os olhos e apenas... suporta.\n");
                    
                    jogador.incrementarInacao(); // +1 Inação

                    // VERIFICAÇÃO DO FINAL RUIM 2 (Game Over por passividade)
                    if (jogador.getContadorInacao() >= 2) {
                        sb.append("\n...E assim os dias passam.\n");
                        sb.append("O quintal frio. Batalhas sem sentido. A comida ruim.\n");
                        sb.append("Cada vez que você teve uma chance de descobrir a verdade, o medo o manteve parado.\n");
                        sb.append("Você se acostumou com a dor. Você esqueceu o cheiro das Oran Berries.\n");
                        sb.append("Você se tornou o parceiro silencioso do fracasso do seu treinador.\n\n");
                        
                        sb.append("[FINAL RUIM 2: A ESPERANÇA PERDIDA]\n");
                        sb.append("Você aceitou seu destino de sofrimento, esperando um milagre que nunca veio.\n");
                        sb.append("Milagres não vêm para aqueles que não lutam por eles.\n\n");
                        sb.append("Fim de Jogo.");
                        
                        // Não damos EXP nem continuamos
                        etapa = 99; 
                        
                    } else {
                        // Se sobreviveu à inação (porque agiu no Cap 3)
                        sb.append("\n(Você sobrevive à noite. A manhã chega.)\n");
                        sb.append("(O humano acorda, te ignora e sai. Você perdeu a chance de investigar, mas está vivo.)\n");
                        
                        sb.append("(Fim do Capítulo 4. EXP +50 [Bônus de Capítulo].)\n");
                        jogador.ganharExp(50);
                        
                        etapa = 99; // Finaliza o capítulo e vai para o 5
                    }

                } else if (input.equals("2")) {
                    // --- CAMINHO B: INVESTIGAR A CASA ---
                    sb.append("\n> Você escolheu INVESTIGAR.\n");
                    sb.append("Você não pode simplesmente esperar. O frio é ruim, mas a ignorância é pior.\n");
                    sb.append("Você força a trava da gaiola novamente. Ela cede.\n");
                    sb.append("Silenciosamente, você vai até a porta dos fundos.\n\n");
                    
                    sb.append("(Usando Habilidade: " + jogador.getHabilidadeExploracao() + ")\n");
                    sb.append("Você usa suas habilidades naturais para entrar na casa sem fazer barulho.\n\n");

                    sb.append("O cheiro lá dentro é horrível (álcool e lixo), mas está quente.\n");
                    sb.append("Você sobe na mesa de centro. Há papéis espalhados.\n");
                    sb.append("Um mapa de Johto. Vários locais marcados com aquele Símbolo Estranho.\n\n");
                    
                    sb.append("-> A Torre Queimada (Ecruteak)\n");
                    sb.append("-> O Farol (Olivine)\n");
                    sb.append("-> Hotel Abandonado (Rota 16)\n\n");

                    jogador.adicionarPista("MAPA DE JOHTO");
                    jogador.ganharExp(40); // Bônus de Investigação

                    sb.append("Você está prestes a memorizar mais detalhes quando ouve... vozes.\n");
                    sb.append("Lá fora. Passos se aproximando da porta da frente.\n");
                    sb.append("Você corre e se esconde debaixo do sofá velho, bem a tempo.\n\n");

                    sb.append("(Fim do Capítulo 4. EXP +50 [Bônus de Capítulo].)\n");
                    jogador.ganharExp(50);
                    
                    etapa = 99; // Finaliza o capítulo
                    
                } else {
                    return "Opção inválida. Digite 1 ou 2.";
                }
                break;
        }

        return sb.toString();
    }
}