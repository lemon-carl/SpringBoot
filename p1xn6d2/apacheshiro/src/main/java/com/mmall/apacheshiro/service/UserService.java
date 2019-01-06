package com.mmall.apacheshiro.service;

import com.mmall.apacheshiro.model.User;

/**
 * @InterFaceName : UserService Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 15:43 @Description :
 */
public interface UserService {

  User findByUsername(String username);
}
