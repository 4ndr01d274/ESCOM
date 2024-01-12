import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.nio.charset.StandardCharsets;

public class WebServer {
    private static final String[] MINERIA_URLS = {
        "http://localhost:8081",
        "http://localhost:8082",
        "http://localhost:8083"
    };

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor web iniciado en el puerto 8080");

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String line;
                StringBuilder requestBuilder = new StringBuilder();
                while (!(line = in.readLine()).isBlank()) {
                    requestBuilder.append(line).append("\r\n");
                }

                String request = requestBuilder.toString();

                if (request.startsWith("GET")) {
                    sendFormPage(out);
                } else if (request.startsWith("POST")) {
                    String requestBody = getRequestBody(request, in);
                    if (requestBody != null && requestBody.contains("words=")) {
                        String wordsString = URLDecoder.decode(requestBody.split("words=")[1], StandardCharsets.UTF_8);
                        String[] words = wordsString.split("\\+");
                        Map<String, Map<String, Integer>> processingResults = sendWordsToProcessingServer(words);
                        sendResponse(out, formatResultsAsHTML(processingResults));
                    } else {
                        sendResponse(out, "No se recibieron palabras.");
                    }
                } else {
                    sendNotFoundResponse(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendFormPage(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<form action=\"/\" method=\"post\">");
        out.println("Palabras: <input type=\"text\" name=\"words\"><br>");
        out.println("<input type=\"submit\" value=\"Enviar\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    private static String getRequestBody(String request, BufferedReader in) throws IOException {
        int contentLength = getContentLength(request);
        if (contentLength > 0) {
            char[] buffer = new char[contentLength];
            in.read(buffer, 0, contentLength);
            return new String(buffer);
        }
        return null;
    }

    private static int getContentLength(String request) {
        String contentLengthPrefix = "Content-Length: ";
        return Arrays.stream(request.split("\r\n"))
                     .filter(line -> line.startsWith(contentLengthPrefix))
                     .findFirst()
                     .map(line -> Integer.parseInt(line.substring(contentLengthPrefix.length())))
                     .orElse(0);
    }

    private static void sendResponse(PrintWriter out, String message) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println(message);
        out.println("</body></html>");
    }

    private static void sendNotFoundResponse(PrintWriter out) {
        out.println("HTTP/1.1 404 Not Found");
        out.println();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<h1>404 Not Found</h1>");
        out.println("</body></html>");
    }

    private static Map<String, Map<String, Integer>> sendWordsToProcessingServer(String[] words) {
        ExecutorService executor = Executors.newFixedThreadPool(MINERIA_URLS.length);
        Map<String, Map<String, Integer>> finalResults = new ConcurrentHashMap<>();

        List<Future<?>> futures = new ArrayList<>();
        for (String url : MINERIA_URLS) {
            Future<?> future = executor.submit(() -> {
                try {
                    URL processingUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) processingUrl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(("words=" + String.join("+", words)).getBytes(StandardCharsets.UTF_8));
                        os.flush();
                    }

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while (!(line = reader.readLine()).equals("END")) {
                            if (line.isEmpty()) continue;
                            String[] parts = line.split(":");
                            finalResults.computeIfAbsent(parts[0], k -> new ConcurrentHashMap<>())
                                         .merge(parts[1], Integer.parseInt(parts[2]), Integer::sum);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        return finalResults;
    }

    private static String formatResultsAsHTML(Map<String, Map<String, Integer>> results) {
        // Ordenar los archivos por el total de ocurrencias de todas las palabras
        List<Map.Entry<String, Map<String, Integer>>> sortedResults = results.entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(
                e2.getValue().values().stream().mapToInt(Integer::intValue).sum(),
                e1.getValue().values().stream().mapToInt(Integer::intValue).sum()))
            .collect(Collectors.toList());

        // Construir el HTML con los resultados
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<h2>Resultados de Minería de Texto</h2>");

        for (Map.Entry<String, Map<String, Integer>> entry : sortedResults) {
            String file = entry.getKey();
            Integer total = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
            htmlBuilder.append("<p>").append(file).append(": ").append(total).append("</p>");
        }

        return htmlBuilder.toString();
    }
}