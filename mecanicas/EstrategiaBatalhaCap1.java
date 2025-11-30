package mecanicas;

import personagem.Personagem;

public class EstrategiaBatalhaCap1 implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        return new String[] {
            "[LUTAR]: (Você se prepara para usar seu ataque mais forte contra o Quilava.)",
            "[PROTEGER]: (Você se joga na frente do Sentret para protegê-lo.)",
            "[FUGIR]: (Você tenta correr para a grama alta.)"
        };
    }

    @Override
    public String executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Batalha batalha) {
        StringBuilder sb = new StringBuilder();

        switch (escolha) {
            case 1: // Lutar
                sb.append("Você dispara seu ataque (ATK ").append(p1.getAtk())
                  .append("), mas o Quilava (AGI ").append(p2.getAgi())
                  .append(") o desvia com um movimento treinado...\n");
                sb.append("...e te acerta com um Ataque Rápido (Quick Attack) que te joga no chão. A dor é aguda.\n");
                break;
            case 2: // Proteger
                sb.append("Você se coloca na frente do Sentret, que corre assustado.\n");
                sb.append("O Quilava te atinge com um Brasa (Ember) de aviso, queimando seu pelo/pele.\n");
                sb.append("Você grita de dor, uma dor que nunca sentiu antes.\n");
                break;
            case 3: // Fugir
                sb.append("Você mal dá dois passos antes que o Quilava (AGI ").append(p2.getAgi())
                  .append(") use Ataque Rápido e apareça na sua frente...\n");
                sb.append("...bloqueando seu caminho. Não há escapatória.\n");
                break;
            default:
                return "Escolha inválida.";
        }

        // Define mecanicamente a derrota
        p1.setHp(1); 
        batalha.setBatalhaTerminou(true); // Termina a batalha
        
        // --- CONCLUSÃO ---
        sb.append("\n(Qualquer escolha que você faça é fútil. A diferença de poder é muito grande.)\n");
        sb.append("(Esta é uma batalha impossível de vencer...)\n");
        sb.append("(HP ").append(p1.getHp()).append("/").append(p1.getHpMax()).append(")\n");
        
        return sb.toString();
    }

    @Override
    public String executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        // Nesta batalha roteirizada, o inimigo não age "mecanicamente" depois do jogador,
        // pois a batalha acaba no turno do jogador.
        return "";
    }

    @Override
    public boolean isBatalhaImpossivel() {
        return true;
    }
}