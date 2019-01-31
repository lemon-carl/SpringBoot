package com.mmall.util;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : StringUtil
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-31 23:50
 * @Description :
 */
public class StringUtil {

  public static List<Integer> splitToListInt(String str) {
    List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
    return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
  }
}
