package mecanicas;

import personagem.Personagem;

public class EstrategiaBatalhaNormal implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        return new String[] {"Atacar", "Defender", "Fugir"};
    }

    @Override
    public String executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Batalha batalha) {
        StringBuilder sb = new StringBuilder();

        switch (escolha) {
            case 1: // Atacar
                sb.append(String.format("%s avança contra %s!\n", p1.getNome(), p2.getNome()));
                int dano = p1.atacar(p2);
                sb.append(String.format(" >> Dano causado: %d | HP Inimigo: %d/%d\n", dano, p2.getHp(), p2.getHpMax()));
                
                if (!p2.estaVivo()) batalha.setBatalhaTerminou(true);
                break;

            case 2: // Defender
                sb.append(p1.getNome()).append(" assume uma postura defensiva e recupera fôlego!\n");
                p1.curar(5);
                sb.append(" >> Recuperou 5 HP.\n");
                break;

            case 3: // Fugir
                sb.append(p1.getNome()).append(" tenta fugir...\n");
                if (p1.getAgi() * Math.random() > p2.getAgi() * Math.random()) {
                    sb.append("...e escapa com sucesso!\n");
                    batalha.setBatalhaTerminou(true);
                } else {
                    sb.append("...mas é interceptado!\n");
                }
                break;
            default:
                sb.append("Ação inválida. Perdeu a vez!\n");
        }
        return sb.toString();
    }

    @Override
    public String executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        if (!p2.estaVivo()) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s ataca %s!\n", p2.getNome(), p1.getNome()));
        
        int dano = p2.atacar(p1);
        sb.append(String.format(" >> %s recebeu %d de dano. (HP: %d/%d)\n", p1.getNome(), dano, p1.getHp(), p1.getHpMax()));

        return sb.toString();
    }

    @Override
    public boolean isBatalhaImpossivel() {
        return false;
    }
}