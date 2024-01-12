import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Servicio2 {

    public static void main(String[] args) throws IOException {
        int port = 8082; // Puedes cambiar el puerto según tus necesidades

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/receive-number", new SumOddNumbersHandler());
        server.setExecutor(null); // Usa el ejecutor por defecto
        server.start();

        System.out.println("Servidor iniciado en el puerto " + port);
    }

    static class SumOddNumbersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Obtén el número enviado en el cuerpo de la solicitud
            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            int receivedNumber = Integer.parseInt(requestBody);

            // Calcula la suma de todos los números impares menores al número recibido
            int sum = 0;
            for (int i = 1; i < receivedNumber; i += 2) {
                sum += i;
            }

            // Construye la respuesta
            String response = "La suma de todos los números impares menores que " + receivedNumber + " es: " + sum;

            // Puedes imprimir el resultado en la consola
            System.out.println(response);

            // Envía la respuesta al cliente
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
