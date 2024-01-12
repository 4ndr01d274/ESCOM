//PruebaPoligono.java
import java.util.Random;
public class PruebaPoligono{   
public static void main(String[] args) {
        int numVertices = 10; // Número máximo de vértices del polígono
        PoligonoIrreg poligono = new PoligonoIrreg(numVertices);
        Random rand = new Random();
        System.out.println("Generando vértices aleatorios:");
        for (int i = 0; i < numVertices; i++) {
            double x = rand.nextDouble() * 200 - 100; // Coordenada x aleatoria en el rango [-10, 10]
            double y = rand.nextDouble() * 200 - 100; // Coordenada y aleatoria en el rango [-10, 10]
            Coordenada vertice = new Coordenada(x, y);
            poligono.anadeVertice(vertice);
        }
        System.out.println("\n" + poligono.toString());
        poligono.ordenaVertices();
        System.out.println("\n" + poligono.toString());
    }
}