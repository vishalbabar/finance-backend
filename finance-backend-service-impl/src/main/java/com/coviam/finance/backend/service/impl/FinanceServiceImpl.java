package com.coviam.finance.backend.service.impl;

import com.coviam.backend.service.FinanceService;
import com.coviam.finance.backend.entity.CurrencyMaster;
import com.coviam.finance.backend.repository.FinanceRepository;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FinanceServiceImpl implements FinanceService {

  @Autowired
  private FinanceRepository financeRepository;

  @Override
  public CurrencyMaster findById(Long currencyId) {
    CurrencyMaster currencyMaster = this.financeRepository.findByCurrencyId(currencyId);
    if (currencyMaster != null)
      return currencyMaster;
    else
      throw new BusinessException(Error.FINANCE_ID_NOT_FOUND);
  }
}
