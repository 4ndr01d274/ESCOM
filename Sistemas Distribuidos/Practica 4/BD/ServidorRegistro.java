//LOPEZ PEREZ ALBERTO ANDREI    PRACTICA 4      7CV2 
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorRegistro {

    private static final int PUERTO = 12345;
    private static Map<String, String> usuariosRegistrados = new HashMap<>();
    private static String otroServidorDireccion = "localhost";
    private static int otroServidorPuerto = 54321;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // Puedes ajustar el número de hilos según tus necesidades

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor en línea en el puerto " + PUERTO);

            cargarUsuarios();

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                   

                    // Delegar la tarea de manejar la conexión a un hilo del pool de hilos
                    executorService.execute(() -> manejarConexion(socket));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void manejarConexion(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            String comando = entrada.readLine();

            if ("REGISTRO".equals(comando)) {
                registrarUsuario(entrada, salida);
            } else if ("LOGIN".equals(comando)) {
                iniciarSesion(entrada, salida);
            } else if ("SALIR".equals(comando)) {
                socket.close();
            } else if ("Health Check".equals(comando)) {
                salida.println("El servidor esta Vivo");
            } else {
                salida.println("Comando no válido.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("USUARIOS.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    usuariosRegistrados.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registrarUsuario(BufferedReader entrada, PrintWriter salida) throws IOException {
        String usuario = entrada.readLine();
        String contrasena = entrada.readLine();

        if (usuariosRegistrados.containsKey(usuario)) {
            salida.println("ERROR: El usuario ya existe.");
        } else {
            usuariosRegistrados.put(usuario, contrasena);

            try (FileWriter fw = new FileWriter("USUARIOS.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter archivoSalida = new PrintWriter(bw)) {
                archivoSalida.println(usuario + ":" + contrasena);
            } catch (IOException e) {
                e.printStackTrace();
            }

            salida.println("Registro exitoso. ¡Bienvenido, " + usuario + "!");
        }
    }

    private static void iniciarSesion(BufferedReader entrada, PrintWriter salida) throws IOException {
        String usuario = entrada.readLine();
        String contrasena = entrada.readLine();

        if (usuariosRegistrados.containsKey(usuario) && usuariosRegistrados.get(usuario).equals(contrasena)) {
            salida.println("Inicio de sesión exitoso.");

            // Obtener la lista de archivos en la carpeta "PELICULAS/"
            File carpetaPeliculas = new File("PELICULAS/");
            File[] archivos = carpetaPeliculas.listFiles();

            // Enviar la lista de archivos al cliente
            salida.println("Archivos en la carpeta PELICULAS:");
            if (archivos != null) {
                for (File archivo : archivos) {
                    salida.println(archivo.getName());
                }
            } else {
                salida.println("La carpeta PELICULAS está vacía o no existe.");
            }

            // Indicar el final de la lista
            salida.println("Fin de la lista");

            // Recibir el nombre de la película y el número de subtítulo del cliente
            String nombreArchivo = entrada.readLine();
            int numeroInicio = Integer.parseInt(entrada.readLine());

            // Enviar estos datos a otro servidor
            enviarDatosAOtroServidor(nombreArchivo, numeroInicio, otroServidorDireccion, otroServidorPuerto, salida);

            // Esperar la nueva opción del cliente
            String nuevaOpcion = entrada.readLine();

            // Puedes realizar acciones adicionales según la nueva opción del cliente
            if ("REGISTRO".equals(nuevaOpcion)) {
                // Lógica para registro
            } else if ("LOGIN".equals(nuevaOpcion)) {
                // Lógica para inicio de sesión
            } else if ("SALIR".equals(nuevaOpcion)) {
                // Lógica para salir
            } else {
                salida.println("Opción no válida.");
            }
        } else {
            salida.println("ERROR: Usuario o contraseña incorrectos.");
        }
    }

    private static void enviarDatosAOtroServidor(String nombrePelicula, int numeroSubtitulo, String direccion, int puerto, PrintWriter clienteSalida) {
        try (Socket socket = new Socket(direccion, puerto);
             PrintWriter servidorSalida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader servidorEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Establece una conexión con el otro servidor utilizando la dirección y el puerto especificados.

            // Envía el nombre de la película al otro servidor.
            servidorSalida.println(nombrePelicula);

            // Envía el número de subtítulo al otro servidor.
            servidorSalida.println(numeroSubtitulo);

            // Recibe las respuestas del otro servidor y envíalas de vuelta al cliente.
            String respuesta;
            while ((respuesta = servidorEntrada.readLine()) != null) {
                // Envía la respuesta al cliente.
                clienteSalida.println(respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
