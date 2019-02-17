package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.User;
import com.supersong.graduation.service.UserService;
import com.supersong.graduation.utils.FileUtils;
import com.supersong.graduation.utils.Msg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Msg register(@RequestBody User user) {
        if (user.getEmail().isEmpty()) {
            return Msg.fail().addMessage("邮箱不能为空!");
        }
        if (user.getUserName().isEmpty()) {
            return Msg.fail().addMessage("账户名不能为空!");
        }
        if (user.getRealName().isEmpty()) {
            return Msg.fail().addMessage("用户名不能为空!");
        }
        if (user.getPassword().isEmpty()) {
            return Msg.fail().addMessage("密码不能为空!");
        }
        if (userService.addUser(user) == 1) {
            return Msg.success().addMessage("注册成功!");
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteUser(String userId) {
        if (userId.isEmpty()) {
            return Msg.fail();
        }
        if (userService.deleteUser(userId) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(@RequestBody User user) {
        if (user.getUserName().isEmpty()){
            return Msg.fail();
        }
        if (user.getId().isEmpty()) {
            return Msg.fail();
        }
        if (user.getPhone().isEmpty()){
            return Msg.fail();
        }
        if (user.getEmail().isEmpty()){
            return Msg.fail();
        }
        if (userService.updateUser(user) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/checkName", method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(String userName, String userId) {
        if (userService.checkName(userName, userId)) {
            System.out.println(userService.checkName(userName, userId));
            return Msg.fail().addMessage("存在相同用户!");
        }
        return Msg.success().addMessage("可以使用用户名!");
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public Msg login(@RequestBody String loginMsg, String password, String type) {
//        User user = userService.login(loginMsg, password, type);
//        if (user == null) {
//            return Msg.fail().addMessage("用户名和密码不匹配!");
//        }
//        return Msg.success();
//    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public Msg getMenu(@Param("token") String userId) {
        return null;
    }

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadImg(@Param("file") MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = String.valueOf(System.currentTimeMillis()) + "." + contentType.split("/")[1];
        try {
            FileUtils.uploadFile(file.getBytes(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.success().addData("url",fileName);
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public Msg checkEmail(String email, String userId) {
        if (userService.checkEmail(email, userId)) {
            return Msg.fail().addMessage("已注册邮箱!");
        }
        return Msg.success();
    }

    @RequestMapping(value = "/checkPhone", method = RequestMethod.GET)
    @ResponseBody
    public Msg checkPhone(String phone, String userId) {
        if (userService.checkPhone(phone, userId)) {
            return Msg.fail().addMessage("已存在号码!");
        }
        return Msg.success();
    }

    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ResponseBody
    public Msg queryUser(String query, String type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.queryUser(query, type);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public Msg error(){
        return Msg.fail().addMessage("未授权");
    }

    @RequestMapping(value = "/getUserByToken",method = RequestMethod.GET)
    @ResponseBody
    public Msg getUserByToken(String token){
        User user = (User) redisTemplate.opsForValue().get(token);
        if (null == user){
            return Msg.fail().addMessage("token已过期!");
        }
        return Msg.success().addData("user",user);
    }
}
