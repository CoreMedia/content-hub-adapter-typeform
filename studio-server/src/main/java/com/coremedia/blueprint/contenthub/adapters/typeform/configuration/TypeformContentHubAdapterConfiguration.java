package com.coremedia.blueprint.contenthub.adapters.typeform.configuration;

import com.coremedia.blueprint.contenthub.adapters.typeform.TypeformContentHubAdapterFactory;
import com.coremedia.blueprint.contenthub.adapters.typeform.TypeformContentHubSettings;
import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeformContentHubAdapterConfiguration {

  @Bean
  public ContentHubAdapterFactory<TypeformContentHubSettings> typeformContentHubAdapterFactory() {
    return new TypeformContentHubAdapterFactory();
  }

}
