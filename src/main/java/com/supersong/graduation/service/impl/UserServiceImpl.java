package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.Role;
import org.apache.commons.codec.digest.DigestUtils;
import com.supersong.graduation.bean.User;
import com.supersong.graduation.dao.UserDao;
import com.supersong.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        try {
            user.setId(UUID.randomUUID().toString());
            user.setCreateDate(new Date());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            user.setStatus(1);
            if (user.getRealName().isEmpty()) {
                user.setRealName(user.getUserName());
            }
            userDao.addUser(user);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUser(String userId) {
        try {
            userDao.deleteUserRole(userId);
            userDao.deleteUser(userId);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUser(User user) {
        try {
            if (!user.getPassword().isEmpty()) {
                user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            }
            userDao.updateUser(user);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean checkName(String userName, String userId) {
        return userDao.checkName(userName, userId) > 0;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String loginMsg, String password, String type) {
        List<User> list = new ArrayList<>();
        if ("phone".equals(type)) {
            list = userDao.loginByPhone(loginMsg);
        }
        if ("email".equals(type)) {
            list = userDao.login(loginMsg);
        }
        if (list.size() == 0) {
            return null;
        }
        if (list.get(0).getPassword().equals(DigestUtils.md5Hex(password))) {
            User user = list.get(0);
            List<Role> roles = userDao.getRoleByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role:roles){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            user.setAuthorities(grantedAuthorities);
            user.setLoginTime(new Date());
            userDao.updateUser(user);
            return user;
        }
        return null;
    }

    @Override
    public boolean checkEmail(String email, String userId) {
        return userDao.checkEmail(email, userId) > 0;
    }

    @Override
    public boolean checkPhone(String phone, String userId) {
        return userDao.checkPhone(phone, userId) > 0;
    }

    @Override
    public List<User> queryUser(String query, String type) {
        Map<String, String> map = new HashMap<>();
        map.put(type, query);
        return userDao.queryUser(map);
    }

}
