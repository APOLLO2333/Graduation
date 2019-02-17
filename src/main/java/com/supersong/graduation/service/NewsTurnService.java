package com.supersong.graduation.service;

import com.supersong.graduation.bean.NewTurn;

import java.util.List;

public interface NewsTurnService {

    int add(NewTurn newTurn);
    int addList(List<NewTurn> list);
    int update(NewTurn newTurn);
    int delete(String id);
    List<NewTurn> getAll();
    int checkName(String id,String newsTitle);
}
