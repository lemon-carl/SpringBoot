package com.mmall.beans;

import lombok.*;

import java.util.Set;

/**
 * @Auth CarlLing
 * @Date 2019/1/21 17:24
 * @Version 1.0
 * @Desc
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;

}
