package com.supersong.graduation.dao;

import com.supersong.graduation.bean.NewsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsTypeDao {
    void add(NewsType newsType);
    void delete(String id);
    void update(NewsType newsType);
    List<NewsType> getAll();
    List<NewsType> query(String query);
    int checkName(@Param("typeName") String typeName,@Param("id") String id);
}
