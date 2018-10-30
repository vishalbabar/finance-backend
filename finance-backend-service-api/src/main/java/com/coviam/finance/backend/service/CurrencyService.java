package com.coviam.finance.backend.service;


import com.coviam.finance.backend.entity.CurrencyMaster;


public interface CurrencyService {

  CurrencyMaster findById(Long currencyId);
}
