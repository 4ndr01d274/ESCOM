
import networking.WebClient;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient(); //Se instancía un objeto de tipo WebClient
    }
    //unico metodo de la clase aggregator
    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {//Recibe las 2 listas
        CompletableFuture<String>[] futures = new CompletableFuture[4]; //Permite continuar la ejecución de codigo bloqueante

        for (int i = 0; i < 4; i++) { //se iteran las listas 
            String workerAddress = workersAddresses.get(0); //Se obtienen las direcciones y las cadenas de tareas
            String task = tasks.get(i);

            byte[] requestPayload = task.getBytes(); //Se almacena la tarea en formato de bytes
            futures[i] = webClient.sendTask(workerAddress, requestPayload); //Se envian las tareas asincronas 
        }

        List<String> results = new ArrayList(); //Se obtienen los resultados por medio de futures
        for (int i = 0; i < 4; i++) {
            results.add(futures[i].join());
        }

        return results;
    }
}
