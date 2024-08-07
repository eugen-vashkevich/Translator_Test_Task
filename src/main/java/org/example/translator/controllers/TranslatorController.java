package org.example.translator.controllers;

import org.example.translator.models.TranslateRequest;
import org.example.translator.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/** Controller class responsible for handling translation requests. */
@RestController
@RequestMapping("/api")
public class TranslatorController {

  @Autowired TranslatorService translatorService;

  /**
   * Translates the given text using the TranslatorService and returns the translated text or error
   * message in the response body.
   *
   * @param translateBody the request body containing the text to translate and other details
   * @param request the HTTP servlet request to obtain the IP address
   * @return a ResponseEntity containing the translated text or error message
   */
  @PostMapping("/translate")
  public ResponseEntity<?> translate(
      @RequestBody TranslateRequest translateBody, HttpServletRequest request) {
    final var ipAddress = request.getRemoteAddr();
    return translatorService.translate(translateBody, ipAddress);
  }
}
