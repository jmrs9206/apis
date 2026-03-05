package org.example.DogsServer.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DogsService {

  private final Gson gson = new Gson();

  public JsonObject jsonDogsAllModificado(String response) {
    JsonObject jsonRaiz = gson.fromJson(response, JsonObject.class);
    JsonObject message = jsonRaiz.getAsJsonObject("message");

    JsonArray listaRazas = new JsonArray();

    for (String raza : message.keySet()) {
      JsonArray subRazas = message.get(raza).getAsJsonArray();

      if (!subRazas.isEmpty()) {
        for (JsonElement subRaza : subRazas) {
          String nombre = raza + "-" + subRaza.getAsString();

          JsonObject dogs = new JsonObject();
          dogs.addProperty("nombre", nombre);
          listaRazas.add(dogs);

        }
      } else {

        JsonObject dogs = new JsonObject();
        dogs.addProperty("nombre", raza);
        listaRazas.add(dogs);

      }
    }

    JsonObject perros = new JsonObject();
    perros.add("perros", listaRazas);

    return perros;
  }

  public JsonObject jsonDogsrazasquenotienensubrazas(String response) {
    JsonObject jsonRaiz = gson.fromJson(response, JsonObject.class);
    JsonObject message = jsonRaiz.getAsJsonObject("message");

    JsonArray listaRazas = new JsonArray();

    for (String razas : message.keySet()) {

      boolean conSubRazas = message.get(razas).getAsJsonArray().isEmpty();

      if (conSubRazas) {
        JsonObject dogs = new JsonObject();
        dogs.addProperty("nombre", razas);
        listaRazas.add(dogs);
      }
    }
    JsonObject perros = new JsonObject();
    perros.add("perros", listaRazas);

    return perros;
  }

  public JsonObject jsonDogsrazasquesitienensubrazas(String response) {
    JsonObject jsonRaiz = gson.fromJson(response, JsonObject.class);
    JsonObject message = jsonRaiz.getAsJsonObject("message");

    JsonArray listaRazas = new JsonArray();

    for (String razas : message.keySet()) {

      boolean razasConSubRazas = message.get(razas).getAsJsonArray().isEmpty();

      JsonArray subRazas = message.get(razas).getAsJsonArray();

      if (!razasConSubRazas) {
        for (JsonElement ConSubRazas : subRazas) {
          String nombre = razas + "-" + ConSubRazas.getAsString();

          JsonObject dogs = new JsonObject();
          dogs.addProperty("nombre", nombre);
          listaRazas.add(dogs);
        }
      }
    }
    JsonObject perros = new JsonObject();
    perros.add("perros", listaRazas);

    return perros;
  }


  public JsonObject jsonDogsImageRandom(String response) {

    JsonObject jsonRaiz = gson.fromJson(response, JsonObject.class);
    String message = jsonRaiz.get("message").getAsString();
    JsonObject imageRandom = new JsonObject();
    imageRandom.addProperty("imagen", message);

    return imageRandom;

  }


}
