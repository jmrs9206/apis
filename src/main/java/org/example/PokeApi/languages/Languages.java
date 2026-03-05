package org.example.PokeApi.languages;

import java.util.List;

public class Languages {
  private int id;
  private String iso639;
  private String iso3166;
  private String name;
  private List<Names> names;
  private boolean official;

  public Languages(int id, String iso639, String iso3166, String name, List<Names> names, boolean official) {
    this.id = id;
    this.iso639 = iso639;
    this.iso3166 = iso3166;
    this.name = name;
    this.names = names;
    this.official = official;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIso639() {
    return iso639;
  }

  public void setIso639(String iso639) {
    this.iso639 = iso639;
  }

  public String getIso3166() {
    return iso3166;
  }

  public void setIso3166(String iso3166) {
    this.iso3166 = iso3166;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Names> getNames() {
    return names;
  }

  public void setNames(List<Names> names) {
    this.names = names;
  }

  public boolean isOfficial() {
    return official;
  }

  public void setOfficial(boolean official) {
    this.official = official;
  }

  @Override
  public String toString() {
    return "Languages{" + "id=" + id + ", iso639='" + iso639 + '\'' + ", iso3166='" + iso3166
        + '\'' + ", name='" + name + '\'' + ", names=" + names + ", official=" + official + '}';
  }

  public void mostrarInformacion() {
    System.out.println("--- Información del Idioma ---");
    System.out.println("ID: " + id);
    System.out.println("Nombre: " + name);
    System.out.println("ISO 639: " + iso639);
    System.out.println("ISO 3166: " + iso3166);
    System.out.println("Oficial: " + (official ? "Sí" : "No"));
    System.out.println("Language:");
    if (names != null) {
      for (Names n : names) {
        System.out.println("  - " + n.getName() + " (" + n.getLanguage().getName() + ")");
      }
    }
    System.out.println("-------------------------------");
  }


}