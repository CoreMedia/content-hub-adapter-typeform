package com.coremedia.labs.plugins.adapters.typeform.service;


import com.coremedia.labs.plugins.adapters.typeform.service.model.Form;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TypeformServiceTest {

  private static final String TYPEFORM_CLIENT_ACCESSTOKEN = "";

  private final TypeformService client;

  public TypeformServiceTest() {
    client = new TypeformService(TYPEFORM_CLIENT_ACCESSTOKEN);
  }

  @Test
  @Disabled
  public void getForms() {
    List<Form> forms = client.getForms();
    assertNotNull(forms);
  }

  @Test
  @Disabled
  public void getFormById() {
    Form form = client.getFormById("KvyBuo");
    assertNotNull(form);
  }
}
