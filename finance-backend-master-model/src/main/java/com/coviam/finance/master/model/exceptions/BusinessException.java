package com.coviam.finance.master.model.exceptions;

import com.coviam.finance.master.model.enums.Error;

public class BusinessException extends RuntimeException {

  private final Error error;

  public BusinessException(Error errorCode) {
    this.error = errorCode;
  }

  public String getErrorCode() {
    return this.error.getCode();
  }

  @Override
  public String getMessage() {
    return this.error.getMessage();
  }
}
