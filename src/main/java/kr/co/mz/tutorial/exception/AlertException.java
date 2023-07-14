package kr.co.mz.tutorial.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlertException.class);

  private final String redirectUrl;

  public AlertException(String message) {
    this(message, null);
    LOGGER.debug("Through AlertException");
  }

  public AlertException(String message, String redirectUrl) {
    super(message);
    this.redirectUrl = redirectUrl;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }
}
