package org.example.translator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSplitter {
  /**
   * Splits the given text into a list of words and punctuation marks.
   *
   * @param text The input text to be split. If the text is {@code null} or empty, the method
   *     returns an empty list.
   * @return A list of strings where each string is either a word or a punctuation mark found in the
   *     input text. The list maintains the order of tokens as they appear in the input text.
   * @example
   *     <pre>
   * SentenceSplitter.splitSentence("Hello, world!");
   * // Returns: ["Hello", ",", "world", "!"]
   * </pre>
   */
  public static List<String> splitSentence(String text) {
    List<String> wordsWithPunctuation = new ArrayList<>();
    Pattern pattern = Pattern.compile("\\w+|\\p{Punct}");
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
      wordsWithPunctuation.add(matcher.group());
    }

    return wordsWithPunctuation;
  }
}
