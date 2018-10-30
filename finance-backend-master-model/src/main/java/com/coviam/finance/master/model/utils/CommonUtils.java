package com.coviam.finance.master.model.utils;

import com.coviam.finance.master.model.enums.Error;
import com.coviam.finance.master.model.exceptions.BusinessException;

/**
 * Created by Vishal B on Oct, 2018
 */

public interface CommonUtils {

  static void checkError(boolean cond, Error error) {
    if (cond) {
      throw new BusinessException(error);
    }
  }
}
