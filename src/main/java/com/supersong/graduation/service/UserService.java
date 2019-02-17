package com.supersong.graduation.service;

import com.supersong.graduation.bean.User;


import java.util.List;


public interface UserService {
    int addUser(User user);

    int deleteUser(String userId);

    int updateUser(User user);

    boolean checkName(String userName, String userId);

    List<User> getAll();

    User login(String userName, String password, String type);

    boolean checkEmail(String email, String userId);

    boolean checkPhone(String phone, String userId);

    List<User> queryUser(String query, String type);


}
