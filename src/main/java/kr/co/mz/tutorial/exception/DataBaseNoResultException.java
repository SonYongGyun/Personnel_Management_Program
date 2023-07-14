package kr.co.mz.tutorial.exception;

public class DataBaseNoResultException extends DataBaseException {

  public DataBaseNoResultException(String message, Throwable t) {
    super(message, t);
  }

  public DataBaseNoResultException(String s) {
    super(s);
  }
}
