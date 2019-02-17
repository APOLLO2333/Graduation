package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.bean.Role;
import com.supersong.graduation.service.RoleService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg addRole(@RequestBody Role role) {
        if (role.getRoleName().isEmpty()) {
            return Msg.fail().addMessage("角色名不能为空!");
        }
        if (roleService.addRole(role) == 1) {
            return Msg.success().addMessage("添加成功!");
        }
        return Msg.fail().addMessage("添加失败!");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteRole(String id) {
        if (id.isEmpty()) {
            return Msg.fail().addMessage("操作失败!");
        }
        if (roleService.deleteRole(id) == 1) {
            return Msg.success().addMessage("删除成功!");
        }
        return Msg.fail().addMessage("操作失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(@RequestBody Role role) {
        if (role.getId().isEmpty()) {
            return Msg.fail().addMessage("操作失败!");
        }
        if (roleService.update(role) == 1) {
            return Msg.success().addMessage("更新成功!");
        }
        return Msg.fail().addMessage("操作失败!");
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg getRole(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleService.getAll();
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo).addMessage("操作成功!");
    }

    @RequestMapping(value = "/addMenuToRole", method = RequestMethod.POST)
    @ResponseBody
    public Msg addMenuToRole(@RequestBody String roleId, List<Menu> list) {
        if (null == roleId || roleId.isEmpty()) {
            return Msg.fail().addMessage("操作失败!");
        }
        if (roleService.addMenuToRole(roleId, list)) {
            return Msg.success().addMessage("操作成功!");
        }
        return Msg.fail().addMessage("操作失败!");
    }

    @RequestMapping(value = "/checkName", method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(String roleName, String roleId) {
        if (roleService.checkName(roleName, roleId)) {
            return Msg.success().addMessage("存在相同名字!");
        }
        return Msg.success().addMessage("可以使用名字!");
    }
}
