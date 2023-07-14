package kr.co.mz.tutorial.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertQueryNotFoundException extends AlertException {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlertQueryNotFoundException.class);

  public AlertQueryNotFoundException(String message) {
    super(message);
    LOGGER.debug("AlertQueryNotFoundException is occurred");
  }
}
