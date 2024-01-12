//LOPEZ PEREZ ALBERTO ANDREI    PRACTICA 4      7CV2 
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerMonitor {

    private static final int MONITOR_PORT = 8888; // Puerto para el servidor de monitoreo
    private static final String SERVER_1_ADDRESS = "localhost";
    private static final int SERVER_1_PORT = 12345;
    private static final String SERVER_2_ADDRESS = "localhost";
    private static final int SERVER_2_PORT = 54321;
    private static final String LOG_FILE = "LOG.txt";

    public static void main(String[] args) {
        // Inicia el servidor de monitoreo
        startMonitorServer();

        // Programa la tarea de health check para cada servidor a intervalos regulares (3 segundos)
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(() -> healthCheckAndLog(SERVER_1_ADDRESS, SERVER_1_PORT), 0, 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> healthCheckAndLog(SERVER_2_ADDRESS, SERVER_2_PORT), 0, 3, TimeUnit.SECONDS);
        
    }

    private static void startMonitorServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(MONITOR_PORT)) {
                

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    

                    // Delega la tarea de manejar la conexión del cliente a un nuevo hilo
                    new Thread(() -> handleMonitorClient(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleMonitorClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // Lee el mensaje del cliente
            String message = reader.readLine();

            if ("HEALTH_CHECK_SERVER_1".equals(message)) {
                healthCheckAndLog(SERVER_1_ADDRESS, SERVER_1_PORT);
            } else if ("HEALTH_CHECK_SERVER_2".equals(message)) {
                healthCheckAndLog(SERVER_2_ADDRESS, SERVER_2_PORT);
            } else {
                writer.println("Comando de monitoreo no válido.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Cliente de monitoreo desconectado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void healthCheckAndLog(String serverAddress, int serverPort) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter logWriter = new PrintWriter(bw)) {

            // Marca de tiempo para el registro
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            // Realiza el health check
            try (Socket socket = new Socket(serverAddress, serverPort);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                // Envía un mensaje de health check al servidor
                out.println("Health Check");

                // Recibe la respuesta del servidor
                String response = in.readLine();
                String logMessage = timestamp  + serverAddress + ":" + serverPort + ": " + response;
                

                // Guarda la información en el archivo de log
                logWriter.println(logMessage);

            } catch (IOException e) {
                // El servidor no está disponible
                String logMessage = timestamp + " - El servidor " + serverAddress + ":" + serverPort + " no está disponible.";
               

                // Guarda la información en el archivo de log
                logWriter.println(logMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
