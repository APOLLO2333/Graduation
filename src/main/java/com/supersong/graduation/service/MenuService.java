package com.supersong.graduation.service;

import com.supersong.graduation.bean.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    int addMenu(Menu menu);

    int deleteMenuById(String id);

    int updateMenu(Menu menu);

    List<Menu> getAll();

    boolean checkName(String menuName, String menuId);

    Map<String, List<Menu>> getMenu(String userId);
}
