package com.coremedia.labs.plugins.adapters.typeform.server.configuration;

import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.typeform.server.TypeformContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.typeform.server.TypeformContentHubSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeformContentHubAdapterConfiguration {

  @Bean
  public ContentHubAdapterFactory<TypeformContentHubSettings> typeformContentHubAdapterFactory() {
    return new TypeformContentHubAdapterFactory();
  }

}
