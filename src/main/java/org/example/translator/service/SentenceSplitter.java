package org.example.translator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSplitter {
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
