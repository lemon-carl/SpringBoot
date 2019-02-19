package com.mmall.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Auth CarlLing
 * @Date 2019/1/21 14:50
 * @Version 1.0
 * @Desc
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {


    private List<T> data = Lists.newArrayList();

    private int total = 0;
}
