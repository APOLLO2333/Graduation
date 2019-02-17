package com.supersong.graduation.dao;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {
    void addRole(Role role);

    void deleteRole(String roleId);

    void updateRole(Role role);

    List<Role> getAll();

    int checkName(@Param("roleName") String roleName,@Param("roleId") String roleId);

    void deleteRoleMenu(String roleId);

    void addMenuToRole(@Param("roleId") String roleId, @Param("list") List<Menu> list);
}
