import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ServidorHttp {

    public static void main(String[] args) throws IOException {
        // Crear un servidor en el puerto 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Crear un manejador de contexto para la ruta /servicios
        server.createContext("/servicios", new ServiciosHandler());

        // Iniciar el servidor
        server.setExecutor(null); // Usar el ejecutor por defecto
        server.start();

        System.out.println("Servidor iniciado en http://127.0.0.1:8080/");
    }

    // Clase para manejar las solicitudes en la ruta /servicios
    static class ServiciosHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Obtener los parámetros de la URL
            Map<String, String> queryParams = getQueryParams(exchange.getRequestURI());

            // Obtener los valores de los parámetros
            String numero = queryParams.get("numero");
            String servicio = queryParams.get("servicio");

            // Imprimir los parámetros en la terminal
            System.out.println("Número: " + numero);
            System.out.println("Servicio: " + servicio);

            // Procesar el número según el servicio
            String resultado = procesarServicio(numero, servicio);

            // Construir la respuesta
            String response = "Número: " + numero + "\nServicio: " + servicio + "\nResultado: " + resultado;

            // Configurar la cabecera de la respuesta
            exchange.sendResponseHeaders(200, response.length());

            // Obtener el flujo de salida y escribir la respuesta
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // Método para procesar el número según el servicio
        private String procesarServicio(String numero, String servicio) {
            // Lógica para procesar el número según el servicio
            switch (servicio) {
                case "1":
                    // Enviar el número al servicio 1 (por ejemplo, http://127.0.0.1:8081)
                    enviarAServicio(numero, "http://127.0.0.1:8081/receive-number");
                    return "Número enviado al Servicio 1";
                case "2":
                    // Puedes agregar lógica para otros servicios aquí
                    enviarAServicio(numero, "http://127.0.0.1:8082/receive-number");
                    return "Número enviado al Servicio 2";
                case "3":
                    enviarAServicio(numero, "http://127.0.0.1:8081/receive-number");
                    enviarAServicio(numero, "http://127.0.0.1:8082/receive-number");
                    return "Número enviado al Servicio 1 y 2";
                default:
                    return "Servicio no válido";
            }
        }

        // Método para enviar el número a una dirección específica
        private void enviarAServicio(String numero, String direccionServicio) {
            try {
                URL url = new URL(direccionServicio);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Enviar el número al servicio
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = numero.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Respuesta del Servicio: " + responseCode);

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener parámetros de consulta de la URL
    static Map<String, String> getQueryParams(URI uri) {
        Map<String, String> result = new HashMap<>();
        String query = uri.getQuery();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }
}
