package kr.co.mz.tutorial.exception;

public class DataBaseInsertException extends DataBaseException {

  private final String redirectUrl;


  public DataBaseInsertException(String message, String redirectUrl, Throwable t) {
    super(message, t);
    this.redirectUrl = redirectUrl;
  }

  public DataBaseInsertException(String message, Throwable t) {
    this(message, null, t);
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }
}
