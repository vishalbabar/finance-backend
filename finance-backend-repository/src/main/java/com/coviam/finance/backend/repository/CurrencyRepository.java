package com.coviam.finance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coviam.finance.backend.entity.CurrencyMaster;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyMaster, Long> {
  CurrencyMaster findByCurrencyId(Long currencyId);
}
