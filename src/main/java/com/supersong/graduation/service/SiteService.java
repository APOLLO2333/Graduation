package com.supersong.graduation.service;

import com.supersong.graduation.bean.Site;

import java.util.List;

public interface SiteService {

    int add(Site site);

    int delete(String id);

    int update(Site site);

    List<Site> getAll();
}
