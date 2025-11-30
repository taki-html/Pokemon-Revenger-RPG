package web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import main.Jogo;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Servidor {

    // Guarda as sessões de jogo: ID -> Jogo
    private static Map<String, Jogo> sessoes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int porta = 4567;
        HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);

        // ROTA 1: Iniciar Jogo
        server.createContext("/api/jogo/iniciar", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                if (!"POST".equalsIgnoreCase(t.getRequestMethod())) {
                    enviarResposta(t, 405, "Método não permitido");
                    return;
                }

                Jogo novoJogo = new Jogo();
                String idSessao = UUID.randomUUID().toString();
                sessoes.put(idSessao, novoJogo);

                // Inicia o jogo e pega o primeiro texto
                String textoIntro = novoJogo.processarEntrada("");

                // Monta JSON na mão (para não depender de Gson/Lib externa)
                String json = String.format("{\"idSessao\": \"%s\", \"texto\": \"%s\"}", 
                        idSessao, limparTextoParaJson(textoIntro));

                enviarResposta(t, 200, json);
            }
        });

        // ROTA 2: Receber Ação
        server.createContext("/api/jogo/acao", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                if (!"POST".equalsIgnoreCase(t.getRequestMethod())) {
                    enviarResposta(t, 405, "Método não permitido");
                    return;
                }

                // Lê o corpo da requisição (JSON enviado pelo JS)
                InputStream is = t.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                // Extração simples dos dados (sem biblioteca JSON)
                String idSessao = extrairValorJson(body, "idSessao");
                String inputUsuario = extrairValorJson(body, "input");

                Jogo jogoAtual = sessoes.get(idSessao);
                String respostaTexto;

                if (jogoAtual == null) {
                    respostaTexto = "Erro: Sessão expirada ou inválida.";
                } else {
                    respostaTexto = jogoAtual.processarEntrada(inputUsuario);
                }

                String json = String.format("{\"texto\": \"%s\"}", limparTextoParaJson(respostaTexto));
                enviarResposta(t, 200, json);
            }
        });

        // ROTA 3: Dados Iniciais (Resumo/Equipe)
        server.createContext("/api/inicial", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                String resumo = "Você é um Pokémon de Johto, arrancado de sua vida selvagem... " +
                        "Sua jornada se torna uma missão de vingança e libertação.";
                
                // JSON manual simples
                String json = "{" +
                        "\"titulo\": \"Pokémon: Revenge\"," +
                        "\"resumo\": \"" + resumo + "\"," +
                        "\"equipe\": [" +
                        "  {\"nome\": \"Dev Java\", \"papel\": \"Backend\"}," +
                        "  {\"nome\": \"Dev Web\", \"papel\": \"Frontend\"}" +
                        "]" +
                        "}";
                enviarResposta(t, 200, json);
            }
        });

        // ROTA 4: Arquivos Estáticos (HTML, CSS, JS)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                String uri = t.getRequestURI().getPath();
                if (uri.equals("/")) uri = "/index.html";

                // Procura na pasta "web" (antiga interface)
                File arquivo = new File("web" + uri); 
                // Se você não renomeou a pasta para "web", mude "web" para "interface" na linha acima!

                if (arquivo.exists() && !arquivo.isDirectory()) {
                    String mimeType = "text/plain";
                    if (uri.endsWith(".html")) mimeType = "text/html";
                    else if (uri.endsWith(".css")) mimeType = "text/css";
                    else if (uri.endsWith(".js")) mimeType = "application/javascript";
                    else if (uri.endsWith(".jpg")) mimeType = "image/jpeg";

                    t.getResponseHeaders().set("Content-Type", mimeType);
                    t.sendResponseHeaders(200, arquivo.length());
                    try (OutputStream os = t.getResponseBody()) {
                        Files.copy(arquivo.toPath(), os);
                    }
                } else {
                    String msg = "404 - Arquivo nao encontrado";
                    t.sendResponseHeaders(404, msg.length());
                    try (OutputStream os = t.getResponseBody()) {
                        os.write(msg.getBytes());
                    }
                }
            }
        });

        server.setExecutor(null);
        System.out.println("Servidor RPG rodando (Sem Spark) em http://localhost:" + porta);
        server.start();
    }

    // --- Métodos Auxiliares ---

    private static void enviarResposta(HttpExchange t, int status, String json) throws IOException {
        byte[] respostaBytes = json.getBytes(StandardCharsets.UTF_8);
        t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        t.sendResponseHeaders(status, respostaBytes.length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(respostaBytes);
        }
    }

    // Remove quebras de linha e aspas para não quebrar o JSON manual
    private static String limparTextoParaJson(String texto) {
        if (texto == null) return "";
        return texto.replace("\"", "'")
                    .replace("\n", "\\n")
                    .replace("\r", "");
    }

    // Parser simples de JSON para pegar valor por chave
    private static String extrairValorJson(String json, String chave) {
        try {
            String busca = "\"" + chave + "\":";
            int inicio = json.indexOf(busca);
            if (inicio == -1) return "";
            
            inicio += busca.length();
            
            // Pula espaços e a primeira aspas
            while (json.charAt(inicio) == ' ' || json.charAt(inicio) == '"' || json.charAt(inicio) == ':') {
                inicio++;
            }
            
            int fim = json.indexOf("\"", inicio);
            return json.substring(inicio, fim);
        } catch (Exception e) {
            return "";
        }
    }
}