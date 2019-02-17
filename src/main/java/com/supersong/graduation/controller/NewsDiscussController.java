package com.supersong.graduation.controller;

import com.supersong.graduation.bean.DiscussFilter;
import com.supersong.graduation.bean.NewsDiscuss;
import com.supersong.graduation.service.NewsDiscussService;
import com.supersong.graduation.service.NewsService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/newsDiscuss")
public class NewsDiscussController {

    @Autowired
    private NewsDiscussService newsDiscussService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/getNewsDiscussByNewsId", method = RequestMethod.GET)
    @ResponseBody
    public Msg getNewsDiscussByNewsId(String newsId) {
        if (null == newsId || newsId.isEmpty()) {
            return Msg.fail();
        }
        List<NewsDiscuss> list = newsDiscussService.getDiscussByNewsId(newsId);
        return Msg.success().addData("list", list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNewsDiscuss(@RequestBody NewsDiscuss newsDiscuss) {
        if (null == newsDiscuss) {
            return Msg.fail();
        }
        if (!newsService.checkNewsCanDiscuss(newsDiscuss.getNewsId())) {
            return Msg.fail().addMessage("不允许评论！");
        }
        if (1 == newsDiscussService.addNewsDiscuss(newsDiscuss)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteNewsDiscuss(String id) {
        if (null == id) {
            return Msg.fail();
        }
        if (1 == newsDiscussService.deleteNewsDiscuss(id)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/saveFilter", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveFilter(@RequestBody DiscussFilter discussFilter) {
        if (null == discussFilter.getKeyword() || null == discussFilter.getReplaceWord()){
            return Msg.fail().addMessage("参数为空!");
        }
        if (1 == newsDiscussService.addDiscussFilter(discussFilter)){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getFilter",method = RequestMethod.GET)
    @ResponseBody
    public Msg getFilter(){
        return Msg.success().addData("discussFilter",newsDiscussService.getDiscussFilter());
    }

}
