import java.util.ArrayList;
import java.util.List;

public class PoligonoIrreg {

    private List<Coordenada> vertices;

    public PoligonoIrreg() {
        this.vertices = new ArrayList<>();
    }

    public void agregarVertice(Coordenada vertice) {
        vertices.add(vertice);
    }

    public List<Coordenada> getVertices() {
        return vertices;
    }

    public double calcularPerimetro() {
        double perimetro = 0;

        for (int i = 0; i < vertices.size() - 1; i++) {
            Coordenada puntoActual = vertices.get(i);
            Coordenada puntoSiguiente = vertices.get(i + 1);

            double distancia = Math.sqrt(Math.pow(puntoSiguiente.abcisa() - puntoActual.abcisa(), 2) +
                                         Math.pow(puntoSiguiente.ordenada() - puntoActual.ordenada(), 2));

            perimetro += distancia;
        }

        Coordenada primerPunto = vertices.get(0);
        Coordenada ultimoPunto = vertices.get(vertices.size() - 1);

        double distanciaFinal = Math.sqrt(Math.pow(ultimoPunto.abcisa() - primerPunto.abcisa(), 2) +
                                          Math.pow(ultimoPunto.ordenada() - primerPunto.ordenada(), 2));

        perimetro += distanciaFinal;

        return perimetro;
    }

    @Override
    public String toString() {
        return "PoligonoIrreg{vertices=" + vertices + '}';
    }
}
