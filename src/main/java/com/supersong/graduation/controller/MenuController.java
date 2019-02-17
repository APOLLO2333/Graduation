package com.supersong.graduation.controller;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.service.MenuService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Msg addMenu(Menu menu) {
        if (menu.getMenuName().isEmpty()) {
            return Msg.fail().addMessage("权限名不能为空!");
        }
        if (menu.getMenuLevel().isEmpty()) {
            return Msg.fail().addMessage("权限等级不能为空!");
        }
        if (menu.getMenuUrl().isEmpty()) {
            return Msg.fail().addMessage("权限跳转路径不能为空!");
        }
        if (menuService.checkName(menu.getMenuName(),null)){
            return Msg.fail().addMessage("存在相同权限名!");
        }
        if (menuService.addMenu(menu) == 1) {
            return Msg.success().addMessage("添加成功!");
        } else {
            return Msg.fail().addMessage("添加失败!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateMenu(Menu menu) {
        if (menu.getId().isEmpty()) {
            return Msg.fail().addMessage("操作失败!");
        }
        if (menuService.updateMenu(menu) == 0) {
            return Msg.fail().addMessage("操作失败!");
        } else {
            return Msg.success().addMessage("操作成功!");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteMenu(String id) {
        if (id.isEmpty()){
            return Msg.fail().addMessage("操作失败!");
        }
        if (menuService.deleteMenuById(id)==0){
            return Msg.fail().addMessage("操作失败!");
        }else {
            return Msg.success().addMessage("操作成功!");
        }
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public Msg getAllMenu(){
        List<Menu> list = menuService.getAll();
        if (list!=null){
            return Msg.success().addData("menuList",list).addMessage("操作成功!");
        }else {
            return Msg.fail().addMessage("没有数据");
        }
    }
    @RequestMapping(value = "/checkName",method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(String menuName,String id){
        if (menuService.checkName(menuName,id)){
            return Msg.fail().addMessage("该名字已经被使用!");
        }else {
            return Msg.success().addMessage("该名字可以使用!");
        }
    }
}
