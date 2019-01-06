package com.mmall.springsecuity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ClassName : MyUserService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-01 22:19
 * @Description :
 */

@Component
public class MyUserService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
