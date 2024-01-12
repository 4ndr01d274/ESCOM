import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class MineriaTexto {
    private static final String DOCUMENTS_FOLDER = "/LIBROS_TXT"; // Cambia esto a tu carpeta de documentos

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8081; // Usa el argumento del puerto si está disponible
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor de minería iniciado en el puerto " + port);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                String body = readRequestBody(in);
                String[] words = getWordsFromBody(body);
                System.out.println("Palabras recibidas: " + Arrays.toString(words));

                Map<String, Map<String, Integer>> results = processDocuments(words);
                sendHttpResponse(out, results);
            }
        }
    }

    private static String readRequestBody(BufferedReader in) throws IOException {
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        int contentLength = -1;
        while (!(line = in.readLine()).isBlank()) {
            requestBuilder.append(line).append("\r\n");
            if (line.startsWith("Content-Length: ")) {
                contentLength = Integer.parseInt(line.substring("Content-Length: ".length()).trim());
            }
        }
        
        if (contentLength <= 0) {
            return "";
        }
        
        char[] bodyChars = new char[contentLength];
        in.read(bodyChars, 0, contentLength);
        return new String(bodyChars);
    }

    private static String[] getWordsFromBody(String body) {
        if (body.length() > 0) {
            return body.split("words=")[1].split("\\+");
        }
        return new String[0];
    }

    private static Map<String, Map<String, Integer>> processDocuments(String[] words) {
        Map<String, Map<String, Integer>> wordCountsPerFile = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DOCUMENTS_FOLDER))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && path.toString().endsWith(".txt")) {
                    String fileName = path.getFileName().toString();
                    Map<String, Integer> countsInFile = countWordsInFile(words, path);
                    wordCountsPerFile.put(fileName, countsInFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCountsPerFile;
    }

    private static Map<String, Integer> countWordsInFile(String[] words, Path filePath) throws IOException {
        Map<String, Integer> counts = new HashMap<>();
        String content = new String(Files.readAllBytes(filePath));
        for (String word : words) {
            counts.put(word, countWordInContent(word, content));
        }
        return counts;
    }

    private static int countWordInContent(String word, String content) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static void sendHttpResponse(BufferedWriter out, Map<String, Map<String, Integer>> results) throws IOException {
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/plain; charset=UTF-8\r\n");
        out.write("\r\n");

        for (String file : results.keySet()) {
            for (Map.Entry<String, Integer> entry : results.get(file).entrySet()) {
                out.write(file + ":" + entry.getKey() + ":" + entry.getValue() + "\r\n");
            }
        }
        out.write("END\r\n");
        out.flush();
    }
}