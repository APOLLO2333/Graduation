package com.supersong.graduation.service.impl;


import com.supersong.graduation.bean.WebLog;
import com.supersong.graduation.dao.WebLogDao;
import com.supersong.graduation.service.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional(rollbackFor = Exception.class)
public class WebLogServiceImpl implements WebLogService {

    @Autowired
    private WebLogDao webLogDao;

    public void add(WebLog webLog) {
        webLog.setCreateTime(new Date());
        try {
            webLogDao.add(webLog);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    public List<WebLog> getAll(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
            long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
//        long yesterday = System.currentTimeMillis() - 24 * 60 * 60 * 1000;//昨天的这一时间的毫秒数
            return webLogDao.getAll(zero / 1000, twelve / 1000);
        } else {
            return webLogDao.getAll(startTime, endTime);
        }
    }
}
