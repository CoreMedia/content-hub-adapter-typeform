package com.coremedia.blueprint.contenthub.adapters.typeform.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Folder;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TypeformFolder extends TypeformContentHubObject implements Folder {

  private final ContentHubType type;
  private final String name;

  public TypeformFolder(@NonNull ContentHubObjectId objectId, String name, TypeformContentHubType type) {
    super(objectId);
    this.name = name;
    this.type = type.getType();
  }

  @Override
  public String getName() {
    return name;
  }

  @NonNull
  @Override
  public ContentHubType getContentHubType() {
    return type;
  }

}
