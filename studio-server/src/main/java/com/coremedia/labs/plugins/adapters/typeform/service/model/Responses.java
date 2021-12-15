package com.coremedia.labs.plugins.adapters.typeform.service.model;

import java.util.List;

public class Responses {
  private int totalItems;
  private List<Response> items;

  public int getTotalItems() {
    return totalItems;
  }

  public List<Response> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "Responses{" +
            "totalItems=" + totalItems +
            ", items=" + items +
            '}';
  }
}
