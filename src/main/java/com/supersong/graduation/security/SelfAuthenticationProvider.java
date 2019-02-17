package com.supersong.graduation.security;

import com.supersong.graduation.bean.User;
import com.supersong.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；
        String userName = principal.split("_")[0];
        String type = principal.split("_")[1];
        User user = userService.login(userName, password, type);
        String token = user.getId().replace("-", "").trim();
        token = changeToken(token);
        redisTemplate.opsForValue().set(token, user);
        UserDetails userInfo = myUserDetailsService.loadUserByUsername(user.getId());

//        if (!userInfo.getPassword().equals(password)) {
//            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
//        }

        return new UsernamePasswordAuthenticationToken(token, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


    private String changeToken(String token) {
        int a[] = new int[32];
        char ch[] = new char[32];
        ch = token.toCharArray();
        long time = new Date().getTime();
        int hash = (int) (time % 10);
        for (int i = 0; i < 32; i++) {
            a[i] = (int) ch[i];

            a[i] = a[i] + hash;

            ch[i] = (char) a[i];
        }
        return new String(ch);
    }
}
