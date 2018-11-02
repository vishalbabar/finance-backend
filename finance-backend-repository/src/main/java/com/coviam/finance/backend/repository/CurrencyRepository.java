package com.coviam.finance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coviam.finance.backend.entity.CurrencyMaster;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyMaster, Long> {
  CurrencyMaster findByStoreIdAndCurrencyId(int storeId,Long currencyId);
  List<CurrencyMaster> findAllByStoreId(int storeId);
}
