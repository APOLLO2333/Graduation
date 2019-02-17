package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.News;
import com.supersong.graduation.bean.NewsSupport;
import com.supersong.graduation.bean.NewsVisitor;
import com.supersong.graduation.bean.User;
import com.supersong.graduation.dao.NewsDao;
import com.supersong.graduation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<News> getAll() {
        return newsDao.getAll();
    }

    @Override
    public int addNews(News news) {
        try {
            news.setId(UUID.randomUUID().toString());
            news.setCreateTime(new Date());
            news.setModifyTime(news.getCreateTime());
            news.setLikeCount(0);
            news.setViewCount(0);
            if (null == news.getFirstImg()) {
                news.setFirstImg("1");
            }
            newsDao.addNews(news);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int updateNews(News news) {
        try {
            news.setModifyTime(new Date());
            newsDao.updateNews(news);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteNews(String id) {
        try {
            newsDao.deleteNews(id);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public News getNewsById(String id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public int getViewCount(long startTime, long endTime, String newsId) {
        return newsDao.getViewCount(startTime, endTime, newsId);
    }

    @Override
    public int getLikeCount(long startTime, long endTime, String newsId) {
        return newsDao.getLikeCount(startTime, endTime, newsId);
    }

    @Override
    public int getDislikeCount(long startTime, long endTime, String newsId) {
        return newsDao.getDislikeCount(startTime, endTime, newsId);
    }

    @Override
    public int addNewsVisitor(NewsVisitor newsVisitor) {
        try {
            User user = (User) redisTemplate.opsForValue().get(newsVisitor.getUserId());
            newsVisitor.setId(UUID.randomUUID().toString());
            newsVisitor.setModifyTime(new Date());
            newsVisitor.setUserId(user.getId());
            newsDao.addNewsVisitor(newsVisitor);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int addNewsSupport(NewsSupport newsSupport) {
        try {
            newsSupport.setCancel(0);
            newsSupport.setId(UUID.randomUUID().toString());
            newsSupport.setModifyTime(new Date());
            newsDao.addNewsSupport(newsSupport);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int updateNewsSupport(NewsSupport newsSupport) {
        try {
            newsDao.updateNewsSupport(newsSupport);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public boolean checkNewsCanDiscuss(String id) {
        //discuss = 1 可以评论
        return newsDao.checkNewsCanDiscuss(id) > 0;
    }

    @Override
    public boolean checkUserIsSupport(String userId, String newsId) {
        return newsDao.checkUserIsSupport(userId, newsId) > 0;
    }

    @Override
    public List<News> getNewsByQuery(String author, String title, Long startTime, Long endTime,String type) {
        Map<String, Object> map = new HashMap<>();
        if (null != author) {
            map.put("author", author);
        }
        if (null != title) {
            map.put("title", title);
        }
        if (null != type){
            map.put("type",type);
        }
        if (null != startTime && startTime != 0 && null != endTime && endTime != 0) {
            map.put("startTime", startTime);
            map.put("endTime", endTime);
        }
        return newsDao.getNewsByQuery(map);
    }

    @Override
    public List<News> getNewsImportant() {
        return newsDao.getNewsImportant();
    }

    @Override
    public List<News> getNewsNotImportant() {
        return newsDao.getNewsNotImportant();
    }
}
