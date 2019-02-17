package com.supersong.graduation.service;

import com.supersong.graduation.bean.WebLog;

import java.util.List;

public interface WebLogService {

    void add(WebLog webLog);

    List<WebLog> getAll(Long startTime,Long endTime);
}
