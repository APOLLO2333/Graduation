package com.supersong.graduation.dao;

import com.supersong.graduation.bean.NewTurn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsTurnDao {
    void add(NewTurn newTurn);
    void update(NewTurn newTurn);
    List<NewTurn> getAll();
    void delete(String id);

    void deleteAll();

    int checkName(@Param("id") String id ,@Param("newsTitle") String newsTitle);

    void addList(@Param("list") List<NewTurn> list);
}
