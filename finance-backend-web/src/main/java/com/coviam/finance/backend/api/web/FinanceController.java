package com.coviam.finance.backend.api.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/financeController")
public class FinanceController {

  @RequestMapping(value = "/print", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> print(@RequestParam String name) {
    System.out.println("Hello" + name);
    
    return new ResponseEntity<Map<String, String>>(Collections.singletonMap("response", name),
        HttpStatus.OK);
  }
}
