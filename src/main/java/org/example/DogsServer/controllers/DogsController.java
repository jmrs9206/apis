package org.example.DogsServer.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import org.example.DogsServer.service.DogsService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DogsController {

  private final HttpClient client = HttpClient.newHttpClient();
  private final Gson gson = new Gson();
  private final DogsService dogService = new DogsService();

  public void handle(HttpExchange exchange) throws IOException {

    String path = exchange.getRequestURI().getPath();

    try {

      if (path.equalsIgnoreCase("/dogs/list")) {
        String response = fetchFromApi("https://dog.ceo/api/breeds/list/all", client);
        JsonObject allDogs = dogService.jsonDogsAllModificado(response).getAsJsonObject();
        sendResponse(exchange, 200, gson.toJson(allDogs));

        return;
      }
      if (path.equalsIgnoreCase("/dogs/list/razasquenotienensubrazas")) {
        String response = fetchFromApi("https://dog.ceo/api/breeds/list/all", client);
        JsonObject onlyBreeds = dogService.jsonDogsrazasquenotienensubrazas(response).getAsJsonObject();
        sendResponse(exchange, 200, gson.toJson(onlyBreeds));

        return;

      }
      if (path.equalsIgnoreCase("/dogs/list/razasquesitienensubrazas")) {
        String response = fetchFromApi("https://dog.ceo/api/breeds/list/all", client);
        JsonObject onlyBreeds = dogService.jsonDogsrazasquesitienensubrazas(response).getAsJsonObject();
        sendResponse(exchange, 200, gson.toJson(onlyBreeds));

        return;
      }
      if (path.equalsIgnoreCase("/dogs/list/image/random")) {
        String response = fetchFromApi("https://dog.ceo/api/breeds/image/random", client);
        JsonObject imageRandom = dogService.jsonDogsImageRandom(response).getAsJsonObject();
        sendResponse(exchange, 200, gson.toJson(imageRandom));
        return;
      }

      sendResponse(exchange, 404, "Endpoint no encontrado");

    } catch (Exception e) {
      sendResponse(exchange, 500, "Error llamando a la API dogs: " + e.getMessage());
    }

  }


  private String fetchFromApi(String apiUrl, HttpClient client) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(apiUrl))
        .GET()
        .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }

  private void sendResponse(HttpExchange exchange, int status, String body) throws IOException {
    exchange.getResponseHeaders().add("Content-Type", "application/json");
    byte[] bytes = body.getBytes();
    exchange.sendResponseHeaders(status, bytes.length);
    OutputStream os = exchange.getResponseBody();
    os.write(bytes);
    os.close();
  }

}