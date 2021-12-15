package com.coremedia.labs.plugins.adapters.typeform.configuration;

import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.typeform.TypeformContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.typeform.TypeformContentHubSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeformContentHubAdapterConfiguration {

  @Bean
  public ContentHubAdapterFactory<TypeformContentHubSettings> typeformContentHubAdapterFactory() {
    return new TypeformContentHubAdapterFactory();
  }

}
