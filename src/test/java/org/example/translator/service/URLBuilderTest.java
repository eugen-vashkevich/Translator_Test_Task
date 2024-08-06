package org.example.translator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class URLBuilderTest {

  private URLBuilder urlBuilder;

  @BeforeEach
  public void setUp() {
    urlBuilder = new URLBuilder();
  }

  @Test
  public void testBuildUrl() {
    // Given
    final var sourceLanguage = "en";
    final var targetLanguage = "es";
    final var word = "hello";
    final var expectedUrl =
        "https://translate.googleapis.com/translate_a/single?client=gtx&dt=t"
            + "&sl="
            + sourceLanguage
            + "&tl="
            + targetLanguage
            + "&q="
            + word;

    // When
    final var actualUrl = urlBuilder.buildUrl(sourceLanguage, targetLanguage, word);

    // Then
    assertEquals(expectedUrl, actualUrl);
  }

  @Test
  public void testBuildUrlWithEmptyWord() {
    // Given
    final var sourceLanguage = "en";
    final var targetLanguage = "de";
    final var word = "";
    final var expectedUrl =
        "https://translate.googleapis.com/translate_a/single?client=gtx&dt=t"
            + "&sl="
            + sourceLanguage
            + "&tl="
            + targetLanguage
            + "&q=";

    // When
    final var actualUrl = urlBuilder.buildUrl(sourceLanguage, targetLanguage, word);

    // Then
    assertEquals(expectedUrl, actualUrl);
  }
}
