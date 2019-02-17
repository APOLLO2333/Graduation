package com.supersong.graduation.service;

import com.supersong.graduation.bean.BlogRoll;

import java.util.List;

public interface BlogRollService {

    int addList(List<BlogRoll> list);


    List<BlogRoll> getAll();
}
