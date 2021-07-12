package com.lemon.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author: Lemon
 * @Date : 2021/7/11 13:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    /**
     * 发送人
     */
    private String from;
    /**
     * 接收人
     */
    private String to;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 发送时间
     */
    private LocalDateTime date;
    /**
     * 发送人昵称
     **/
    private String fromNickName;
}
