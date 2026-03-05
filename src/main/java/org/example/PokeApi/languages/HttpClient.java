package org.example.PokeApi.languages;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {

  // URL de PokeAPI
  private final String url = "https://pokeapi.co/api/v2/language/1";

  // Cliente HTTP
  private final java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

  // Metodo principal que hace la petición y devuelve el Lenguaje
  public Languages getLanguages() throws IOException, InterruptedException {

    // Construcción de la petición GET
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    // Envío de la petición
    HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());

    // Parseo del JSON
    Gson gson = new Gson();
    Languages data = gson.fromJson(response.body(), Languages.class);

    return data;
  }

}