package com.supersong.graduation.dao;

import com.supersong.graduation.bean.BlogRoll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogRollDao {

    void addList(@Param("list") List<BlogRoll> list);

    void deleteAll();

    List<BlogRoll> getAll();
}
