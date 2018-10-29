package com.coviam.finance.backend.api.web.controller;


import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviam.backend.service.FinanceService;
import com.coviam.finance.backend.api.web.helper.api.WebModelConverterService;
import com.coviam.finance.backend.web.model.BaseRestResponse;
import com.coviam.finance.backend.web.model.response.FinanceResponse;
import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;


@RestController
@RequestMapping(value = "/financeController")
public class FinanceController {

  @Autowired
  private FinanceService financeService;

  @Autowired
  private WebModelConverterService webModelConverterService;

  @RequestMapping(value = "/print", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> print(@RequestParam String name) {
    System.out.println("Hello" + name);

    return new ResponseEntity<Map<String, String>>(Collections.singletonMap("response", name),
        HttpStatus.OK);
  }

  @RequestMapping(value = "/findById", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse findByCurrencyId(@RequestParam Long currencyId) {
    FinanceResponse financeResponse;
    try {
      financeResponse = this.webModelConverterService
          .convert(this.financeService.findById(currencyId), FinanceResponse.class);
    } catch (BusinessException e) {
      return new BaseRestResponse(false, e.getErrorCode(), e.getMessage(), null);
    } catch (Exception e) {
      return new BaseRestResponse(false, Error.SYSTEM_ERROR.getCode(),
          Error.SYSTEM_ERROR.getMessage(), null);
    }
    return new BaseRestResponse(true, financeResponse);
  }
}
