//  PROYECTO 3  LOPEZ PEREZ ALBERTO ANDREI  7CV2
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EstadisticasCURP extends JFrame {
    private BarChartPanel chartPanel;

    public EstadisticasCURP() {
        setTitle("Estadísticas de CURPs");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chartPanel = new BarChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        // Iniciar una tarea programada para actualizar las estadísticas cada 3 segundos.
        Timer timer = new Timer(3000, e -> actualizarEstadisticas());
        timer.start();
    }

    private void actualizarEstadisticas() {
        try {
            // Leer el archivo "Registros.txt" y contar las CURPs y su nivel de estudio.
            int totalCURPs = 0;
            int nivelPreescolar = 0;
            int nivelPrimaria = 0;
            int nivelSecundaria = 0;
            int nivelPreparatoria = 0;
            int nivelUniversidad = 0;
            int nivelMaestria = 0;
            int nivelDoctorado = 0;

            BufferedReader reader = new BufferedReader(new FileReader("Registros.txt"));
            String linea;

            while ((linea = reader.readLine()) != null) {
                totalCURPs++;

                String[] partes = linea.split(" ");
                if (partes.length >= 3) {
                    String nivelEstudio = partes[2];

                    switch (nivelEstudio) {
                        case "PREESCOLAR":
                            nivelPreescolar++;
                            break;
                        case "PRIMARIA":
                            nivelPrimaria++;
                            break;
                        case "SECUNDARIA":
                            nivelSecundaria++;
                            break;
                        case "PREPARATORIA":
                            nivelPreparatoria++;
                            break;
                        case "UNIVERSIDAD":
                            nivelUniversidad++;
                            break;
                        case "MAESTRÍA":
                            nivelMaestria++;
                            break;
                        case "DOCTORADO":
                            nivelDoctorado++;
                            break;
                    }
                }
            }

            // Actualizar los datos del gráfico de barras con colores y número total de CURPs.
            chartPanel.updateData(totalCURPs, nivelPreescolar, nivelPrimaria, nivelSecundaria, nivelPreparatoria, nivelUniversidad, nivelMaestria, nivelDoctorado);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstadisticasCURP app = new EstadisticasCURP();
            app.setVisible(true);
        });
    }
}

class BarChartPanel extends JPanel {
    private int totalCURPs;
    private int nivelPreescolar;
    private int nivelPrimaria;
    private int nivelSecundaria;
    private int nivelPreparatoria;
    private int nivelUniversidad;
    private int nivelMaestria;
    private int nivelDoctorado;

    public BarChartPanel() {
        // Inicializa los datos del gráfico de barras
        updateData(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void updateData(int totalCURPs, int nivelPreescolar, int nivelPrimaria, int nivelSecundaria, int nivelPreparatoria,
                          int nivelUniversidad, int nivelMaestria, int nivelDoctorado) {
        this.totalCURPs = totalCURPs;
        this.nivelPreescolar = nivelPreescolar;
        this.nivelPrimaria = nivelPrimaria;
        this.nivelSecundaria = nivelSecundaria;
        this.nivelPreparatoria = nivelPreparatoria;
        this.nivelUniversidad = nivelUniversidad;
        this.nivelMaestria = nivelMaestria;
        this.nivelDoctorado = nivelDoctorado;

        repaint(); // Vuelve a dibujar el gráfico
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = 80;
        int spacing = 100;
        int startX = 60;
        int chartHeight = getHeight() - 100;
        int max = Math.max(totalCURPs, nivelDoctorado); // Ajusta la escala para el gráfico

        // Dibuja barras para cada nivel de estudio con colores y números
        drawBar(g, startX, chartHeight, barWidth, (nivelPreescolar * chartHeight) / max, "Preescolar", Color.BLUE, nivelPreescolar);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelPrimaria * chartHeight) / max, "Primaria", Color.YELLOW, nivelPrimaria);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelSecundaria * chartHeight) / max, "Secundaria", Color.RED, nivelSecundaria);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelPreparatoria * chartHeight) / max, "Preparatoria", Color.GREEN, nivelPreparatoria);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelUniversidad * chartHeight) / max, "Universidad", Color.DARK_GRAY, nivelUniversidad);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelMaestria * chartHeight) / max, "Maestría", Color.ORANGE, nivelMaestria);
        startX += spacing;
        drawBar(g, startX, chartHeight, barWidth, (nivelDoctorado * chartHeight) / max, "Doctorado", Color.GRAY, nivelDoctorado);

        g.setColor(Color.BLACK);
        g.drawLine(30, chartHeight + 20, getWidth() - 20, chartHeight + 20);

        // Etiqueta de eje Y con el número total de CURPs
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Número de CURPs: " + totalCURPs, 10, 40);
    }

    private void drawBar(Graphics g, int x, int chartHeight, int width, int height, String label, Color color, int number) {
        g.setColor(color);
        g.fillRect(x, chartHeight - height, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, chartHeight - height, width, height);
        g.drawString(label, x + 10, chartHeight + 40);
        g.drawString(String.valueOf(number), x + 20, chartHeight - height - 5);
    }
}
