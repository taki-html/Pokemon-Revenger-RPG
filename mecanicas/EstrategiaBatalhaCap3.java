package mecanicas;

import personagem.Personagem;

public class EstrategiaBatalhaCap3 implements IEstrategiaBatalha {

    @Override
    public String[] getOpcoesDoJogador() {
        return new String[] {
            "[ATACAR]: (Você ataca o Quilava com força total.)",
            "[PROTEGER]: (Você assume uma postura defensiva.)",
            "[HESITAR]: (Você olha para o Quilava... e se recusa a lutar.)"
        };
    }

    @Override
    public String executarTurnoJogador(int escolha, Personagem p1, Personagem p2, Batalha batalha) {
        StringBuilder sb = new StringBuilder();

        switch (escolha) {
            case 1: // Atacar
                sb.append(String.format("%s ataca %s!\n", p1.getNome(), p2.getNome()));
                sb.append("(O Quilava não se defende!)\n");
                
                int dano = p1.atacar(p2);
                sb.append(String.format("Você causou %d de dano. (HP de %s: %d/%d)\n", dano, p2.getNome(), p2.getHp(), p2.getHpMax()));
                
                if (!p2.estaVivo()) {
                    sb.append(p2.getNome()).append(" foi nocauteado.\n");
                    batalha.setBatalhaTerminou(true); // O jogador venceu
                } else {
                     sb.append("Ketch: \"MAIS FORTE! ACABE COM ELE!\"\n");
                }
                break;

            case 2: // Proteger
            case 3: // Hesitar
                sb.append("Você se recusa a lutar.\n");
                sb.append("Ketch: \"O QUE ESTÁ FAZENDO? LUTE!\"\n");
                // O jogador não faz nada, a batalha termina (script)
                batalha.setBatalhaTerminou(true); 
                break;
            default:
                return "Opção inválida.";
        }
        return sb.toString();
    }

    @Override
    public String executarTurnoInimigo(Personagem p1, Personagem p2, Batalha batalha) {
        // O inimigo está com medo e não ataca
        return p2.getNome() + " te encara, com medo. Ele não vai revidar.\n";
    }

    @Override
    public boolean isBatalhaImpossivel() {
        // Retornamos 'true' aqui não porque o jogador perde,
        // mas para evitar mensagens genéricas de vitória/XP do sistema padrão.
        // Queremos controlar a narrativa.
        return true; 
    }
}