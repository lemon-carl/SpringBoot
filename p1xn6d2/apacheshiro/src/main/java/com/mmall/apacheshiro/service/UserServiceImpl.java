package com.mmall.apacheshiro.service;

import com.mmall.apacheshiro.mapper.UserMapper;
import com.mmall.apacheshiro.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName : UserServiceImpl Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 15:44 @Description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

  @Override
  public User findByUsername(String username) {
    return userMapper.findByUsername(username);
  }
}
