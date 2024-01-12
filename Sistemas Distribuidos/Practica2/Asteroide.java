import java.util.Random;

public class Asteroide extends PoligonoIrreg implements Comparable<Asteroide> {

    public Asteroide(int numVertices) {
        generarCoordenadasAleatorias(numVertices);
    }

    private void generarCoordenadasAleatorias(int numVertices) {
        Random random = new Random();
        for (int i = 0; i < numVertices; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            agregarVertice(new Coordenada(x, y));
        }
    }

    @Override
    public int compareTo(Asteroide otroAsteroide) {
        double perimetroThis = this.calcularPerimetro();
        double perimetroOtro = otroAsteroide.calcularPerimetro();

        return Double.compare(perimetroThis, perimetroOtro);
    }
}
