package com.supersong.graduation.controller;

import com.supersong.graduation.bean.NewTurn;
import com.supersong.graduation.service.NewsTurnService;
import com.supersong.graduation.utils.Msg;
import com.supersong.graduation.utils.RequestVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/newsTurn")
public class NewsTurnController {
    @Autowired
    private NewsTurnService newsTurnService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNewsTurn(@RequestBody NewTurn newTurn) {
        if (newTurn.getImgUrl() == null || "".equals(newTurn.getImgUrl())) {
            return Msg.fail();
        }
        if (newTurn.getNewsTitle() == null || "".equals(newTurn.getNewsTitle())) {
            return Msg.fail();
        }
        if (newTurn.getrUrl() == null || "".equals(newTurn.getrUrl())) {
            return Msg.fail();
        }

        if (newsTurnService.add(newTurn) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNewsTurnList(@RequestBody RequestVo<List<NewTurn>>  list) {
        if (newsTurnService.addList(list.getRequest()) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateNewsTurn(@RequestBody NewTurn newTurn){
        if (newsTurnService.update(newTurn)==1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteNewsTurn(String id){
        if (newsTurnService.delete(id)==1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(){
      return Msg.success().addData("turn",newsTurnService.getAll());
    }

    @RequestMapping(value = "checkName",method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(String id, String newsTitle){
        if (newsTurnService.checkName(id,newsTitle)==1){
            return Msg.fail().addMessage("已经存在同名");
        }else {
            return Msg.success();
        }
    }
}
