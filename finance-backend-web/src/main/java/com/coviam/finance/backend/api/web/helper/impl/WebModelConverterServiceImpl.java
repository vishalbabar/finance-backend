package com.coviam.finance.backend.api.web.helper.impl;

import com.coviam.finance.backend.api.web.helper.api.WebModelConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

@Service
public class WebModelConverterServiceImpl implements WebModelConverterService {

  @Autowired
  private Mapper mapper;

  @Override
  public <M, T> T convert(M entity, Class<T> clasz) throws Exception {
    T response = this.mapper.map(entity, clasz);
    return response;
  }
}
