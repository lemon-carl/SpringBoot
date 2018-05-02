package com.hr.girl;/**
 * packageName :com.hr.girl
 * Created with IDEA
 * author:CarlLing
 * Date:2018/5/2
 * Time:20:32
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Name : GirlProperties
 * @Author :  LH
 * @CreateDate : 2018-05-02 20:32
 * @Description : 
 */
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
