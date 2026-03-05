package org.example.DogsServer; // Define el paquete raíz del servidor

import com.sun.net.httpserver.HttpServer;
import org.example.DogsServer.router.RouterHandler;

import java.net.InetSocketAddress;

public class Main { // Clase principal que arranca la aplicación

  public static void main(String[] args) throws Exception { // Punto de entrada del programa
    // Crea una instancia del servidor escuchando en el puerto 8080. El segundo parámetro (0) usa el backlog por defecto.
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

    // Registra un contexto para la raíz "/" delegando todas las peticiones a RouterHandler
    server.createContext("/", new RouterHandler());

    // Configura el ejecutor como null para que el servidor use su implementación por defecto (monohilo en este caso simple)
    server.setExecutor(null);

    // Inicia la ejecución del servidor HTTP
    server.start();

    // Imprime mensajes informativos en la consola para saber que el servidor está activo
    System.out.println("Servidor iniciado en http://localhost:8080");
    System.out.println("Endpoints disponibles:");
    System.out.println("Lista de Perros (razas y sub razas):  http://localhost:8080/dogs/list");
    System.out.println("Lista de Perros que no tienen sub razas:  http://localhost:8080/dogs/list/razasquenotienensubrazas");
    System.out.println("Lista de Perros que sí tienen sub razas:  http://localhost:8080/dogs/list/razasquesitienensubrazas");
    System.out.println("Imágenes Random de Perros:  http://localhost:8080/dogs/list/image/random");
  }
}