package com.coremedia.labs.plugins.adapters.typeform.server;

import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class TypeformContentHubAdapterFactory implements ContentHubAdapterFactory<TypeformContentHubSettings> {

  private static final String ADAPTER_ID = "typeform";

  @Override
  public String getId() {
    return ADAPTER_ID;
  }

  @Override
  public ContentHubAdapter createAdapter(@NonNull TypeformContentHubSettings settings,
                                         @NonNull String connectionId) {
    return new TypeformContentHubAdapter(settings, connectionId);
  }
}
