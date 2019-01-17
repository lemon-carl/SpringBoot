package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName : LevelUtil
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-13 18:26
 * @Description : 计算level各个层级
 */

/**
 *  树形结构计算工具类
 */
public class LevelUtil {


    public  static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    /**
     * 计算部门的层级
     * @param parentLevel 父级的上一层
     * @param parentId 父级的id
     * @return
     */
    // 0
    // 0.1
    // 0.1.2
    // 0.1.3
    // 0.4
    public static String calculateLevel(String parentLevel,int parentId){
        //不传就是首层
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        } else {
          return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
