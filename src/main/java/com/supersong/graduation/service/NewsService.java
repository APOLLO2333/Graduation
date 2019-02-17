package com.supersong.graduation.service;

import com.supersong.graduation.bean.News;
import com.supersong.graduation.bean.NewsSupport;
import com.supersong.graduation.bean.NewsVisitor;

import java.util.List;

public interface NewsService {
    List<News> getAll();

    int addNews(News news);

    int updateNews(News news);

    int deleteNews(String id);

    News getNewsById(String id);

    int getViewCount(long startTime, long endTime, String newsId);

    int getLikeCount(long startTime, long endTime, String newsId);

    int getDislikeCount(long startTime, long endTime, String newsId);

    int addNewsVisitor(NewsVisitor newsVisitor);

    int addNewsSupport(NewsSupport newsSupport);

    int updateNewsSupport(NewsSupport newsSupport);

    boolean checkNewsCanDiscuss(String id);

    boolean checkUserIsSupport(String userId,String newsId);

    List<News> getNewsByQuery(String author,String title,Long startTime,Long endTime,String type);

    List<News> getNewsImportant();

    List<News> getNewsNotImportant();
}
