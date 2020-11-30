package com.coremedia.blueprint.contenthub.adapters.typeform.model;

import com.coremedia.contenthub.api.ContentHubType;

public enum TypeformContentHubType {

  FOLDER(new ContentHubType("folder")),
  FORM(new ContentHubType("form"));

  private ContentHubType type;

  TypeformContentHubType(ContentHubType type) {
    this.type = type;
  }

  public ContentHubType getType() {
    return type;
  }

}
