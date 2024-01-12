import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Servicio1 {

    public static void main(String[] args) throws IOException {
        int port = 8081; // Puedes cambiar el puerto según tus necesidades

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/receive-number", new NumberHandler());
        server.setExecutor(null); // Usa el ejecutor por defecto
        server.start();

        System.out.println("Servidor iniciado en el puerto " + port);
    }

    static class NumberHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Obtén el número enviado en el cuerpo de la solicitud
            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            int receivedNumber = Integer.parseInt(requestBody);

            // Verifica si el número es primo
            boolean isPrime = isPrime(receivedNumber);

            // Construye la respuesta
            String response;
            if (isPrime) {
                response = "El número recibido es primo";
            } else {
                response = "El número recibido no es primo";
            }

            // Puedes imprimir el resultado en la consola
            System.out.println(response);

            // Envía la respuesta al cliente
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
        }

        // Función para verificar si un número es primo
        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
