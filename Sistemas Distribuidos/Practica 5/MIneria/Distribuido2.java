import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Distribuido2 {

    public static void main(String[] args) throws IOException {
        int port = 8082;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/receive-text", new TextHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Servidor iniciado en el puerto " + port);
    }

    static class TextHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                // Obtén el texto enviado en el cuerpo de la solicitud
                String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(requestBody);

                // Utiliza el código de procesamiento de frase de Distribuido1
                Map<String, Double> sumaTFIDFporLibro = procesarFrase(requestBody);

                // Construye la respuesta con todos los valores de TF-IDF
                StringBuilder response = new StringBuilder();
                for (Map.Entry<String, Double> entry : sumaTFIDFporLibro.entrySet()) {
                    response.append(entry.getValue()).append(",").append("\n");
                }

                // Convierte la respuesta a bytes
                byte[] responseBytes = response.toString().getBytes(StandardCharsets.UTF_8);

                // Configura los encabezados de la respuesta con el código 200 (OK) y la longitud del mensaje
                exchange.sendResponseHeaders(200, responseBytes.length);

                // Obtiene el OutputStream del cuerpo de la respuesta
                try (OutputStream os = exchange.getResponseBody()) {
                    // Escribe los bytes en el cuerpo de la respuesta
                    os.write(responseBytes);
                }

                // Imprime la respuesta en la consola del servidor
                System.out.println("Enviando respuesta al cliente:\n" + response.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private Map<String, Double> procesarFrase(String frase) {
            // Especifica la ruta de la carpeta que deseas leer
            Path rutaCarpeta = Paths.get("./LIBROS_TXT");
        
            // Crea un objeto File para representar la carpeta
            File carpeta = rutaCarpeta.toFile();
        
            // Verifica si la carpeta existe
            if (carpeta.exists() && carpeta.isDirectory()) {
                // Obtiene la lista de archivos en la carpeta
                File[] archivos = carpeta.listFiles();
        
                // Itera sobre la lista de archivos
                if (archivos != null) {
                    int numDocumentos = archivos.length; // Número total de documentos
        
                    // Extrae las palabras de la frase
                    Set<String> palabrasEnFrase = extraerPalabrasDeFrase(frase);
        
                    // Mapa para almacenar la suma de TF-IDF por libro
                    Map<String, Double> sumaTFIDFporLibro = new HashMap<>();
        
                    // Utilizar ExecutorService para procesamiento concurrente
                    ExecutorService executorService = Executors.newFixedThreadPool(Math.min(archivos.length, 10));
        
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            executorService.execute(() -> {
                                // Calcula el TF-IDF de cada palabra en la frase
                                double sumaTFIDF = 0.0;
        
                                for (String palabra : palabrasEnFrase) {
                                    // Cuenta las ocurrencias de la palabra en cada archivo
                                    int contador = contarPalabraEnArchivo(archivo, palabra);
        
                                    // Calcula el TF de la palabra buscada en el archivo
                                    double tf = calcularTF(archivo, palabra);
        
                                    // Calcula el IDF de la palabra en la colección
                                    double idf = calcularIDF(numDocumentos, contarDocumentosConPalabra(archivos, palabra));
        
                                    // Calcula el TF-IDF de la palabra en el archivo
                                    double tfidf = calcularTFIDF(tf, idf);
        
                                    // Agrega el TF-IDF al total de la suma
                                    sumaTFIDF += tfidf;
                                }
        
                                // Almacena la suma de TF-IDF por libro en el mapa
                                synchronized (sumaTFIDFporLibro) {
                                    sumaTFIDFporLibro.put(archivo.getName(), sumaTFIDF);
                                }
                            });
                        }
                    }
        
                    // Apaga el ExecutorService de manera ordenada
                    executorService.shutdown();
                    try {
                        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        
                    // Imprime la suma total de TF-IDF por libro
                    for (Map.Entry<String, Double> entry : sumaTFIDFporLibro.entrySet()) {
                        // System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
        
                    
                    
                    // Devuelve el mapa con la suma de TF-IDF por libro
                    return sumaTFIDFporLibro;
                } else {
                    System.out.println("La carpeta está vacía.");
                }
            } else {
                System.out.println("La carpeta no existe o no es un directorio.");
            }
        
            // En caso de error, devuelve un mapa vacío o maneja el flujo según tu lógica
            return Collections.emptyMap();
        }
        private static Set<String> extraerPalabrasDeFrase(String frase) {
            // Utiliza un conjunto para evitar duplicados
            Set<String> palabras = new HashSet<>();
    
            // Dividir la frase en palabras
            String[] palabrasArray = frase.split("\\s+");
    
            // Agregar las palabras al conjunto
            palabras.addAll(Arrays.asList(palabrasArray));
    
            return palabras;
        }
        
        private void sendResponse(HttpExchange exchange, String message) throws IOException {
            // Configura los encabezados de la respuesta con el código 200 (OK) y la longitud del mensaje
            exchange.sendResponseHeaders(200, message.length());
        
            // Obtiene el OutputStream del cuerpo de la respuesta
            try (OutputStream os = exchange.getResponseBody()) {
                // Escribe el mensaje en el cuerpo de la respuesta
                os.write(message.getBytes(StandardCharsets.UTF_8));
            }
        }
        
        private static int contarPalabraEnArchivo(File archivo, String palabra) {
            int contador = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Utilizar expresiones regulares para obtener palabras
                    String[] palabras = linea.split("\\W+");
                    for (String palabraEnLinea : palabras) {
                        if (palabraEnLinea.equals(palabra)) {
                            contador++;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contador;
        }
    
        private static double calcularTF(File archivo, String palabra) {
            int totalPalabras = contarTotalPalabrasEnArchivo(archivo);
            int ocurrencias = contarPalabraEnArchivo(archivo, palabra);
    
            // Evitar dividir por cero
            if (totalPalabras > 0) {
                return (double) ocurrencias / totalPalabras;
            } else {
                return 0.0;
            }
        }
    
        private static int contarTotalPalabrasEnArchivo(File archivo) {
            int totalPalabras = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Contar el número de palabras en cada línea
                    totalPalabras += linea.split("\\s+").length;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return totalPalabras;
        }
    
        private static int contarDocumentosConPalabra(File[] archivos, String palabra) {
            int documentosConPalabra = 0;
            for (File archivo : archivos) {
                if (archivo.isFile() && contarPalabraEnArchivo(archivo, palabra) > 0) {
                    documentosConPalabra++;
                }
            }
            return documentosConPalabra;
        }
    
        private static double calcularIDF(int numDocumentos, int numDocumentosConPalabra) {
            // Evitar dividir por cero
            if (numDocumentosConPalabra > 0) {
                return Math.log((double) numDocumentos / numDocumentosConPalabra);
            } else {
                return 0.0;
            }
        }
    
        private static double calcularTFIDF(double tf, double idf) {
            return tf * idf;
        }
    
        private static List<String> obtenerTop10Libros(Map<String, Double> sumaTFIDFporLibro) {
            // Ordena el mapa por valores en orden descendente
            List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(sumaTFIDFporLibro.entrySet());
            listaOrdenada.sort(Map.Entry.<String, Double>comparingByValue().reversed());
        
            // Obtén los 10 libros con mayor valor en la suma total de TF-IDF
            List<String> top10Libros = new ArrayList<>();
            for (Map.Entry<String, Double> entry : listaOrdenada.subList(0, Math.min(10, listaOrdenada.size()))) {
                top10Libros.add(entry.getKey());
            }
        
            return top10Libros;
        
        }
    }
}
