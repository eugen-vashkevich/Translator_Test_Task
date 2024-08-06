package org.example.translator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.Callable;

import static org.example.translator.service.TranslationParser.parseTranslationResponse;

class TranslationTask implements Callable<String> {
  private final String url;
  private final RestTemplate restTemplate;

  TranslationTask(String url, RestTemplate restTemplate) {
    this.url = url;
    this.restTemplate = restTemplate;
  }

  @Override
  public String call() throws IOException {
    ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
    if (entity.getStatusCode().is2xxSuccessful()) {
      return parseTranslationResponse(entity.getBody());
    } else {
      throw new IllegalStateException();
    }
  }
}
