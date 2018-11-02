package com.coviam.finance.backend.repository.utils;

import com.coviam.finance.backend.entity.CurrencyMaster;

public interface TestUtils {

  Long ID = 100L;
  int STORE_ID = 10001;

  public static CurrencyMaster getObjectOfCurrencyMaster(Long currencyId, String currencyCode,
      String currencyDescription) {
    CurrencyMaster currencyMaster =
        new CurrencyMaster(currencyId, currencyCode, currencyDescription);
    return currencyMaster;
  }

}
