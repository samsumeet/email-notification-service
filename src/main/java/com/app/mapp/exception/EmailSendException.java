package com.app.mapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSendException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(EmailSendException.class);

  public EmailSendException(String message) {
    super(message);
    LOGGER.error(message);

  }
}