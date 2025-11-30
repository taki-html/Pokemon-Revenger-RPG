package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaNormal;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;

public class CapituloNove implements Capitulo {

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 9: O CORAÇÃO DO CULTO ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());
        
        Util.aguardarEnter(scanner);

        System.out.println("Você arromba a porta final. É uma caverna subterrânea sob a torre.");
        System.out.println("O local vibra com uma energia roxa e doentia.");
        
        System.out.println("No centro, diante de um altar, está o LÍDER DO CULTO.");
        System.out.println("Ele é alto, imponente. O Talismã na mão dele brilha intensamente.");
        
        System.out.println("\nNo canto, encolhido como um servo, está Ketch, seu antigo treinador.");
        System.out.println("Líder: \"Então... o rato sobreviveu.\"");
        
        Util.aguardarEnter(scanner);
        
        System.out.println("Ele não está com raiva. Ele está... divertido.");
        System.out.println("Líder: \"Você vem para vingar seus amigos patéticos?\"");
        System.out.println("Líder: \"A fraqueza deve ser purificada. A 'Grande Arma' nos dará esse poder.\"");
        
        System.out.println("Ele levanta uma Ultra Ball.");
        System.out.println("Líder: \"Guardião... entretenha nosso convidado.\"");
        
        Util.aguardarEnter(scanner);

        System.out.println("Desta bola, não sai um Pokémon comum... sai uma besta.");
        System.out.println("Um TYRANITAR SOMBRIO. Olhos brilhando em roxo, corrompido pela energia do culto.");
        System.out.println("Ele ruge, e a caverna treme.");

        // Configuração do Boss Final
        Inimigo tyranitar = new Inimigo("Tyranitar Sombrio", 40, 12, 10, 10, 10);
        
        System.out.println("\n!!! BATALHA FINAL !!!");
        System.out.println("(Se você perder aqui, o mundo acaba.)");
        
        Batalha batalhaFinal = new Batalha(jogador, tyranitar, scanner, new EstrategiaBatalhaNormal());
        boolean venceu = batalhaFinal.iniciarBatalha();

        if (!venceu) {
            String msgFinal = "[FINAL RUIM 6: A PURIFICAÇÃO]\n" +
                              "O Tyranitar te joga contra a parede da caverna. A escuridão toma conta.\n" +
                              "O Líder do Culto ri: \"A fraqueza foi purificada.\"\n" +
                              "O ritual é concluído. Johto cai em sombras.";
            return ResultadoCapitulo.finalRuim(msgFinal);
        }

        // --- PÓS-BATALHA ---
        System.out.println("\nO Tyranitar ruge uma última vez e cai. A energia roxa desaparece de seus olhos.");
        System.out.println("Ele parece... aliviado. Ele foi libertado.");
        jogador.ganharExp(200); // XP Massivo

        Util.aguardarEnter(scanner);

        System.out.println("O Líder do Culto grita de raiva. \"NÃO! INÚTIL!\"");
        System.out.println("Ele corre para o Talismã no altar, que está sobrecarregado e instável.");
        System.out.println("Líder: \"EU MESMO FAREI ISSO! O PODER SERÁ MEU!\"");

        // Escolha Final de Ação
        int escolhaFinal = Util.obterEscolha(scanner, "O LÍDER ESTÁ VULNERÁVEL. ACABE COM ISSO.",
                "[ATACAR O LÍDER] (Acabar com ele. A vingança final.)",
                "[DESTRUIR O TALISMÃ] (Acabar com a fonte do poder. Salvar a todos.)");

        if (escolhaFinal == 1) {
            System.out.println("\nVocê cede ao ódio. Você dispara seu ataque contra o humano.");
            System.out.println("Ele é atingido e cai.");
            System.out.println("Mas o Talismã, sem controle, EXPLODE.");
            System.out.println("A caverna começa a desmoronar sobre todos vocês.");
            // Leva ao Final Bom (mas caótico), tratado no Cap 10, ou narrativa de fuga aqui mesmo.
            // Para simplificar, vamos considerar que o jogador escapa por pouco no Cap 10.
        } else {
            System.out.println("\nVocê ignora o humano. Você mira no cristal.");
            System.out.println("Você usa seu ataque mais forte contra o Talismã.");
            System.out.println("Líder: \"NÃO! O QUE VOCÊ FEZ?!\"");
            System.out.println("CRACK! O Talismã explode em mil pedaços.");
            System.out.println("A energia roxa se dissipa. O Líder cai de joelhos, impotente.");
        }

        System.out.println("\nKetch observa tudo do canto, em choque. Está acabado.");
        System.out.println("A vingança está completa. O Culto caiu.");
        
        Util.aguardarEnter(scanner);
        return ResultadoCapitulo.continuar();
    }
}