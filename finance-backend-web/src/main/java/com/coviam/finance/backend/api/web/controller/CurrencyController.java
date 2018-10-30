package com.coviam.finance.backend.api.web.controller;


import com.coviam.finance.backend.api.web.helper.api.WebModelConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviam.finance.backend.service.CurrencyService;
import com.coviam.finance.backend.web.model.BaseRestResponse;
import com.coviam.finance.backend.web.model.response.CurrencyResponse;
import com.coviam.finance.master.model.constants.ApiPath;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;


@RestController
@RequestMapping(value = ApiPath.CURRENCY_MASTER)
public class CurrencyController {

  @Autowired
  private CurrencyService currencyService;

  @Autowired
  private WebModelConverterService webModelConverterService;

  @RequestMapping(value = ApiPath.FIND_BY_ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse findByCurrencyId(@RequestParam Long currencyId) {
    CurrencyResponse currencyResponse;
    try {
      currencyResponse = this.webModelConverterService
          .convert(this.currencyService.findById(currencyId), CurrencyResponse.class);
    } catch (BusinessException e) {
      return new BaseRestResponse(false, e.getErrorCode(), e.getMessage(), null);
    } catch (Exception e) {
      return new BaseRestResponse(false, Error.SYSTEM_ERROR.getCode(),
          Error.SYSTEM_ERROR.getMessage(), null);
    }
    return new BaseRestResponse(true, currencyResponse);
  }
}
