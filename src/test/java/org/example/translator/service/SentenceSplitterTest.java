package org.example.translator.service;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentenceSplitterTest {

  @Test
  public void testSplitSentenceWithPunctuation() {
    // Given
    final var text = "Hello, world!";
    final var expectedResult = Arrays.asList("Hello", ",", "world", "!");

    // When
    final var actualResult = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void testSplitSentenceWithoutPunctuation() {
    // Given
    final var text = "Hello world";
    final var expectedResult = Arrays.asList("Hello", "world");

    // When
    final var actualResult = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void testSplitSentenceWithMultipleSpaces() {
    // Given
    final var text = "Hello   world";
    final var expected = Arrays.asList("Hello", "world");

    // When
    final var actual = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSplitSentenceWithSpecialCharacters() {
    // Given
    final var text = "Hello @world!";
    final var expected = Arrays.asList("Hello", "@", "world", "!");

    // When
    final var actual = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSplitEmptySentence() {
    // Given
    final var text = "";
    final var expected = Arrays.asList();

    // When
    final var actual = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSplitSentenceWithNumbers() {
    // Given
    final var text = "Year 2024 is here.";
    final var expected = Arrays.asList("Year", "2024", "is", "here", ".");

    // When
    final var actual = SentenceSplitter.splitSentence(text);

    // Then
    assertEquals(expected, actual);
  }
}
