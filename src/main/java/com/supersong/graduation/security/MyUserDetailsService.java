package com.supersong.graduation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.supersong.graduation.bean.User ;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        user = (User) redisTemplate.opsForValue().get(s.replace("-","").trim());
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
    /**
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     */

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = userService.getUserByName(username);
//        if (null == sysUser) {
//            throw new UsernameNotFoundException(username);
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (SysRole role : sysUser.getRoleList()) {
//            for (SysPermission permission : role.getPermissionList()) {
//                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
//            }
//        }
//
//        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
//    }
}
