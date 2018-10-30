package com.coviam.finance.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviam.finance.backend.entity.CurrencyMaster;
import com.coviam.finance.backend.repository.CurrencyRepository;
import com.coviam.finance.backend.service.CurrencyService;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.utils.CommonUtils;


@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Override
  public CurrencyMaster findById(Long currencyId) {
    CommonUtils.checkError(currencyId == null, Error.CURRENCY_ID_NULL);
    CurrencyMaster currencyMaster = this.currencyRepository.findByCurrencyId(currencyId);
    CommonUtils.checkError(currencyMaster == null, Error.CURRENCY_NOT_FOUND);
    return currencyMaster;
  }
}
