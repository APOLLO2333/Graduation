package com.supersong.graduation.dao;

import com.supersong.graduation.bean.News;
import com.supersong.graduation.bean.NewsSupport;
import com.supersong.graduation.bean.NewsVisitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {
    List<News> getAll();

    void addNews(News news);

    void updateNews(News news);

    void deleteNews(String id);

    News getNewsById(String id);

    int getViewCount(@Param("startTime") long startTime, @Param("endTime") long endTime, @Param("newsId") String newsId);

    int getLikeCount(@Param("startTime") long startTime, @Param("endTime") long endTime, @Param("newsId") String newsId);

    int getDislikeCount(@Param("startTime") long startTime, @Param("endTime") long endTime, @Param("newsId") String newsId);

    void addNewsVisitor(NewsVisitor newsVisitor);

    void addNewsSupport(NewsSupport newsSupport);

    void updateNewsSupport(NewsSupport newsSupport);

    int checkNewsCanDiscuss(String id);

    int checkUserIsSupport(@Param("userId") String userId,@Param("newsId") String newsId);

    List<News> getNewsByQuery(@Param("map") Map map);

    List<News> getNewsImportant();

    List<News> getNewsNotImportant();
}
