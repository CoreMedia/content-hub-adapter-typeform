package com.coremedia.blueprint.contenthub.adapters.typeform.model;

import com.coremedia.blueprint.contenthub.adapters.typeform.service.model.Form;
import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Item;
import edu.umd.cs.findbugs.annotations.NonNull;

public class TypeformItem extends TypeformContentHubObject implements Item {

  private TypeformContentHubType type;
  private Form form;

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
