package networking;

import java.util.Arrays;
import java.util.List;

public class TestClient {
    private static final String WORKER_ADDRESS_1 = "http://localhost:8080/task";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        
        // Crear objeto Demo
        Demo demoObject = new Demo(2022, "Prueba serializacion y deserializacion");

        // Convertir objeto a bytes
        byte[] serializedObject = SerializationUtils.serialize(demoObject);

        // Imprimir contenido del objeto antes de enviar
        System.out.println("Contenido del objeto antes de enviar: ");
        System.out.println("a = " + demoObject.a);
        System.out.println("b = " + demoObject.b);

        // Enviar objeto serializado al servidor
        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1), Arrays.asList(serializedObject));

        // Imprimir resultados recibidos del servidor (puede haber mensajes de depuración del servidor)
        for (String result : results) {
            System.out.println(result);
        }
    }
}
