package com.supersong.graduation.dao;

import com.supersong.graduation.bean.WebLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebLogDao {

    void add(WebLog webLog);

    List<WebLog> getAll(@Param("startTime") Long startTime ,@Param("endTime") Long endTime);
}
