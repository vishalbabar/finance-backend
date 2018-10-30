package com.coviam.finance.backend.api.web;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coviam.finance.backend.api.web.controller.CurrencyController;
import com.coviam.finance.backend.repository.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coviam.finance.backend.api.web.helper.api.WebModelConverterService;
import com.coviam.finance.backend.entity.CurrencyMaster;
import com.coviam.finance.backend.service.CurrencyService;
import com.coviam.finance.backend.web.model.response.CurrencyResponse;
import com.coviam.finance.master.model.constants.ApiPath;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;

/**
 * Created by Vishal B on Oct, 2018
 */
public class CurrencyControllerTest {

  private static Long CURRENCY_ID = 100L;
  @InjectMocks
  private CurrencyController currencyController;

  @Mock
  private CurrencyService currencyService;

  @Mock
  private WebModelConverterService webModelConverterService;

  private MockMvc mockMvc;
  private CurrencyResponse currencyResponse;
  private CurrencyMaster currencyMaster;

  @Before
  public void init() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.currencyController).build();
    this.currencyMaster =
        TestUtils.getObjectOfCurrencyMaster(CURRENCY_ID, "INR", "indian currency");
    this.currencyResponse = new CurrencyResponse(CURRENCY_ID, "INR", "indian currency");


    doReturn(currencyMaster).when(this.webModelConverterService).convert(currencyMaster,
        CurrencyResponse.class);
  }

  @After
  public void tearDown() {
    Mockito.verifyNoMoreInteractions(this.webModelConverterService, this.currencyService);
  }

  @Test
  public void findById_Test() throws Exception {
    Mockito.when(this.currencyService.findById(CURRENCY_ID)).thenReturn(currencyMaster);
    Mockito.when(this.webModelConverterService.convert(currencyMaster, CurrencyResponse.class))
        .thenReturn(currencyResponse);

    this.mockMvc
        .perform(get(ApiPath.CURRENCY_MASTER + ApiPath.FIND_BY_ID)
            .accept(MediaType.APPLICATION_JSON).param("currencyId", CURRENCY_ID.toString()))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));

    Mockito.verify(this.currencyService).findById(CURRENCY_ID);
    Mockito.verify(this.webModelConverterService).convert(currencyMaster, CurrencyResponse.class);
  }

  @Test
  public void findById_BusinessException_Test() throws Exception {
    Mockito.when(this.currencyService.findById(TestUtils.ID))
        .thenThrow(new BusinessException(Error.CURRENCY_NOT_FOUND));

    this.mockMvc
        .perform(get(ApiPath.CURRENCY_MASTER + ApiPath.FIND_BY_ID)
            .accept(MediaType.APPLICATION_JSON).param("currencyId", CURRENCY_ID.toString()))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(false)));

    Mockito.verify(this.currencyService).findById(TestUtils.ID);
  }

  @Test
  public void findById_convert_Exception_Test() throws Exception {
    Mockito.when(this.currencyService.findById(TestUtils.ID)).thenReturn(currencyMaster);

    Mockito.when(this.webModelConverterService.convert(currencyMaster, CurrencyResponse.class))
        .thenThrow(Exception.class);
    this.mockMvc
        .perform(get(ApiPath.CURRENCY_MASTER + ApiPath.FIND_BY_ID)
            .accept(MediaType.APPLICATION_JSON).param("currencyId", TestUtils.ID.toString()))
        .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(false)));

    Mockito.verify(this.currencyService).findById(TestUtils.ID);
    Mockito.verify(this.webModelConverterService).convert(currencyMaster, CurrencyResponse.class);

  }
}
