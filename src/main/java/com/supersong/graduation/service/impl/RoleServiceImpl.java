package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.bean.Role;
import com.supersong.graduation.dao.MenuDao;
import com.supersong.graduation.dao.RoleDao;
import com.supersong.graduation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public int addRole(Role role) {
        try {
            role.setId(UUID.randomUUID().toString());
            role.setCreateTime(new Date());
            roleDao.addRole(role);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRole(String roleId) {
        try {
           roleDao.deleteRoleMenu(roleId);
           roleDao.deleteRole(roleId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int update(Role role) {
        if (role.getId() == null || "".equals(role.getId())) {
            return 0;
        }
        try {
            roleDao.updateRole(role);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public boolean checkName(String roleName,String roleId) {
        return roleDao.checkName(roleName,roleId)>0;
    }

    @Override
    public List<Role> getAll() {
        List<Role> list = roleDao.getAll();
        return list.size() > 0 ? list : null;
    }

    @Override
    public boolean addMenuToRole(String roleId, List<Menu> list) {
        try {
            roleDao.deleteRoleMenu(roleId);
            roleDao.addMenuToRole(roleId,list);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }
}
