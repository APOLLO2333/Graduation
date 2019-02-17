package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.NewsType;
import com.supersong.graduation.service.NewsTypeService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/newsType")
public class NewsTypeController {

    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestBody NewsType newsType) {
        if (newsType.getTypeName().isEmpty()) {
            return Msg.fail();
        }
        if (newsTypeService.add(newsType) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(String id){
        if (id.isEmpty()){
            return Msg.fail();
        }
        if (newsTypeService.delete(id)==1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(@RequestBody NewsType newsType){
        if (newsType.getId().isEmpty()){
            return Msg.fail();
        }
        if (newsTypeService.update(newsType)==1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<NewsType> list = newsTypeService.getAll();
        PageInfo<NewsType> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo",pageInfo);
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Msg query(int pageNum, int pageSize,String query){
        PageHelper.startPage(pageNum,pageSize);
        List<NewsType> list = newsTypeService.query(query);
        PageInfo<NewsType> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo",pageInfo);
    }
}
