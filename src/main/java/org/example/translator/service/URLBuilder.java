package org.example.translator.service;

import org.springframework.stereotype.Service;

/** This service class provides functionality to build a URL for Google Translate API requests. */
@Service
public class URLBuilder {

  private static StringBuilder baseURL =
      new StringBuilder("https://translate.googleapis.com/translate_a/single?client=gtx&dt=t");

  /**
   * Builds a complete URL for the Google Translate API request based on the provided parameters.
   *
   * @param sourceLanguage the source language code (e.g., "en" for English)
   * @param targetLanguage the target language code (e.g., "ru" for Russian)
   * @param word the word or phrase to be translated
   * @return the complete URL as a {@code String} which includes the base URL, source language,
   *     target language, and the word to be translated
   */
  public String buildUrl(String sourceLanguage, String targetLanguage, String word) {
    final var completedURL =
        baseURL + "&sl=" + sourceLanguage + "&tl=" + targetLanguage + "&q=" + word;
    return completedURL;
  }
}
