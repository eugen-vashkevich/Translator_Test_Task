package org.example.translator.models;

public class TranslateRequest {
  private String ipAddress;
  private String sourceLang;
  private String targetLang;
  private String textToTranslate;
  private String translatedText;

  public TranslateRequest(String textToTranslate, String sourceLang, String targetLang) {
    this.textToTranslate = textToTranslate;
    this.sourceLang = sourceLang;
    this.targetLang = targetLang;
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void setTranslatedText(String translatedText) {
    this.translatedText = translatedText;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getTextToTranslate() {
    return textToTranslate;
  }

  public void setTextToTranslate(String textToTranslate) {
    this.textToTranslate = textToTranslate;
  }

  public String getSourceLang() {
    return sourceLang;
  }

  public void setSourceLang(String sourceLang) {
    this.sourceLang = sourceLang;
  }

  public String getTargetLang() {
    return targetLang;
  }

  public void setTargetLang(String targetLang) {
    this.targetLang = targetLang;
  }
}
