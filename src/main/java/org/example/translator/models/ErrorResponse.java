package org.example.translator.models;

/** Represents the response containing an error message. */
public class ErrorResponse {
  private String error;

  public ErrorResponse() {}

  public ErrorResponse(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
