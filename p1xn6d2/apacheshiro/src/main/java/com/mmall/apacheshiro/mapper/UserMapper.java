package com.mmall.apacheshiro.mapper;

import com.mmall.apacheshiro.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @InterFaceName : UserMapper Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 15:41 @Description : 映射接口
 */
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
