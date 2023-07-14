package kr.co.mz.tutorial.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrongAccessException extends RuntimeException {

  private final String redirectUrl;
  private static final Logger LOGGER = LoggerFactory.getLogger(WrongAccessException.class);

  public WrongAccessException(String message, String redirectUrl) {
    super(message);
    this.redirectUrl = redirectUrl;
    LOGGER.debug("A WrongAccessException occurred. Going back to home.");
  }

  public WrongAccessException(String message) {
    this(message, null);
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }
}
