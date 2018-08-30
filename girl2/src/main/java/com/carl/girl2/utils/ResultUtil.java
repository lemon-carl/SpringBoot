package com.carl.girl2.utils;

import com.carl.girl2.entity.Result;

/**
 * @ClassName : ResultUtil Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2018-08-27 23:14 @Description :
 */
public class ResultUtil {

  public static Result success(Object object) {
    Result result = new Result();
    result.setCode(0);
    result.setMsg("成功了");
    result.setData(object);
    return result;
  }

  public static Result success() {
    return success(null);
  }

  public static Result error(Integer code, String msg) {
    Result result = new Result();
    result.setCode(code);
    result.setMsg(msg);
    return result;
  }
}
