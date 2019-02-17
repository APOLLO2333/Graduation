package com.supersong.graduation.dao;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.bean.Role;
import com.supersong.graduation.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    @Insert("insert into t_user(id,user_name,real_name) values('1','2','3')")
    int add();
    int getUserCount();
    void addUser(User user);
    void deleteUser(String userId);
    void deleteUserRole(String userId);
    void updateUser(User user);
    int checkName(@Param("userName") String userName, @Param("userId") String userId);
    List<User> getAll();
    List<User> getUserByName(String userName);
    List<User> getUserByEmail(String email);

    List<User> login(String email);
    List<User> loginByPhone(String phone);
    int checkPhone(@Param("phone") String phone,@Param("userId")String userId);
    int checkEmail(@Param("email") String email,@Param("userId")String userId);
    List<User> queryUser(@Param("map")Map map);

    List<Menu> getMenuByUserId(String userId);

    List<Role> getRoleByUserId(String userId);
}
