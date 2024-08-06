package org.example.translator.service;

import org.example.translator.models.TranslateRequest;
import org.example.translator.repositories.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.example.translator.service.SentenceSplitter.splitSentence;

@Service
public class TranslatorService {

  @Autowired private TranslationRepository translationRepository;

  @Autowired private URLBuilder urlBuilder;

  @Autowired private RestTemplate restTemplate;

  private final ExecutorService executorService = Executors.newFixedThreadPool(10);

  public ResponseEntity<String> translate(TranslateRequest translateBody, String ipAddress) {
    final var sourceLanguage = translateBody.getSourceLang();
    final var targetLanguage = translateBody.getTargetLang();
    final var words = splitSentence(translateBody.getTextToTranslate());

    List<Future<String>> futures =
        words.stream()
            .filter(word -> !Pattern.matches("\\p{Punct}", word))
            .map(
                word -> {
                  final var url = urlBuilder.buildUrl(sourceLanguage, targetLanguage, word);
                  return executorService.submit(new TranslationTask(url, restTemplate));
                })
            .collect(Collectors.toList());

    final var translatedWords = handleFutures(futures);
    if (translatedWords == null) {
      return new ResponseEntity<>("Unknown language code", HttpStatus.BAD_REQUEST);
    }

    final var translatedText = assembleTranslatedText(words, translatedWords);

    translateBody.setTranslatedText(translatedText);
    translateBody.setIpAddress(ipAddress);

    try {
      translationRepository.save(translateBody);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return new ResponseEntity<>(translatedText, HttpStatus.OK);
  }

  private String assembleTranslatedText(List<String> originalWords, List<String> translatedWords) {
    final var translatedText = new StringBuilder();
    int wordIndex = 0;

    for (var item : originalWords) {
      if (item.matches("\\p{Punct}")) {
        translatedText.append(item);
        wordIndex++;
      } else {
        translatedText.append(translatedWords.get(wordIndex));
      }
      translatedText.append(" ");
    }

    return translatedText.toString().trim();
  }

  private List<String> handleFutures(List<Future<String>> futures) {
    final var translatedWords = new ArrayList<String>();
    for (var future : futures) {
      try {
        translatedWords.add(future.get());
      } catch (InterruptedException | ExecutionException e) {
        return null;
      }
    }
    return translatedWords;
  }
}
