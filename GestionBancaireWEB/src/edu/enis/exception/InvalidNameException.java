package edu.enis.exception;

@SuppressWarnings("serial")
public class InvalidNameException extends Exception {
  private String name = "";

  public InvalidNameException() {
    name = "";
  }

  public InvalidNameException(String str) {
    name = str;
  }

  public String getMessage() {
    return "'" + name + "' is invalid, name must be at least 3 characters long";
  }

}
