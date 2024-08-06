package org.example.translator.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.translator.models.TranslateRequest;
import org.example.translator.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class TranslatorController {

    @Autowired
    TranslatorService translatorService;

    @PostMapping("/translate")
    public ResponseEntity<String> translate(
            @RequestBody TranslateRequest translateBody, HttpServletRequest request) {
        final var ipAddress = request.getRemoteAddr();
        return translatorService.translate(translateBody, ipAddress);
    }
}