package com.supersong.graduation.controller;

import com.supersong.graduation.bean.BlogRoll;
import com.supersong.graduation.service.BlogRollService;
import com.supersong.graduation.utils.Msg;
import com.supersong.graduation.utils.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/blogRoll")
public class BlogRollController {

    @Autowired
    private BlogRollService blogRollService;

    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    @ResponseBody
    public Msg addBlogRollList(@RequestBody RequestVo<List<BlogRoll>> list) {
        if (blogRollService.addList(list.getRequest()) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(){
        return Msg.success().addData("BlogRoll",blogRollService.getAll());
    }
}
