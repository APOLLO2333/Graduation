package com.supersong.graduation.dao;

import com.supersong.graduation.bean.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteDao {

    void add(Site site);

    void delete(String id);

    void update(Site site);

    List<Site> getAll();
}
