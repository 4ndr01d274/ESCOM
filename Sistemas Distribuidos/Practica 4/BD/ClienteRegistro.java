//LOPEZ PEREZ ALBERTO ANDREI    PRACTICA 4      7CV2 
import java.io.*;
import java.net.*;

public class ClienteRegistro {

    public static void main(String[] args) {
        String servidorDireccion = "localhost"; // Cambia esto a la dirección del servidor si es necesario
        int servidorPuerto = 12345;

        try (Socket socket = new Socket(servidorDireccion, servidorPuerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Cliente conectado al servidor en " + servidorDireccion + ":" + servidorPuerto);

            while (true) {
                System.out.println("Elija una opción: (REGISTRO / LOGIN / SALIR)");
                String opcion = entradaUsuario.readLine();

                if ("SALIR".equals(opcion)) {
                    break;
                }

                if ("REGISTRO".equals(opcion) || "LOGIN".equals(opcion)) {
                    salida.println(opcion);
                } else {
                    System.out.println("Opción no válida.");
                    continue;
                }

                System.out.println("Ingrese el nombre de usuario:");
                String usuario = entradaUsuario.readLine();
                System.out.println("Ingrese la contraseña:");
                String contrasena = entradaUsuario.readLine();

                salida.println(usuario);
                salida.println(contrasena);

                // Después de recibir "Inicio de sesión exitoso." desde el servidor
                String respuesta = entrada.readLine();
                System.out.println(respuesta);

                // Recibir y mostrar la lista de archivos
                String linea;
                while ((linea = entrada.readLine()) != null) {
                    if (linea.equals("Fin de la lista")) {
                        break;
                    }
                    System.out.println(linea);
                }

                // Solicitar información de la película
                System.out.println("Ingrese el nombre de la película:");
                String nombreArchivo = entradaUsuario.readLine();
                System.out.println("Ingrese el número de subtítulo:");
                String numeroInicioStr = entradaUsuario.readLine();
                int numeroInicio = Integer.parseInt(numeroInicioStr);

                // Enviar la información al servidor
                salida.println(nombreArchivo);
                salida.println(numeroInicio);

            String lineas;
            while ((lineas = entrada.readLine()) != null) {
                System.out.println(lineas);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
