package com.coviam.backend.service;


import com.coviam.finance.backend.entity.CurrencyMaster;


public interface FinanceService {

  CurrencyMaster findById(Long currencyId);
}
