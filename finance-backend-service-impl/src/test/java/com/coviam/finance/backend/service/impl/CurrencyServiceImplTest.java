package com.coviam.finance.backend.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.coviam.finance.backend.entity.CurrencyMaster;
import com.coviam.finance.backend.repository.CurrencyRepository;
import com.coviam.finance.backend.repository.utils.TestUtils;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;


public class CurrencyServiceImplTest {

  private CurrencyMaster currencyMaster;

  @InjectMocks
  private CurrencyServiceImpl currencyService;

  @Mock
  private CurrencyRepository currencyRepository;

  @Before
  public void init() throws Exception {
    initMocks(this);
    this.currencyMaster =
        TestUtils.getObjectOfCurrencyMaster(TestUtils.ID, "INR", "indian currency");
  }

  @Test
  public void findById_IdNullTest() throws Exception {
    try {
      this.currencyService.findById(null);
    } catch (BusinessException e) {
      assertEquals(e.getErrorCode(), Error.CURRENCY_ID_NULL.getCode());
    }
  }

  @Test
  public void findByIdNullTest() throws Exception {
    Mockito.when(this.currencyRepository.findByCurrencyId(TestUtils.ID)).thenReturn(null);

    try {
      this.currencyService.findById(TestUtils.ID);
    } catch (BusinessException e) {
      assertEquals(e.getErrorCode(), Error.CURRENCY_NOT_FOUND.getCode());
      verify(this.currencyRepository).findByCurrencyId(TestUtils.ID);
    }
  }

  @Test
  public void findByIdSuccessTest() throws Exception {
    Mockito.when(this.currencyRepository.findByCurrencyId(TestUtils.ID)).thenReturn(currencyMaster);
    CurrencyMaster result = this.currencyService.findById(TestUtils.ID);
    assertNotNull(result);
    assertEquals(result.getCurrencyId(), currencyMaster.getCurrencyId());
    Mockito.verify(this.currencyRepository).findByCurrencyId(TestUtils.ID);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.currencyRepository);
  }
}
