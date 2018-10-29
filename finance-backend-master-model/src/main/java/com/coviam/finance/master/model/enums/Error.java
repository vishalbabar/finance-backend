package com.coviam.finance.master.model.enums;

public enum Error {

  FINANCE_ID_NOT_FOUND("finance id not found"),

  // others
  SYSTEM_ERROR("Internal system error");

  private String message;

  Error(String message) {
    this.message = message;
  }

  public String getCode() {
    return this.name();
  }

  public String getMessage() {
    return this.message;
  }
}
