package org.example.translator.service;

import org.springframework.stereotype.Service;

@Service
public class URLBuilder {

  private static StringBuilder baseURL =
      new StringBuilder("https://translate.googleapis.com/translate_a/single?client=gtx&dt=t");

  public String buildUrl(String sourceLanguage, String targetLanguage, String word) {
    final var completedURL = baseURL + "&sl=" + sourceLanguage +
            "&tl=" + targetLanguage + "&q=" + word;
    return completedURL;
  }
}
