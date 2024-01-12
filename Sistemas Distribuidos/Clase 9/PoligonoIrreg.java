//PoligonoIrreg.java
import java.util.*;
public class PoligonoIrreg {
    //private Coordenada[] vertices;
    private int numVertices;
    private int maxVertices;
    List<Coordenada> vertices = new ArrayList<>();
    public PoligonoIrreg(int maxVertices) {
        this.maxVertices = maxVertices;
       // this.vertices = new Coordenada[maxVertices];
       
        this.numVertices = 0;
    }
    public void anadeVertice(Coordenada coordenada) {
        if (numVertices < maxVertices) {
            vertices.add(coordenada);
            numVertices++;
        } else {
            System.out.println("Excedido del numero de vertices");
        }
    }
      public void ordenaVertices() {
        Comparator<Coordenada> comparador = new Comparator<Coordenada>() {
            @Override
            public int compare(Coordenada c1, Coordenada c2) {
                double magnitudC1 = Math.sqrt(c1.abcisa() * c1.abcisa() + c1.ordenada() * c1.ordenada());
                double magnitudC2 = Math.sqrt(c2.abcisa() * c2.abcisa() + c2.ordenada() * c2.ordenada());
                return Double.compare(magnitudC1, magnitudC2);
            }
        };
        Collections.sort(vertices, comparador);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vertices:\n");
        for (Coordenada coorde : vertices) {
            double magnitud;
            magnitud = Math.sqrt(coorde.abcisa() * coorde.abcisa() + coorde.ordenada() * coorde.ordenada()); 
            sb.append(coorde).append(" Magnitud: ").append(magnitud).append("\n");
        }
        return sb.toString();
    }
}