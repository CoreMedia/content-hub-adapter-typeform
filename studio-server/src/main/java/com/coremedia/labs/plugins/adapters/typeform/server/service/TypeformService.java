package com.coremedia.labs.plugins.adapters.typeform.server.service;


import com.coremedia.labs.plugins.adapters.typeform.server.service.model.Form;
import com.coremedia.labs.plugins.adapters.typeform.server.service.model.Forms;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TypeformService {

  private static final Logger LOG = LoggerFactory.getLogger(TypeformService.class);

  private static final String DEFAULT_API_ENDPOINT = "https://api.typeform.com";

  private static final String ID = "id";

  private final RestTemplate restTemplate;
  private final String apiEndpoint;
  private final String apiKey;

  public TypeformService(@NonNull String apiKey) {
    this(DEFAULT_API_ENDPOINT, apiKey);
  }

  public TypeformService(@NonNull String apiEndpoint, @NonNull String apiKey) {
    this.apiEndpoint = apiEndpoint;
    this.apiKey = apiKey;
    this.restTemplate = new RestTemplate();
  }

  public Form getFormById(String formId) {
    return performApiCall("/forms/{id}", Map.of(ID, formId), Form.class);
  }

  public List<Form> getForms() {
    Forms forms = performApiCall("/forms", Map.of(), Forms.class);
    if (forms != null) {
      return forms.getItems();
    }
    return new ArrayList<>();
  }

  private <T> T performApiCall(String path, @NonNull Map<String, Object> pathVariables, Class<T> responseType) {

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + this.apiKey);
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiEndpoint + path);


    // perform request
    String url = uriBuilder.buildAndExpand(pathVariables).toUriString();
    LOG.debug("GET - {}", url);
    //ResponseEntity<String> debugResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
    if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
      return null;
    }
    return exchange.getBody();
  }
}
