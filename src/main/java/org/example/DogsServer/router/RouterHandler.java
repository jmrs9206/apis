package org.example.DogsServer.router; // Define el paquete que maneja el enrutamiento

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DogsServer.controllers.DogsController;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class RouterHandler implements HttpHandler { // Implementa HttpHandler para ser un manejador válido de HttpServer

  // Crea una única instancia del controlador para reutilizarla en todas las peticiones
  private final DogsController dogsController = new DogsController();

  @Override // Sobrescribe el método handle definido en la interfaz HttpHandler
  public void handle(HttpExchange exchange) throws IOException { // Se ejecuta cada vez que llega una petición al servidor

    // Obtiene la ruta de la petición (ej.: /dogs/list) desde el objeto de intercambio
    String path = exchange.getRequestURI().getPath();

    // Comprueba si la ruta solicitada empieza por el prefijo "/dogs"
    if (path.startsWith("/dogs")) {
      // Si empieza por /dogs, delega el control al DogsController pasándole el objeto exchange
      dogsController.handle(exchange);
      // Sale del método una vez gestionada la petición por el controlador
      return;
    }

    // Sirve el archivo index.html para la raíz "/"
    if (path.equals("/")) {
      path = "/src/main/java/org/example/DogsServer/views/index.html";
    } else if (path.startsWith("/views/")) {
        // Sirve los archivos estáticos desde la carpeta views
        path = "/src/main/java/org/example/DogsServer" + path;
    }

    // Intenta servir el archivo solicitado
    File file = new File(System.getProperty("user.dir") + path);
    if (file.exists() && !file.isDirectory()) {
        String contentType = "text/plain";
        if (path.endsWith(".html")) contentType = "text/html";
        else if (path.endsWith(".css")) contentType = "text/css";
        else if (path.endsWith(".js")) contentType = "text/javascript";

        byte[] content = Files.readAllBytes(file.toPath());
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, content.length);
        OutputStream os = exchange.getResponseBody();
        os.write(content);
        os.close();
        return;
    }

    // Si la ruta no coincide con nada, se prepara una respuesta de error 404
    String response = "404 - Ruta no encontrada";
    // Envía el código de estado 404 (No encontrado) y la longitud de la cadena de respuesta
    exchange.sendResponseHeaders(404, response.length());
    
    // Obtiene el flujo de salida de la respuesta HTTP
    OutputStream os = exchange.getResponseBody();
    // Escribe los bytes del mensaje "404 - Ruta no encontrada" en la respuesta
    os.write(response.getBytes());
    // Cierra el stream para finalizar el envío de la respuesta al cliente
    os.close();
  }
}
