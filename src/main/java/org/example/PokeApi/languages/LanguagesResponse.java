package org.example.PokeApi.languages;

import java.util.List;

public class LanguagesResponse {
  private List<Languages> id;

  public LanguagesResponse() {
  }

  public LanguagesResponse(List<Languages> id) {
    this.id = id;
  }

  public List<Languages> getId() {
    return id;
  }

  public void setId(List<Languages> id) {
    this.id = id;
  }
}
