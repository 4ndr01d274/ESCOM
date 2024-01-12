package com.mycompany.app;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebServer {

    private static final String STATUS_ENDPOINT = "/status";
    private static final String HOME_PAGE_ENDPOINT = "/";
    private static final String HOME_PAGE_UI_ASSETS_BASE_DIR = "/ui_assets/";
    private static final String ENDPOINT_PROCESS = "/procesar_datos";

    private final int port;
    private HttpServer server;
    private final ObjectMapper objectMapper;

    public WebServer(int port) {
        this.port = port;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(ENDPOINT_PROCESS);
        HttpContext homePageContext = server.createContext(HOME_PAGE_ENDPOINT);
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        homePageContext.setHandler(this::handleRequestForAsset);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleRequestForAsset(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        byte[] response;

        String asset = exchange.getRequestURI().getPath();

        if (asset.equals(HOME_PAGE_ENDPOINT)) {
            response = readUiAsset(HOME_PAGE_UI_ASSETS_BASE_DIR + "index.html");
        } else {
            response = readUiAsset(asset);
        }
        addContentType(asset, exchange);
        sendResponse(response, exchange);
    }

    private byte[] readUiAsset(String asset) throws IOException {
        InputStream assetStream = getClass().getResourceAsStream(asset);

        if (assetStream == null) {
            return new byte[]{};
        }
        return assetStream.readAllBytes();
    }

    private static void addContentType(String asset, HttpExchange exchange) {

        String contentType = "text/html";
        if (asset.endsWith("js")) {
            contentType = "text/javascript";
        } else if (asset.endsWith("css")) {
            contentType = "text/css";
        }
        exchange.getResponseHeaders().add("Content-Type", contentType);
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        try {
            // Leer los datos de la solicitud
            byte[] requestBody = exchange.getRequestBody().readAllBytes();

            // Mapear los datos JSON a un objeto FrontendSearchRequest
            FrontendSearchRequest frontendSearchRequest = objectMapper.readValue(requestBody,
                    FrontendSearchRequest.class);
            String frase = frontendSearchRequest.getSearchQuery();

            String[] palabras = frase.split("\\s+");
            String[] palabras1 = new String[palabras.length];
            String[] palabras2 = new String[palabras.length];
            String[] palabras3 = new String[palabras.length];

            // Utilizar una estructura de datos compartida para almacenar las respuestas de los hilos
            StringBuilder responses = new StringBuilder();

            for (int i = 0; i < palabras.length; i++) {
                int endpointNumber = i % 3 + 1; // Alternar entre 1, 2 y 3

                if (endpointNumber == 1) {
                    palabras1[i] = palabras[i];
                } else if (endpointNumber == 2) {
                    palabras2[i] = palabras[i];
                } else if (endpointNumber == 3) {
                    palabras3[i] = palabras[i];
                }
            }

            // Crear hilos para enviar las palabras a los tres endpoints simultáneamente
            Thread thread1 = new Thread(() -> {
                sendToFallbackEndpoint(palabras1, "http://127.0.0.1:8081/receive-text", responses);
            });

            Thread thread2 = new Thread(() -> {
                sendToFallbackEndpoint(palabras2, "http://127.0.0.1:8082/receive-text", responses);
            });

            Thread thread3 = new Thread(() -> {
                sendToFallbackEndpoint(palabras3, "http://127.0.0.1:8083/receive-text", responses);
            });

            // Iniciar los hilos
            thread1.start();
            thread2.start();
            thread3.start();

            // Esperar a que todos los hilos terminen antes de responder al cliente
            try {
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Enviar al cliente las respuestas combinadas
            String combinedResponse = responses.toString();
            System.out.println(combinedResponse);
            List<String> numeros = Arrays.asList(combinedResponse.split(","));
            
            int batchSize = 46;
            int totalElementos = numeros.size()-1;
            int totalSublistas = (int) Math.ceil((double) totalElementos / batchSize);
        
            List<List<String>> listas = new ArrayList<>();
            for (int i = 0; i < totalSublistas; i++) {
            int startIndex = i * batchSize;
            int endIndex = Math.min(startIndex + batchSize, totalElementos);
            List<String> subList = numeros.subList(startIndex, endIndex);
            listas.add(subList);
            }

            List<Double> sumaListas = sumarListasElementoPorElemento(listas);
            
            String[] texto = {"Allende,Isabel__1982.La_casa_de_los_espíritus[563].txt",
            "Anónimo__2004_.Robin_Hood[11853].txt",
            "Asimov,Isaac__1967.Guía_de_la_Biblia__Antiguo_Testamento_[6134].txt",
            "Beevor,Antony__1998.Stalingrado[10491].txt",
            "Adler,Elizabeth__1991.La_esmeralda_de_los_Ivanoff[10057].txt",
            "Alten,Steve__2008.Al_borde_del_infierno[12141].txt",
            "Hawking,Stephen__1988.Historia_del_tiempo[8536].txt",
            "Gaarder,Jostein__1991.El_mundo_de_Sofía[6571].txt",
            "Hemingway,Ernest__1952.El_viejo_y_el_mar[1519].txt",
            "Aguilera,Juan_Miguel__1998.La_locura_de_Dios[5644].txt",
            "Alcott,Louisa_May__1871.Hombrecitos[15392].txt",
            "García_Márquez,Gabriel__1967.Cien_años_de_soledad[8376].txt",
            "Baum,Lyman_Frank__1900.El_Mago_de_Oz[15715].txt",
            "Hesse,Herman__1919.Demian[2612].txt",
            "Dostoievski,Fiódor__1865.Crimen_y_castigo[13400].txt",
            "Anónimo__1554_.Lazarillo_de_Tormes[11043].txt",
            "Alders,Hanny__1987.El_tesoro_de_los_templarios[13014].txt",
            "Esquivel,Laura__1989.Como_agua_para_chocolate[7750].txt",
            "Anderson,Sienna__2008.No_me_olvides[15047].txt",
            "Gorki,Máximo__1907.La_madre[1592].txt",
            "Benítez,_J._J._1984._Caballo_de_Troya_01__Jerusalén[4826].txt",
            "Asimov,Isaac__1950._Yo,robot[10874].txt",
            "García_Márquez,Gabriel__1985.El_amor_en_los_tiempos_del_cólera[874].txt",
            "Golding,William__1954.El_señor_de_las_moscas[6260].txt",
            "Allende,Isabel__1984.De_amor_y_de_sombra[6283].txt",
            "Hitler,Adolf__1935.Mi_lucha[11690].txt",
            "Bach,Richard__1970.Juan_Salvador_Gaviota[15399].txt",
            "Flaubert,Gustave__1857.Madame_Bovary[3067].txt",
            "Ende,Michael__1973.Momo[1894].txt",
            "Fromm,Erich__1947.El_miedo_a_la_libertad[11619].txt",
            "Alten,Steve__2001._Trilogía_maya_01__El_testamento_maya[8901].txt",
            "Alcott,Louisa_May__1868.Mujercitas[11086].txt",
            "Goleman,Daniel__1995.Inteligencia_emocional[4998].txt",
            "Gaiman,Neil__2002.Coraline[1976].txt",
            "Adler_Olsen,Jussi__1997.La_casa_del_alfabeto[7745].txt",
            "Dickens,Charles__1843.Cuento_de_Navidad[3285].txt",
            "García_Márquez,Gabriel__1989.El_general_en_su_laberinto[875].txt",
            "Huxley,Aldous__1932.Un_mundo_feliz[293].txt",
            "Archer,Jeffrey__1979.Kane_y_Abel[1965].txt",
            "Albom,Mitch__2002.Martes_con_mi_viejo_profesor[382].txt",
            "Hobbes,Thomas__1651.Leviatán[2938].txt",
            "Alameddine,Rabih__2008.El_contador_de_historias[5735].txt",
            "Asimov,Isaac__1985.El_monstruo_subatómico[167].txt",
            "Harris,Thomas__1988.El_silencio_de_los_inocentes[11274].txt",
            "Amis,Martin__1990.Los_monstruos_de_Einstein[8080].txt",
            "Alexander,Caroline__1998.Atrapados_en_el_hielo[15727].txt"};
            Map<String, Double> mapaUnido = new HashMap<>();

            // Llenar el Map con las asociaciones
            for (int i = 0; i < sumaListas.size(); i++) {
                mapaUnido.put(texto[i], sumaListas.get(i));
            }
    
            // Imprimir el Map

            System.out.println("Tu busqueda es: \n");
            obtenerTop10Libros(mapaUnido);


            List<String> top10Libros = obtenerTop10Libros(mapaUnido);

// Convertir la lista a una cadena para enviar al cliente
String top10LibrosResponse = String.join("\n", top10Libros);

// Crear un objeto FrontendSearchResponse
FrontendSearchResponse frontendSearchResponse = new FrontendSearchResponse(top10LibrosResponse,0);


// Convertir el objeto de respuesta a bytes
byte[] responseBytes = objectMapper.writeValueAsBytes(frontendSearchResponse);

// Enviar la respuesta al cliente
sendResponse(responseBytes, exchange);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    

    // Método para enviar la frase al nuevo endpoint
    private void sendToFallbackEndpoint(String[] palabras, String endpointURL, StringBuilder responses) {
        try {
            for (String palabra : palabras) {
                if (palabra != null) {
                    URL url = new URL(endpointURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
    
                    // Escribir la palabra en el cuerpo de la solicitud
                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = palabra.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
    
                    // Leer la respuesta del nuevo endpoint si es necesario
                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                        StringBuilder responseBody = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            responseBody.append(responseLine.trim());
                        }
                        // Agregar la respuesta al StringBuilder compartido
                        responses.append(responseBody.toString()).append("\n");
                        
                    }
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier error al enviar la solicitud al nuevo endpoint
        }
    }

    private static List<Double> sumarListasElementoPorElemento(List<List<String>> listas) {
        List<Double> sumaListas = new ArrayList<>();
    
        // Verificar si la lista de sublistas no está vacía
        if (listas != null && !listas.isEmpty()) {
            // Obtener la longitud de la primera sublista (se asume que todas tienen la misma longitud)
            int longitudSublista = listas.get(0).size();
    
            for (int i = 0; i < longitudSublista; i++) {
                double sumaElemento = 0.0;
    
                // Iterar sobre las sublistas
                for (List<String> sublista : listas) {
                    // Verificar que la sublista tenga elementos suficientes
                    if (i < sublista.size()) {
                        sumaElemento += Double.parseDouble(sublista.get(i));
                    }
                }
    
                // Agregar la suma de los elementos al resultado
                sumaListas.add(sumaElemento);
            }
        }
    
        return sumaListas;
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
        System.out.println("Enviando " + responseBytes.length + " bytes al cliente.");
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }


    public static void main(String[] args) {
        WebServer webServer = new WebServer(8080);
        webServer.startServer();
    }
    private static List<String> obtenerTop10Libros(Map<String, Double> mapaUnido) {
        // Ordena el mapa por valores en orden descendente
        List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(mapaUnido.entrySet());
        listaOrdenada.sort(Map.Entry.<String, Double>comparingByValue().reversed());
    
        // Obtén los 10 libros con mayor valor en la suma total de TF-IDF
        List<String> top10Libros = new ArrayList<>();
        for (Map.Entry<String, Double> entry : listaOrdenada.subList(0, Math.min(64, listaOrdenada.size()))) {
            top10Libros.add(entry.getKey());
            System.out.println(entry.getKey());
        }
    
        return top10Libros;
    }
}
