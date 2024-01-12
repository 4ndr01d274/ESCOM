import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsteroidesApp extends JFrame {

    private List<Asteroide> asteroides;
    private JPanel asteroidesPanel;

    public AsteroidesApp(int numAsteroides) {
        // Configuración básica del JFrame
        setTitle("Asteroides App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar la lista de asteroides
        asteroides = new ArrayList<>();

        // Generar asteroides aleatorios
        Random random = new Random();
        for (int i = 0; i < numAsteroides; i++) {
            int numVertices = random.nextInt(20) + 5; // Número de lados entre 5 y 12
            Asteroide asteroide = new Asteroide(numVertices);
            asteroides.add(asteroide);
        }

        // Crear un JPanel personalizado para dibujar los asteroides
        asteroidesPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarAsteroides(g);
            }
        };

        // Agregar el JPanel al JFrame
        add(asteroidesPanel);

        // Hacer visible el JFrame
        setVisible(true);

        // Programar la tarea de redibujar después de 3 segundos
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(this::redibujarAsteroides, 3, TimeUnit.SECONDS);
    }

    private void dibujarAsteroides(Graphics g) {
        Random random = new Random();
        for (Asteroide asteroide : asteroides) {
            // Posiciones aleatorias en la pantalla
            int x = random.nextInt(getWidth() - 50);
            int y = random.nextInt(getHeight() - 50);

            // Dibujar el asteroide en la posición aleatoria
            dibujarAsteroide(g, x, y, asteroide);
        }
    }

    private void dibujarAsteroide(Graphics g, int x, int y, Asteroide asteroide) {
        List<Coordenada> vertices = asteroide.getVertices();

        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xPoints[i] = (int) (x + vertices.get(i).abcisa() * 100);
            yPoints[i] = (int) (y + vertices.get(i).ordenada() * 100);
        }

        g.drawPolygon(xPoints, yPoints, vertices.size());
    }

    private void redibujarAsteroides() {
        // Borrar la pantalla
        Graphics g = asteroidesPanel.getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // Ordenar por perímetro
        Collections.sort(asteroides);

        // Redibujar los asteroides ordenados
        for (Asteroide asteroide : asteroides) {
            // Posición centrada en la pantalla
            int x = (getWidth() - 50) / 2;
            int y = (getHeight() - 50) / 2;

            // Dibujar el asteroide en la posición centrada
            dibujarAsteroide(g, x, y, asteroide);

            // Esperar medio segundo entre cada asteroide
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Obtener el número de asteroides desde la línea de comandos
        final int numAsteroides;
        if (args.length > 0) {
            try {
                numAsteroides = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido de asteroides.");
                System.exit(1);
                return;
            }
        } else {
            numAsteroides = 5; // Valor predeterminado
        }

        // Crear la aplicación con el número de asteroides especificado
        final int finalNumAsteroides = numAsteroides;
        SwingUtilities.invokeLater(() -> new AsteroidesApp(finalNumAsteroides));
    }
}
