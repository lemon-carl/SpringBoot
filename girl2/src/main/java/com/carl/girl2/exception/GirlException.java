package com.carl.girl2.exception;

import com.carl.girl2.enums.ResultEnum;

/**
 * @ClassName : GirlException Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2018-08-27 23:49 @Description :
 */
public class GirlException extends RuntimeException {

  private Integer code;

  public GirlException(ResultEnum resultEnum) {
    super(resultEnum.getMsg());
    this.code = resultEnum.getCode();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
