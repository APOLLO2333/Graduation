package com.supersong.graduation.dao;

import com.supersong.graduation.bean.SystemSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemSettingDao {

    void add(SystemSetting systemSetting);

    List<SystemSetting> get();
}
