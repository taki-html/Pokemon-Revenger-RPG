package Interface;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.*;

public class Servidor {

    public static void main(String[] args) {
        // Porta do servidor
        port(4567);

        // Serve arquivos HTML/CSS/JS/IMG da pasta Interface (sem src)
        staticFiles.externalLocation("Interface");

        Gson gson = new Gson();

        // Endpoint que manda os dados da tela inicial
        get("/api/inicial", (req, res) -> {
            res.type("application/json");

            Map<String, Object> dados = new HashMap<>();

            dados.put("titulo", "Pokémon: Revenge");

            dados.put("resumo",
                "Após um ataque misterioso, você desperta em um laboratório abandonado. " +
                "Seu passado foi apagado e suas decisões moldarão o destino deste mundo."
            );

            List<Map<String, String>> equipe = new ArrayList<>();

            equipe.add(
                Map.of(
                    "nome", "Pablo Vargas",
                    "papel", "Interface / conexão com o Java"
                )
            );

            equipe.add(
                Map.of(
                    "nome", "Fulano",
                    "papel", "Interface e UX"
                )
            );

            equipe.add(
                Map.of(
                    "nome", "Ciclano",
                    "papel", "Mecânicas e Batalhas"
                )
            );

            dados.put("equipe", equipe);

            return gson.toJson(dados);
        });
    }
}
