package com.supersong.graduation.dao;

import com.supersong.graduation.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    void addMenu(Menu menu);

    void deleteMenuById(String id);

    void deleteRoleMenu(String menuId);

    void updateMenu(Menu menu);

    List<Menu> getAll();

    int checkName(@Param("menuName") String menuName, @Param("menuId") String menuId);

    List<Menu> getFirstMenuByUserId(String userId);

    List<Menu> getSecondMenuByUserId(String userId);
}
