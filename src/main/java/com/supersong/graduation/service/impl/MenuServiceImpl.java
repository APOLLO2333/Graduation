package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.dao.MenuDao;
import com.supersong.graduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public int addMenu(Menu menu) {
        try {
            menu.setId(UUID.randomUUID().toString());
            menu.setCreateTime(new Date());
            if (menu.getParentId() == null || "".equals(menu.getParentId())) {
                menu.setParentId(menu.getId());
            }
            menuDao.addMenu(menu);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int deleteMenuById(String id) {
        try {
            menuDao.deleteRoleMenu(id);
            menuDao.deleteMenuById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int updateMenu(Menu menu) {
        if (menu.getId() == null || "".equals(menu.getId())) {
            return 0;
        }
        try {
            menuDao.updateMenu(menu);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> list = menuDao.getAll();
        return list.size() > 0 ? list : null;
    }

    @Override
    public boolean checkName(String menuName, String menuId) {
        return menuDao.checkName(menuName, menuId) > 0;
    }

    @Override
    public Map<String, List<Menu>> getMenu(String userId) {
        Map<String, List<Menu>> map = new HashMap<>();
        List<Menu> first = menuDao.getFirstMenuByUserId(userId);
        List<Menu> second = menuDao.getSecondMenuByUserId(userId);
        for (Menu f : first) {
            List<Menu> result = new ArrayList<>();
            for (Menu s : second) {
                if (s.getParentId().equals(f.getId())) {
                    result.add(s);
                }
            }
            map.put(f.getId(), result);
        }
        return map;
    }
}
