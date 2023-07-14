package kr.co.mz.tutorial.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertQueryStringValidationException extends AlertException {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlertQueryStringValidationException.class);

  public AlertQueryStringValidationException(String message) {
    super(message);
    LOGGER.debug("A QueryStringValidationException occurred");
  }


}
