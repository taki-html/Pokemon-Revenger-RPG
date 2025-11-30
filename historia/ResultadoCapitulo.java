package historia;
public class ResultadoCapitulo {
    
    private final boolean jogoContinua;
    private final String mensagemFinal;

    // Construtor para quando o jogo continua
    
    public ResultadoCapitulo() {
        this.jogoContinua = true;
        this.mensagemFinal = "";
    }

    // Construtor para quando o jogo termina (Final Ruim)
    public ResultadoCapitulo(String mensagemFinal) {
        this.jogoContinua = false;
        this.mensagemFinal = mensagemFinal;
    }

    public boolean jogoContinua() {
        return jogoContinua;
    }

    public String getMensagemFinal() {
        return mensagemFinal;
    }
    
    // Resultados estáticos pré-definidos para Finais Ruins
    
    public static ResultadoCapitulo finalRuim(String mensagem) {
        return new ResultadoCapitulo(mensagem);
    }
    
    public static ResultadoCapitulo continuar() {
        return new ResultadoCapitulo();
    }
}