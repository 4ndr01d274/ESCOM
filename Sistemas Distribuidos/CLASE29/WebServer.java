import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String SEARCHTOKEN_ENDPOINT = "/searchtoken";

    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor en el puerto: " + serverPort);
    }

    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext searchTokenContext = server.createContext(SEARCHTOKEN_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        searchTokenContext.setHandler(this::handleSearchTokenRequest);

        server.setExecutor(Executors.newFixedThreadPool(8)); // Adjust the thread pool size if necessary.
        server.start();
    }

    private void handleSearchTokenRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        boolean isDebugMode = headers.containsKey("X-Debug") && "true".equalsIgnoreCase(headers.getFirst("X-Debug"));

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        String requestBody = new String(requestBytes);
        String[] params = requestBody.split(",");

        if (params.length < 2) {
            sendResponse("Numero incorrecto de parametros.".getBytes(), exchange);
            return;
        }

        int numTokens;
        String searchToken;
        try {
            numTokens = Integer.parseInt(params[0]);
            searchToken = params[1];
        } catch (NumberFormatException e) {
            sendResponse("Formato invalido de la solicitud. ingresa: numero_de_tokens,token_a_buscar".getBytes(), exchange);
            return;
        }

        char[] longString = generateLongString(numTokens);
        int occurrences = searchForSubstring(longString, searchToken);

        long finishTime = System.nanoTime();

        String responseMessage = String.format("Numero de ocurrencias de '%s': %d\n", searchToken, occurrences);

        if (isDebugMode) {
            long nanos = finishTime - startTime;
            exchange.getResponseHeaders().add("X-Debug-Info", String.format("Tiempo de proceso: %d nanoseconds", nanos));
        }

        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
    if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
        exchange.close();
        return;
    }

    Headers headers = exchange.getRequestHeaders();
    boolean isDebugMode = headers.containsKey("X-Debug") && "true".equalsIgnoreCase(headers.getFirst("X-Debug"));

    long startTime = System.nanoTime();

    // Aquí leías el cuerpo de la petición, pero si no vas a hacer ninguna operación con él,
    // puedes omitir esto o dejarlo para debug:
    byte[] requestBytes = exchange.getRequestBody().readAllBytes();
    System.out.println("HTTP cuerpo de http recibido: " + new String(requestBytes));

    // Elimina o comenta la siguiente línea que causa el error.
    // byte[] responseBytes = calculateResponse(requestBytes);

    // Si necesitas enviar una respuesta específica, define aquí los responseBytes.
    String responseText = "This is a response from the /task endpoint.";
    byte[] responseBytes = responseText.getBytes();

    long finishTime = System.nanoTime();

    if (isDebugMode) {
        long nanos = finishTime - startTime;
        long millis = nanos / 1_000_000;
        long seconds = millis / 1000;
        millis %= 1000;

        String debugMessage = String.format("Operacion tomo %d nanoseconds = %d seconds y %d milliseconds",
                                            nanos, seconds, millis);

        exchange.getResponseHeaders().add("X-Debug-Info", debugMessage);
    }

    sendResponse(responseBytes, exchange);
}


private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
    if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
        exchange.close();
        return;
    }

    String responseMessage = "El servidor está vivo\n";
    sendResponse(responseMessage.getBytes(), exchange);
}


    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }

    // Utility methods from the previous exercise
    public static char[] generateLongString(int n) {
        StringBuilder longString = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String word = generateRandomWord();
            longString.append(word).append(' ');
        }
        return longString.toString().toCharArray();
    }

    public static String generateRandomWord() {
        int wordLength = 3;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            word.append(randomChar);
        }
        return word.toString();
    }

    public static int searchForSubstring(char[] array, String sub) {
        int count = 0;
        String longString = new String(array);
        int index = longString.indexOf(sub);
        while (index >= 0) {
            count++;
            index = longString.indexOf(sub, index + sub.length());
        }
        return count;
    }
}
