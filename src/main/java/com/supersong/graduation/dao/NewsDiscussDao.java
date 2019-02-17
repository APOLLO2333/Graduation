package com.supersong.graduation.dao;

import com.supersong.graduation.bean.DiscussFilter;
import com.supersong.graduation.bean.NewsDiscuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsDiscussDao {

    List<NewsDiscuss> getDiscussByNewsId(String id);

    void deleteNewsDiscuss(String id);

    void addNewsDiscuss(NewsDiscuss newsDiscuss);

    void addDiscussFilter(DiscussFilter discussFilter);

    List<DiscussFilter> getDiscussFilter();

}
