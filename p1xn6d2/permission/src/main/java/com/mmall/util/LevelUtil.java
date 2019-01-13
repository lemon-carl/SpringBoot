package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName : LevelUtil
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-13 18:26
 * @Description : 计算level各个层级
 */

public class LevelUtil {

    private final static String SEPARATOR = ".";

    public static final String ROOT = "0";

    // 0
    // 0.1
    // 0.1.2
    // 0.1.3
    // 0.4
    public static String calculateLevel(String parentLevel,int parentId){
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        } else {
          return   StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
