package historia.capitulos;

import historia.Capitulo;
import historia.ResultadoCapitulo;
import java.util.Scanner;
import mecanicas.Batalha;
import mecanicas.EstrategiaBatalhaCap3;
import mecanicas.IEstrategiaBatalha;
import personagem.Inimigo;
import personagem.Jogador;
import util.Util;


public class CapituloTres implements Capitulo {

    private Inimigo quilava;

    @Override
    public ResultadoCapitulo executar(Jogador jogador, Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 3: O ENCONTRO ---");
        System.out.printf("(Status Atual: Nível %d. HP %d/%d. EXP %d/%d)\n",
                jogador.getNivel(), jogador.getHp(), jogador.getHpMax(), jogador.getExp(), jogador.getExpParaProximoNivel());

        Util.aguardarEnter(scanner);
        
        System.out.println("\nVocê está sendo carregado na gaiola. O humano ('Ketch') está suando e visivelmente nervoso.");
        System.out.println("Ele te leva para um prédio abandonado e te deixa no corredor.");
        System.out.println("(O Quilava está com ele, parecendo tão nervoso quanto o mestre.)");
        Util.aguardarEnter(scanner);
        
        System.out.println("Ketch: \"Quil, vigie a 'mercadoria'. Eu já volto. E FIQUE QUIETO.\"");
        System.out.println("(Ele entra em uma sala. Você ouve vozes abafadas. A trava da gaiola ainda está frouxa, como o Quilava deixou.)");
        System.out.println("(O Quilava te olha. Ele parece implorar para que você não faça barulho.)");
        Util.aguardarEnter(scanner);

        int escolha = Util.obterEscolha(scanner, "O QUE VOCÊ FAZ?",
                "[ESPIAR A REUNIÃO] (Você precisa saber o que está acontecendo.)",
                "[FICAR QUIETO E OUVIR] (É muito arriscado se mover. É melhor só escutar.)");

        if (escolha == 1) {
            // CAMINHO A: ESPIAR (Pista Ativa)
            System.out.println("\nVocê empurra a trava. Clic. Você pisa fora da gaiola.");
            System.out.println("(O Quilava treme, mas não faz nada para te impedir.)");
            System.out.println("Você se esgueira até a fresta da porta.");
            Util.aguardarEnter(scanner);
            
            System.out.println("(Você vê 'Ketch' falando com uma mulher alta, de uniforme preto e um sorriso frio.)");
            System.out.println("Mulher: \"...e sua 'mercadoria' está ferida, Ketch. Você é um incompetente.\"");
            System.out.println("Ketch: \"Mas ele é forte! Ele tem o espírito!\"");
            System.out.println("Mulher: \"Espírito não paga as contas. Onde está o pagamento?\"");
            System.out.println("(Ketch entrega a ela uma pequena caixa. Você vê o que tem dentro: Pokébolas... marcadas com um SÍMBOLO estranho.)");
            Util.aguardarEnter(scanner);
            
            System.out.println("Mulher: \"Bom. Agora, o teste final. Traga-o.\"");
            jogador.adicionarPista("SÍMBOLO DA EQUIPE"); // Pista 1
            jogador.ganharExp(30); // Bônus de Curiosidade
            
            System.out.println("(Você corre de volta para a gaiola, fechando a porta a tempo!)");

        } else {
            // CAMINHO B: OUVIR (Pista Passiva)
            System.out.println("\nVocê se encolhe no fundo da gaiola. O medo é maior.");
            System.out.println("(O Quilava solta um suspiro de alívio.)");
            System.out.println("Você se concentra nas vozes abafadas...");
            Util.aguardarEnter(scanner);
            
            System.out.println("Voz (Ketch): \"...Eu juro, ele é forte! Ele tem o espírito!\"");
            System.out.println("Voz (Fria): \"Sua 'mercadoria' parece patética, Ketch. Ele está ferido. Você falhou na captura.\"");
            System.out.println("Voz (Ketch): \"Mas... mas o símbolo! Eu vi! O Símbolo do Trovão!\"");
            System.out.println("Voz (Fria): \"Isso é lenda. O *Projeto Trovão* não existe. Você está dispensado.\"");
            Util.aguardarEnter(scanner);
            
            System.out.println("Voz (Ketch): \"Não! Espere! Eu posso provar! Eu vou provar!\"");
            jogador.adicionarPista("PROJETO TROVÃO"); // Pista 2
            jogador.incrementarInacao(); // Salva a inação
        }

        // --- Clímax do Capítulo ---
        System.out.println("\n(Clímax do Capítulo)");
        System.out.println("A porta da sala se abre com um estrondo.");
        System.out.println("A Mulher do Uniforme sai, te olhando com desprezo.");
        System.out.println("Ketch a segue, pálido e tremendo de raiva.");
        Util.aguardarEnter(scanner);
        
        System.out.println("Ketch: \"Fraco? Você me acha fraco? Você acha minha captura 'patética'?\"");
        System.out.println("Ketch: \"EU VOU TE MOSTRAR QUEM É FRACO!\"");
        Util.aguardarEnter(scanner);
        
        System.out.println("\n(Ele chuta sua gaiola e a abre.)");
        System.out.println("Ketch: \"SAIA!\"");
        System.out.println("Ketch: \"Quilava! POSIÇÃO DE BATALHA!\"");
        Util.aguardarEnter(scanner);
        
        System.out.println("O Quilava te encara, com as chamas baixas. Ele está apavorado.");
        System.out.println("Ketch: \"A 'mercadoria' precisa de um teste final. ATAQUE O QUILAVA. Mostre a ela do que você é capaz! AGORA!\"");
        Util.aguardarEnter(scanner);

        // --- Batalha ---
        
        this.quilava = new Inimigo("Quilava Assustado", 20, 5, 5, 7, 0); 
        
        IEstrategiaBatalha estrategiaCap3 = new EstrategiaBatalhaCap3();
        
        Batalha batalhaCap3 = new Batalha(jogador, this.quilava, scanner, estrategiaCap3);

        batalhaCap3.iniciarBatalha(); 

        if (!this.quilava.estaVivo()) {
            // O jogador ATACOU e derrotou o Quilava.
            System.out.println("\n(O Quilava está caído no chão, nocauteado. Ele não revidou.)");
            System.out.println("Ketch: \"VIU SÓ? VIU O PODER? EU DISSE!\"");
            System.out.println("A Mulher: \"...Interessante. Talvez. Traga-o.\"");
            System.out.println("(Ela vai embora. Ketch te joga na gaiola.)");
            System.out.println("Ketch: \"Você... você é forte. Você vai me fazer ser respeitado.\"");
            System.out.println("(Você traiu seu único aliado. EXP + 50)");
            jogador.adicionarTraicao(1); // Flag de Traição
            jogador.ganharExp(50);
        } else {
            // O jogador HESITOU ou PROTEGEU.
            System.out.println("\n(Você se recusou a lutar.)");
            System.out.println("Ketch: \"O QUE VOCÊ ESTÁ FAZENDO? LUTE! SEU LIXO INÚTIL!\"");
            System.out.println("A Mulher: \"Patético. Ketch, seu tempo acabou.\"");
            System.out.println("(Ela se vira para ir embora.)");
            System.out.println("Ketch: \"NÃO! É CULPA SUA!\" (Ele levanta a bota para chutar sua gaiola.)");
            System.out.println("(O Quilava salta na frente, recebendo o golpe e protegendo você!)");
            System.out.println("A Mulher: \"...Basta.\" (Ela vai embora, rindo.)");
            System.out.println("Ketch: \"Você... você arruinou tudo!\"");
            System.out.println("(Você protegeu seu aliado. EXP + 50)");
            jogador.adicionarLealdade(1); // Flag de Lealdade
            jogador.ganharExp(70);
        }
        
        Util.aguardarEnter(scanner);
        jogador.mostrarStatus();
        
        System.out.println("\n(Fim do Capítulo 3.)");
        Util.aguardarEnter(scanner);
        return ResultadoCapitulo.continuar();
    }
}