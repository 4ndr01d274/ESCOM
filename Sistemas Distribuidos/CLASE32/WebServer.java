import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import com.sun.net.httpserver.*;
import org.apache.commons.lang3.SerializationUtils;

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
        // ... (Código anterior)
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException, ClassNotFoundException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        boolean isDebugMode = headers.containsKey("X-Debug") && "true".equalsIgnoreCase(headers.getFirst("X-Debug"));

        long startTime = System.nanoTime();

        // Lee el cuerpo de la solicitud
        byte[] requestBytes = exchange.getRequestBody().readAllBytes();

        // Deserializa el objeto Demo
        Demo demoObject = (Demo) SerializationUtils.deserialize(requestBytes);

        // Imprime el contenido del objeto recibido
        System.out.println("Objeto recibido en el servidor:");
        System.out.println("a = " + demoObject.a);
        System.out.println("b = " + demoObject.b);

        // Puedes realizar operaciones adicionales con el objeto si es necesario

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            long nanos = finishTime - startTime;
            exchange.getResponseHeaders().add("X-Debug-Info", String.format("Tiempo de proceso: %d nanoseconds", nanos));
        }

        // Envía una respuesta al cliente si es necesario
        String responseText = "Objeto Demo recibido exitosamente en el servidor.";
        byte[] responseBytes = responseText.getBytes();
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
