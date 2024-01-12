import java.util.Arrays;
import java.util.List;

public class Application {
    private static final String WORKER_ADDRESS_1 = "http://localhost:8080/searchtoken"; //Cadenas de los endpoints para los 2 servidores
    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator(); //Instancia de Agregator
        String task1 = "1757600,IPN";
        String task2 = "1757600,ABC";
        String task3 = "1757600,CMD";
        String task4 = "1757600,UPN";

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(task1, task2, task3, task4)); //Arreglos para lista de trabajadores y lista de tareas 

        for (String result : results) { //Se reciben e imprimen resultdos
            System.out.println(result);
        }
    }
}
