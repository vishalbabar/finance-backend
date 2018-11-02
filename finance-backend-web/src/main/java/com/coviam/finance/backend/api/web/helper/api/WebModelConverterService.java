package com.coviam.finance.backend.api.web.helper.api;

import java.util.List;

public interface WebModelConverterService {

  <M, T> T convert(M entity, Class<T> clasz) throws Exception;

  <M,T>List<T> convert(List<M> entities,Class<T> clasz) throws Exception;

}
