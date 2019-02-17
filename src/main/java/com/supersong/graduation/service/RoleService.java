package com.supersong.graduation.service;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.bean.Role;


import java.util.List;


public interface RoleService {
    int addRole(Role role);
    int deleteRole(String roleId);
    int update(Role role);
    boolean checkName(String roleName,String roleId);
    List<Role> getAll();
    boolean addMenuToRole(String roleId, List<Menu> list);
}
