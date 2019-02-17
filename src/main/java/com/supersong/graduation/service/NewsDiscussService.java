package com.supersong.graduation.service;

import com.supersong.graduation.bean.DiscussFilter;
import com.supersong.graduation.bean.NewsDiscuss;

import java.util.List;

public interface NewsDiscussService {

    List<NewsDiscuss> getDiscussByNewsId(String id);

    int deleteNewsDiscuss(String id);

    int addNewsDiscuss(NewsDiscuss newsDiscuss);

    int addDiscussFilter(DiscussFilter discussFilter);

    DiscussFilter getDiscussFilter();

}
