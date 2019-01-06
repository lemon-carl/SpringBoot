package com.mmall.springsecuity.utils;

//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName : MyPasswordEncoder
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-01 22:26
 * @Description : 自定义加密
 */
public class MyPasswordEncoder implements PasswordEncoder{

    private static final String SALT="123456";

    @Override
    public String encode(CharSequence rawPassword) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return BCrypt.hashpw(rawPassword.toString(), SALT);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
       // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return BCrypt.checkpw(rawPassword.toString(),encodedPassword);
    }
}
