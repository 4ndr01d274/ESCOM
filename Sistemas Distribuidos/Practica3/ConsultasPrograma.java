//  PROYECTO 3  LOPEZ PEREZ ALBERTO ANDREI  7CV2
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ConsultasPrograma {
    public static void main(String[] args) {
        List<String> listaDeRegistros = cargarRegistrosDesdeArchivo("Registros.txt");
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea.

            switch (opcion) {
                case 1:
                    consultarRegistrosPorSexo(listaDeRegistros);
                    break;
                case 2:
                    consultarRegistrosPorEntidadFederativa(listaDeRegistros);
                    break;
                case 3:
                    consultarRegistrosPorNivelDeEstudiosYSexo(listaDeRegistros);
                    break;
                case 4:
                    calcularEdadPromedioPorNivelDeEstudios(listaDeRegistros);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("Menú de Consultas");
        System.out.println("1. ¿Cuántos mensajes SMS totales se han realizado porcada sexo hasta el momento?");
        System.out.println("2. ¿Cuántos mensajes SMS se han realizado por cada entidad federativa?");
        System.out.println("3. ¿Cuántos ciudadanos tienen un cierto nivel de estudios concluidos, así como su sexo?");
        System.out.println("4. ¿Cuál es la edad promedio de los ciudadanos que han concluido un cierto nivel de estudios?");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static List<String> cargarRegistrosDesdeArchivo(String nombreDeArchivo) {
        List<String> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreDeArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                registros.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }

    private static void consultarRegistrosPorSexo(List<String> registros) {
        int totalHombres = 0;
        int totalMujeres = 0;
        for (String registro : registros) {
            char sexo = registro.charAt(10); // Posición 11 (índice 10 en Java).

            if (sexo == 'H') {
                totalHombres++;
            } else if (sexo == 'M') {
                totalMujeres++;
            }
        }
        System.out.println("Total de mensajes SMS de hombres: " + totalHombres);
        System.out.println("Total de mensajes SMS de mujeres: " + totalMujeres);
    }

    private static void consultarRegistrosPorEntidadFederativa(List<String> registros) {
        Map<String, Integer> entidadCount = new HashMap<>();

        for (String registro : registros) {
            if (registro.length() >= 13) {
                String entidad = registro.substring(11, 13); // Obtener la entidad federativa
                entidadCount.put(entidad, entidadCount.getOrDefault(entidad, 0) + 1);
            }
        }

        System.out.println("mensajes SMS por entidad federativa:");
        for (Map.Entry<String, Integer> entry : entidadCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " mensajes SMS");
        }
    }

    private static void consultarRegistrosPorNivelDeEstudiosYSexo(List<String> registros) {
        int totalHombres = 0;
        int totalMujeres = 0;
        
        Map<String, Integer> nivelEstudioHombres = new HashMap<>();
        Map<String, Integer> nivelEstudioMujeres = new HashMap<>();
    
        for (String registro : registros) {
            // Divide el registro en sus componentes.
            String[] partes = registro.split(" ");
    
            if (partes.length >= 3) {
                String sexo = partes[0].charAt(10) == 'H' ? "Hombre" : "Mujer";
                String nivelEstudio = partes[2];
    
                if (sexo.equals("Hombre")) {
                    totalHombres++;
    
                    nivelEstudioHombres.put(nivelEstudio, nivelEstudioHombres.getOrDefault(nivelEstudio, 0) + 1);
                } else if (sexo.equals("Mujer")) {
                    totalMujeres++;
    
                    nivelEstudioMujeres.put(nivelEstudio, nivelEstudioMujeres.getOrDefault(nivelEstudio, 0) + 1);
                }
            }
        }
    
        System.out.println("Mensajes SMS por nivel de estudios y sexo:");
        System.out.println("Nivel de Estudios\tHombres\t\tMujeres");
    
        String[] nivelesDeEstudio = {"PREESCOLAR", "PRIMARIA", "SECUNDARIA", "PREPARATORIA", "UNIVERSIDAD", "MAESTRÍA", "DOCTORADO"};
    
        for (String nivel : nivelesDeEstudio) {
            int hombres = nivelEstudioHombres.getOrDefault(nivel, 0);
            int mujeres = nivelEstudioMujeres.getOrDefault(nivel, 0);
            System.out.println(nivel + "\t\t" + hombres + "\t\t" + mujeres);
        }
    
        System.out.println("Total Hombres: " + totalHombres);
        System.out.println("Total Mujeres: " + totalMujeres);
    }

    private static void calcularEdadPromedioPorNivelDeEstudios(List<String> registros) {
        Map<String, Integer> nivelEstudioEdades = new HashMap<>();
        Map<String, Integer> nivelEstudioContadores = new HashMap<>();
    
        for (String registro : registros) {
            // Divide el registro en sus componentes.
            String[] partes = registro.split(" ");
    
            if (partes.length >= 3) {
                String nivelEstudio = partes[2];
                String curp = partes[0];
                int anoNacimiento = Integer.parseInt(curp.substring(4, 6)); // Obtén los caracteres 5 y 6 de la CURP como años.
    
                int edad = 2023 - (1900 + anoNacimiento); // Supongo que los años son de 1900.
    
                if (edad >= 0) {
                    nivelEstudioEdades.put(nivelEstudio, nivelEstudioEdades.getOrDefault(nivelEstudio, 0) + edad);
                    nivelEstudioContadores.put(nivelEstudio, nivelEstudioContadores.getOrDefault(nivelEstudio, 0) + 1);
                }
            }
        }
    
        System.out.println("Promedio de edades por nivel de estudios:");
        System.out.println("Nivel de Estudios\tEdad Promedio");
    
        for (Map.Entry<String, Integer> entry : nivelEstudioEdades.entrySet()) {
            String nivel = entry.getKey();
            int sumaEdades = entry.getValue();
            int contador = nivelEstudioContadores.get(nivel);
    
            double promedio = (double) sumaEdades / contador;
            System.out.printf("%s\t\t    %.2f\n", nivel, promedio);
        }
    }
}
