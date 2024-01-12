import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//LOPEZ PEREZ ALBERTO ANDREI    PRACTICA 4      7CV2 
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SubtitleServer {

    public static void main(String[] args) {
        final int PUERTO = 54321;
        ExecutorService executorService = Executors.newFixedThreadPool(10); // Puedes ajustar el número de hilos según tus necesidades

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO + "...");

            while (true) {
                Socket cliente = servidor.accept();
                

                // Usa un hilo para manejar la conexión del cliente
                executorService.execute(() -> handleClient(cliente));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket cliente) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            // Leer el mensaje del cliente
            String mensajeCliente = in.readLine();

            if ("Health Check".equals(mensajeCliente)) {
                // Respuesta al cliente para indicar que el servidor está operativo
                out.println("El servidor esta Vivo");
            } else {
                // Resto del código de manejo del cliente aquí
                String nombreArchivo = mensajeCliente;
                int numeroInicio = Integer.parseInt(in.readLine());

                // Obtener subtítulos y dividir en número de línea, texto de subtítulo y marca de tiempo
                String subtitulos = obtenerSubtitulos(nombreArchivo);
                String[] bloques = dividirBloques(subtitulos);

                // Enviar bloques al cliente
                enviarSubtitulos(cliente, bloques, numeroInicio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String obtenerSubtitulos(String nombreArchivo) throws IOException {
        StringBuilder subtitulos = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("PELICULAS/" + nombreArchivo + ".srt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                subtitulos.append(linea).append("\n");
            }
        }
        return subtitulos.toString();
    }

    private static void enviarSubtitulos(Socket cliente, String[] bloques, int numeroInicio) throws IOException {
        try (PrintWriter out = new PrintWriter(cliente.getOutputStream(), true)) {
            for (int i = numeroInicio - 1; i < bloques.length; i++) {
                String bloque = bloques[i];
                String textoSubtitulo = convertSrtToText(bloque);
                out.println(textoSubtitulo);
                long durationInSeconds = getDurationInSeconds(bloque);
                
                try {
                    Thread.sleep(durationInSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Manejar la excepción o realizar alguna acción apropiada
                }
            }
        }
    }

    private static String[] dividirBloques(String srt) {
        // Dividir bloques utilizando expresión regular
        return srt.split("\\n\\n");
    }

    /*private static String extractLineNumber(String srt) {
        // Utilizar expresión regular para extraer el número de línea
        Pattern pattern = Pattern.compile("^(\\d+)");
        Matcher matcher = pattern.matcher(srt);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String extractTimestamp(String srt) {
        // Utilizar expresión regular para extraer la marca de tiempo
        Pattern pattern = Pattern.compile("\\b(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\b");
        Matcher matcher = pattern.matcher(srt);
        if (matcher.find()) {
            return matcher.group(0); // Devolver la marca de tiempo completa
        }
        return null;
    }*/

    private static long getDurationInSeconds(String srt) {
        // Utilizar expresión regular para extraer la marca de tiempo
        Pattern pattern = Pattern.compile("\\b(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\b");
        Matcher matcher = pattern.matcher(srt);
        if (matcher.find()) {
            String startTime = matcher.group(1);
            String endTime = matcher.group(2);
            return calculateDurationInSeconds(startTime, endTime);
        }
        return -1;
    }

    private static long calculateDurationInSeconds(String startTime, String endTime) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);

            long durationInMillis = endDate.getTime() - startDate.getTime();
            return durationInMillis / 1000; // Convertir a segundos
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String convertSrtToText(String srt) {
        // Utilizar expresión regular para eliminar los números de línea y las marcas de tiempo
        return srt.replaceAll("^\\d+\n([\\d:,]+ --> [\\d:,]+\n)", "");
    }
}
