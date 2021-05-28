package com.lemon.server.utils;

import com.lemon.server.model.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具类
 *
 * @author: Lemon
 * @Date : 2021/5/28 17:56
 */
public class AdminUtils {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
