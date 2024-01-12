
package networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WebClient {
    private HttpClient client; //Objeto unico HttpClient

    public WebClient() { //crea un objeto httpclient con http version 1.1 
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
    //Recibe la direccion, los datos a enviar al servidor y devuelve el CompletableFuture
    public CompletableFuture<String> sendTask(String url, Demo demoObject) {
        // Serializa el objeto Demo a un array de bytes
        byte[] serializedObject = SerializationUtils.serialize(demoObject);

        // Imprime el contenido del objeto antes de ser enviado
        System.out.println("Objeto enviado desde el cliente:");
        System.out.println("a = " + demoObject.a);
        System.out.println("b = " + demoObject.b);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(serializedObject))
                .uri(URI.create(url + "/task"))
                .header("X-Debug", "true")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    // Imprime la respuesta recibida del servidor (si es necesario)
                    System.out.println("Respuesta del servidor: " + response.body());
                    return response.headers().toString() + response.body();
                });
    }
}
