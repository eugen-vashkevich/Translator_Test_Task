package org.example.translator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class TranslationParser {

  public static String parseTranslationResponse(String response) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(response);

    if (!rootNode.isArray() || rootNode.isEmpty()) {
      throw new IOException("Invalid response format");
    }

    JsonNode translationsArray = rootNode.get(0);

    if (!translationsArray.isArray() || translationsArray.isEmpty()) {
      throw new IOException("Invalid translations format");
    }

    JsonNode translation = translationsArray.get(0);
    if (translation.isArray() && translation.get(4).asInt() != 5) {
      return translation.get(0).asText();
    } else {
      throw new IOException("Unknown code language");
    }
  }
}
