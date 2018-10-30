package com.coviam.finance.backend.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse implements Serializable {

  private static final long serialVersionUID = 657938964063522581L;
  private Long currencyId;
  private String currencyCode;
  private String currencyDescription;
}
