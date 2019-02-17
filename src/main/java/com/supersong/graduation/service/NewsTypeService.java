package com.supersong.graduation.service;

import com.supersong.graduation.bean.NewsType;

import java.util.List;

public interface NewsTypeService {
    int add(NewsType newsType);
    int delete(String id);
    int update(NewsType newsType);
    List<NewsType> getAll();
    List<NewsType> query(String query);
    int checkName(String typeName,String id);
}
