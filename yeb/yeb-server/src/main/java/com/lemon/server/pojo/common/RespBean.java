package com.lemon.server.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @author: Lemon
 * @Date : 2021/4/11 18:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object obj;


    /**
     * 成功返回结果
     *
     * @param message
     * @return
     */
    public static RespBean ok(String message) {
        return new RespBean(200, message, null);
    }

    /**
     * 成功返回结果,并返回数据
     *
     * @param message
     * @param obj
     * @return
     */
    public static RespBean ok(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @return
     */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回结果，并返回数据
     *
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }


}
