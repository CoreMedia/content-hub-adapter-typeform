package com.coremedia.labs.plugins.adapters.typeform.server.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Item;
import com.coremedia.labs.plugins.adapters.typeform.server.service.model.Form;
import edu.umd.cs.findbugs.annotations.NonNull;

public class TypeformItem extends TypeformContentHubObject implements Item {

  private final TypeformContentHubType type;
  private final Form form;

  public TypeformItem(@NonNull ContentHubObjectId objectId, @NonNull Form form, TypeformContentHubType type) {
    super(objectId);
    this.type = type;
    this.form = form;
  }

  @Override
  public ContentHubType getContentHubType() {
    return type.getType();
  }


  @Override
  public String getCoreMediaContentType() {
    return "CMHTML";
  }

  @Override
  public String getName() {
    return form.getTitle();
  }

  public String getEmbedCode() {
    return form.getDisplay();
  }
}
