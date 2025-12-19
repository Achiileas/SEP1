package SEP1.main;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonServer {

  public static void main(String[] args) throws IOException {

    int port = 8080;
    Path jsonPath = Path.of("cloverville.json");

    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

    server.createContext("/cloverville.json", exchange -> {

      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      byte[] data;
      if (Files.exists(jsonPath)) {
        data = Files.readAllBytes(jsonPath);
      } else {
        data = "{}".getBytes();
      }

      exchange.sendResponseHeaders(200, data.length);

      try (OutputStream os = exchange.getResponseBody()) {
        os.write(data);
      }
    });

    server.start();

    System.out.println(
        "JSON server running at http://localhost:" + port + "/cloverville.json\n" +
            "Serving file: " + jsonPath.toAbsolutePath()
    );
  }
}

