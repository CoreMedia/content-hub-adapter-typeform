package com.coremedia.labs.plugins.adapters.typeform.server.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Answer {
  private String fieldId;
  private String fieldType;
  private String fieldRef;
  @JsonProperty("type")
  private String type;
  @JsonProperty("text")
  private String textValue;
  @JsonProperty("boolean")
  private Boolean booleanValue;
  @JsonProperty("email")
  private String emailValue;
  @JsonProperty("number")
  private Integer numberValue;
  private List<String> choiceValue;

  @JsonProperty("field")
  private void unpackFieldFromNestedObject(Map<String, String> field) {
    this.fieldId = field.get("id");
    this.fieldType = field.get("type");
    this.fieldRef = field.get("ref");
  }

  @JsonProperty("choices")
  private void unpackChoicesFromNestedObject(Map<String, List<String>> choices) {
    this.choiceValue = choices.get("labels");
  }

  @JsonProperty("choice")
  private void unpackChoiceFromNestedObject(Map<String, String> choice) {
    this.choiceValue = Arrays.asList(choice.get("label"));
  }

  public String getFieldId() {
    return fieldId;
  }

  public String getFieldType() {
    return fieldType;
  }

  public String getFieldRef() {
    return fieldRef;
  }

  public String getType() {
    return type;
  }

  public Object getValue() {
    switch (type) {
      case "text":
        return textValue;
      case "email":
        return emailValue;
      case "boolean":
        return booleanValue;
      case "number":
        return numberValue;
      case "choice":
      case "choices":
        return choiceValue;
    }
    return null;
  }

  @Override
  public String toString() {
    return "Answer{" +
            "fieldId='" + fieldId + '\'' +
            ", fieldType='" + fieldType + '\'' +
            ", fieldRef='" + fieldRef + '\'' +
            ", type='" + type + '\'' +
            ", value='" + getValue() +
            '}';
  }
}
