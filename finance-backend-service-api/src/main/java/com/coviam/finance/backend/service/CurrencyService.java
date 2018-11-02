package com.coviam.finance.backend.service;


import com.coviam.finance.backend.entity.CurrencyMaster;

import java.util.List;


public interface CurrencyService {

  CurrencyMaster findById(Long currencyId);
  List<CurrencyMaster> findAll(int storeId);
}
