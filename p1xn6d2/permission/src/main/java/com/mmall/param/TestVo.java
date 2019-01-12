package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @ClassName : TestVo
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-12 1:05
 * @Description :
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull
    private Integer id;
}
