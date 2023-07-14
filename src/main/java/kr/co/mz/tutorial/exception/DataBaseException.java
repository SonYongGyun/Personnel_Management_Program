package kr.co.mz.tutorial.exception;

public class DataBaseException extends RuntimeException {

  public DataBaseException(String message) {
    super(message);
  }

  public DataBaseException(String message, Throwable t) {
    super(message, t);
  }

}
