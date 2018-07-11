package com.example.mockbus.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAlreadyExistException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private static Logger logger = LogManager
      .getLogger(EntityAlreadyExistException.class);

  public EntityAlreadyExistException(String s) {
    super(s);
    logger.error(this.getMessage());

  }
}
