package com.coviam.finance.backend.api.web.helper.api;

public interface WebModelConverterService {

  <M, T> T convert(M entity, Class<T> clasz) throws Exception;

}
