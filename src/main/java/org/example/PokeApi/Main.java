package org.example.PokeApi;

import org.example.PokeApi.languages.HttpClient;
import org.example.PokeApi.languages.Languages;

public class Main {
  public static void main(String[] args) {

    try {

      HttpClient client = new HttpClient();
      Languages l = client.getLanguages();

      l.mostrarInformacion();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
