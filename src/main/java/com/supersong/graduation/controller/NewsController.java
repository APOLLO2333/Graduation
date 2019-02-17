package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.News;
import com.supersong.graduation.bean.NewsSupport;
import com.supersong.graduation.bean.NewsVisitor;
import com.supersong.graduation.bean.User;
import com.supersong.graduation.service.NewsService;
import com.supersong.graduation.utils.FileUtils;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;



    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsService.getAll();
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNews(@RequestBody News news) {
        if (null == news.getTitle() || "".equals(news.getTitle())) {
            return Msg.fail().addMessage("标题不能为空!");
        }
        if (null == news.getFirstImg()) {
            return Msg.fail().addMessage("首页图不能为空!");
        }
        if (null == news.getAuthor() || "".equals(news.getAuthor())) {
            return Msg.fail().addMessage("作者不能为空!");
        }
        if (null == news.getContent() || "".equals(news.getContent())) {
            return Msg.fail().addMessage("内容不能为空!");
        }
        if (null == news.getContentHtml() || "".equals(news.getContentHtml())) {
            return Msg.fail().addMessage("内容不能为空!");
        }
        if (null == news.getType() || "".equals(news.getType())){
            return Msg.fail().addMessage("新闻类型不能为空!");
        }
        if (newsService.addNews(news) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateNews(@RequestBody News news) {
        if (null == news.getId()) {
            return Msg.fail();
        }
        if (newsService.updateNews(news) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteNews(String newsId) {
        if (null == newsId) {
            return Msg.fail();
        }
        if (newsService.deleteNews(newsId) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getNewsById", method = RequestMethod.GET)
    @ResponseBody
    public Msg getNewsById(String newsId) {
        if (null == newsId) {
            return Msg.fail();
        }
        return Msg.success().addData("news", newsService.getNewsById(newsId));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadImg(MultipartFile file) {
        try {
            FileUtils.storePic(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getViewCount", method = RequestMethod.GET)
    @ResponseBody
    public Msg getViewCount(long startTime, long endTime, String newsId) {
        try {
            Integer count = newsService.getViewCount(startTime, endTime, newsId);
            return Msg.success().addData("count", count);
        } catch (Exception e) {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/getLikeCount", method = RequestMethod.GET)
    @ResponseBody
    public Msg getLikeCount(long startTime, long endTime, String newsId) {
        try {
            Integer count = newsService.getLikeCount(startTime, endTime, newsId);
            return Msg.success().addData("count", count);
        } catch (Exception e) {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/getDislikeCount", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDislikeCount(long startTime, long endTime, String newsId) {
        try {
            Integer count = newsService.getDislikeCount(startTime, endTime, newsId);
            return Msg.success().addData("count", count);
        } catch (Exception e) {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/addNewsVisitor", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNewsVisitor(@RequestBody NewsVisitor newsVisitor) {
        if (null == newsVisitor) {
            return Msg.fail();
        }
        newsVisitor.setUserId(request.getHeader("Authorization"));
        if (1 == newsService.addNewsVisitor(newsVisitor)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/addNewsSupport", method = RequestMethod.POST)
    @ResponseBody
    public Msg addNewsSupport(@RequestBody NewsSupport newsSupport) {
        if (null == newsSupport || null == newsSupport.getNewsId() || null == newsSupport.getUserId()) {
            return Msg.fail();
        }
        if (newsService.checkUserIsSupport(newsSupport.getUserId(), newsSupport.getNewsId())) {
            return Msg.fail().addMessage("已经点赞或点踩！");
        }
        if (1 == newsService.addNewsSupport(newsSupport)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/updateNewsSupport", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateNewsSupport(@RequestBody NewsSupport newsSupport) {
        if (null == newsSupport || null == newsSupport.getId()) {
            return Msg.fail();
        }
        if (1 == newsService.updateNewsSupport(newsSupport)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getNewsByQuery", method = RequestMethod.GET)
    @ResponseBody
    public Msg getNewsByQuery(int pageNum, int pageSize, @RequestParam(required = false) String author, @RequestParam(required = false) String title,@RequestParam(required = false) String type,
                              @RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsService.getNewsByQuery(author, title, startTime, endTime,type);
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/getNewsImportant", method = RequestMethod.GET)
    @ResponseBody
    public Msg getNewsImportant(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsService.getNewsImportant();
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/getNewsNotImportant", method = RequestMethod.GET)
    @ResponseBody
    public Msg getNewsNotImportant(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> list = newsService.getNewsNotImportant();
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }
}
