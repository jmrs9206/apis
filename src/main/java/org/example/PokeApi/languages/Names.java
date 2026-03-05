package org.example.PokeApi.languages;

public class Names {
  private String name;
  private Language language;

  public Names(String name, Language language) {
    this.name = name;
    this.language = language;
  }

  public String getName() {
    return name;
  }

  public Language getLanguage() {
    return language;
  }

  @Override
  public String toString() {
    return name + " (" + language.getName() + ")";
  }

}