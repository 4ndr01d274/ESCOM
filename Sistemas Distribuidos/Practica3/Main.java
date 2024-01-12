//  PROYECTO 3  LOPEZ PEREZ ALBERTO ANDREI  7CV2
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        // Obtener la cantidad de registros a generar por segundo desde los argumentos de línea de comandos.
        int numPerSecond = Integer.parseInt(args[0]);

        // Crear un temporizador para generar registros cada segundo.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new GenerarRegistrosTarea(numPerSecond), 0, 1000);
    }

    static String obtenerCURP() {
        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Numero = "0123456789";
        String Sexo = "HM";
        String Entidad[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};
        int indice;
        StringBuilder sb = new StringBuilder(18);
        for (int i = 1; i < 5; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }
        for (int i = 5; i < 11; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }
        indice = (int) (Sexo.length() * Math.random());
        sb.append(Sexo.charAt(indice));
        sb.append(Entidad[(int)(Math.random() * 32)]);
        for (int i = 14; i < 17; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }
        for (int i = 17; i < 19; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }
        return sb.toString();
    }
    
    static String generarNumeroDeTelefono() {
        // Generar un número de teléfono aleatorio de 10 dígitos.
        StringBuilder numeroDeTelefono = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            numeroDeTelefono.append((int) (Math.random() * 10));
        }
        return numeroDeTelefono.toString();
    }

    static String generarNivelDeEstudio() {
        // Generar un nivel de estudio aleatorio a partir de una lista de opciones.
        String[] nivelesDeEstudio = {"PREESCOLAR", "PRIMARIA", "SECUNDARIA", "PREPARATORIA", "UNIVERSIDAD", "MAESTRÍA", "DOCTORADO"};
        int indiceAleatorio = (int) (Math.random() * nivelesDeEstudio.length);
        return nivelesDeEstudio[indiceAleatorio];
    }

    static class GenerarRegistrosTarea extends TimerTask {
        private final int numPorSegundo;
        private final ArrayList<String> listaDeRegistros;

        public GenerarRegistrosTarea(int numPorSegundo) {
            this.numPorSegundo = numPorSegundo;
            this.listaDeRegistros = new ArrayList<>();
        }

        @Override
        public void run() {
            listaDeRegistros.clear();

            // Generar registros y agregarlos a la lista.
            for (int i = 0; i < numPorSegundo; i++) {
                String curp = obtenerCURP();
                String numeroDeTelefono = generarNumeroDeTelefono();
                String nivelDeEstudio = generarNivelDeEstudio();

                // Concatenar las tres cadenas y agregarlas como un registro.
                String registro = curp + " " + numeroDeTelefono + " " + nivelDeEstudio;
                listaDeRegistros.add(registro);
            }

            // Escribir los registros en el archivo "Registros.txt".
            escribirRegistrosEnArchivo("Registros.txt", listaDeRegistros);
        }
    }

    static void escribirRegistrosEnArchivo(String nombreDeArchivo, ArrayList<String> registros) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreDeArchivo, true))) {
            // Escribir cada registro en una línea del archivo.
            for (String registro : registros) {
                escritor.write(registro);
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}