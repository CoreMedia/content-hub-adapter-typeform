package com.coremedia.labs.plugins.adapters.typeform.server.service;


import com.coremedia.labs.plugins.adapters.typeform.server.service.model.Form;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class TypeformServiceTest {

  private static final String TYPEFORM_CLIENT_ACCESSTOKEN = "E5r1k292z7fr2haY923xpUtDmVsQznc7ZEjjjV3W1NCo";

  private final TypeformService client;

  public TypeformServiceTest() {
    client = new TypeformService(TYPEFORM_CLIENT_ACCESSTOKEN);
  }

  @Test
  public void getForms() {
    List<Form> forms = client.getForms();
    Assert.assertNotNull(forms);
  }

  @Test
  public void getFormById() {
    Form form = client.getFormById("KvyBuo");
    Assert.assertNotNull(form);
  }

  @Test
  public void string() {
    String moep = "<div class=\"typeform-widget\" data-url=\"%s\" style=\"width: 100%%; height: 500px;\"></div> <script> (function() { var qs,js,q,s,d=document, gi=d.getElementById, ce=d.createElement, gt=d.getElementsByTagName, id=\"typef_orm\", b=\"https://embed.typeform.com/\"; if(!gi.call(d,id)) { js=ce.call(d,\"script\"); js.id=id; js.src=b+\"embed.js\"; q=gt.call(d,\"script\")[0]; q.parentNode.insertBefore(js,q) } })() </script>";
    String result = String.format(moep, "blub");
    System.out.println(result);

  }
}
