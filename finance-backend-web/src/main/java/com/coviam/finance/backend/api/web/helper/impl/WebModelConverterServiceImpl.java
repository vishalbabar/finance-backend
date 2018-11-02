package com.coviam.finance.backend.api.web.helper.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviam.finance.backend.api.web.helper.api.WebModelConverterService;

@Service
public class WebModelConverterServiceImpl implements WebModelConverterService {

  @Autowired
  private Mapper mapper;

  @Override
  public <M, T> T convert(M entity, Class<T> clasz) throws Exception {
    T response = this.mapper.map(entity, clasz);
    return response;
  }

  @Override
  public <M, T> List<T> convert(List<M> entities, Class<T> clasz) throws Exception {
    List<T> responseList = new ArrayList<>();
    for (M entity : entities) {
      T response = this.mapper.map(entity, clasz);
      responseList.add(response);
    }
    return responseList;
  }
}
